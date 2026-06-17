import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPMathServer {
    public static void main(String[] args) {
        int port = 7000;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("UDP Math Server running on port " + port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                String request = new String(packet.getData(), 0, packet.getLength()).trim();
                String result = evaluateExpression(request);
                
                byte[] responseData = result.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(
                    responseData, responseData.length, packet.getAddress(), packet.getPort()
                );
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String evaluateExpression(String expr) {
        try {
            String[] parts = expr.split("\\s+");
            if (parts.length != 3) return "Error: Format must be [OP] [NUM1] [NUM2] (e.g., ADD 5 3)";
            
            String op = parts[0].toUpperCase();
            double num1 = Double.parseDouble(parts[1]);
            double num2 = Double.parseDouble(parts[2]);
            
            switch (op) {
                case "ADD": return String.valueOf(num1 + num2);
                case "SUB": return String.valueOf(num1 - num2);
                case "MUL": return String.valueOf(num1 * num2);
                case "DIV": return num2 == 0 ? "Error: Division by zero" : String.valueOf(num1 / num2);
                default: return "Error: Unknown operation " + op;
            }
        } catch (Exception e) {
            return "Error parsing expression.";
        }
    }
}