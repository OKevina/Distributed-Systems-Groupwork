import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleLinkCrawler {
    public static void main(String[] args) {
        String targetUrl = "https://www.wikipedia.org";
        System.out.println("Crawling links from: " + targetUrl);

        try {
            URL url = new URL(targetUrl);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                Pattern pattern = Pattern.compile("href=\"(http[s]?://.*?)\"", Pattern.CASE_INSENSITIVE);

                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        System.out.println("Found Hyperlink: " + matcher.group(1));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Crawler stopped: " + e.getMessage());
        }
    }
}