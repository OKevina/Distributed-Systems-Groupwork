import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UppercaseClient {
    public static void main(String[] args) {
        int port = 9000;
        try (Socket socket = new Socket("localhost", port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Type a string to uppercase: ");
            String text = scanner.nextLine();
            
            writer.println(text);
            String response = reader.readLine();
            System.out.println("Modified Response from Server: " + response);

        } catch (IOException e) {
            System.err.println("I/O Connection issue: " + e.getMessage());
        }
    }
}