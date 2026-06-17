import java.io.*;
import java.net.*;

public class UppercaseServer {
    public static void main(String[] args) {
        int port = 9000;
        System.out.println("Uppercase Echo Server listening on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    String inputLine = reader.readLine();
                    if (inputLine != null) {
                        System.out.println("Received from client: " + inputLine);
                        writer.println(inputLine.toUpperCase());
                    }
                } catch (IOException e) {
                    System.out.println("Error handling client stream: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Server connection setup failed: " + e.getMessage());
        }
    }
}