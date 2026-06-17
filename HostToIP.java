import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HostToIP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter host name (e.g., google.com): ");
        String host = scanner.nextLine();

        try {
            InetAddress address = InetAddress.getByName(host);
            System.out.println("Host Name: " + address.getHostName());
            System.out.println("IP Address: " + address.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Could not resolve host: " + host);
        } finally {
            scanner.close();
        }
    }
}