import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DictionaryClient {
    public static void main(String[] args) {
        int port = 8000;
        try (Socket socket = new Socket("localhost", port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter word to look up (e.g., java, socket, protocol): ");
            String word = scanner.nextLine();
            writer.println(word);

            String response = reader.readLine();
            System.out.println("Definition: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}