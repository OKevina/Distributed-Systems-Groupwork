import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PingPongServer {
    public static void main(String[] args) {
        int port = 6000;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Ping-Pong Server started on port " + port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                String received = new String(packet.getData(), 0, packet.getLength()).trim();
                System.out.println("Received: " + received);

                if ("ping".equalsIgnoreCase(received)) {
                    byte[] responseData = "pong".getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(
                        responseData, responseData.length, packet.getAddress(), packet.getPort()
                    );
                    socket.send(responsePacket);
                } else {
                    System.out.println("Ignored non-ping message.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}