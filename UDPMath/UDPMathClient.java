import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPMathClient {
    public static void main(String[] args) {
        int port = 7000;
        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {
            
            InetAddress address = InetAddress.getByName("localhost");
            System.out.println("Enter math expression (Format: [OP] [NUM1] [NUM2] e.g., ADD 12 4.5):");
            String expr = scanner.nextLine();
            
            byte[] buffer = expr.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
            
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket);
            
            String result = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Result from Server: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}