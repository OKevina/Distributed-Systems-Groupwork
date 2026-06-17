import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class PingPongClient {
    public static void main(String[] args) {
        int port = 6000;
        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {
            
            InetAddress address = InetAddress.getByName("localhost");
            
            System.out.print("Enter message to send (try 'ping'): ");
            String message = scanner.nextLine();
            
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
            
            socket.setSoTimeout(3000);
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            
            try {
                socket.receive(responsePacket);
                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("Server response: " + response);
            } catch (java.net.SocketTimeoutException e) {
                System.out.println("No response received (Message dropped by the server).");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}