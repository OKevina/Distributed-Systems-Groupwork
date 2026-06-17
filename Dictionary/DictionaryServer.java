import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class DictionaryServer {
    private static final ConcurrentHashMap<String, String> dictionary = new ConcurrentHashMap<>();

    static {
        dictionary.put("java", "A high-level, class-based, object-oriented programming language.");
        dictionary.put("socket", "An endpoint for communication between two machines.");
        dictionary.put("protocol", "A set of rules governing the exchange of data between devices.");
    }

    public static void main(String[] args) {
        int port = 8000;
        System.out.println("Dictionary Server indexed and running on port " + port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;
        public ClientHandler(Socket socket) { this.socket = socket; }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                
                String word = reader.readLine();
                if (word != null) {
                    String definition = dictionary.getOrDefault(word.trim().toLowerCase(), "Word not found.");
                    writer.println(definition);
                }
            } catch (IOException e) {
                System.err.println("Client handler runtime issue: " + e.getMessage());
            }
        }
    }
}