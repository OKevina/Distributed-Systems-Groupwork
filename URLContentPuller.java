import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLContentPuller {
    public static void main(String[] args) {
        String urlString = "http://www.buyya.com";
        try {
            URL url = new URL(urlString);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                System.out.println("--- Content from " + urlString + " ---");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching URL content: " + e.getMessage());
        }
    }
}