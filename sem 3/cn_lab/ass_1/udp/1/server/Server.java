import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Server {

    private static final int PORT = 5000;
    private static final int BUFFER_SIZE = 8;

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final DateTimeFormatter DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static void main(String[] args) {

        System.out.println("UDP Date-Time Server Started...");
        System.out.println("Listening on Port " + PORT + "...");

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {

            while (true) {

                // Receive exactly one UDP request
                byte[] receiveBuffer = new byte[BUFFER_SIZE];

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveBuffer, receiveBuffer.length);

                serverSocket.receive(receivePacket);

                // Convert only the bytes actually received
                String request = new String(
                        receivePacket.getData(),
                        receivePacket.getOffset(),
                        receivePacket.getLength(),
                        StandardCharsets.UTF_8
                ).trim().toUpperCase();

                String clientAddress =
                        receivePacket.getAddress().getHostAddress();

                int clientPort = receivePacket.getPort();

                System.out.println(
                        "Request: " + request +
                        " from " + clientAddress +
                        ":" + clientPort
                );

                // Generate response
                String response;

                switch (request) {

                    case "DATE":
                        response = LocalDate.now().format(DATE_FORMAT);
                        break;

                    case "TIME":
                        response = LocalTime.now().format(TIME_FORMAT);
                        break;

                    case "DATETIME":
                        response = LocalDateTime.now().format(DATETIME_FORMAT);
                        break;

                    case "QUIT":
                        response = "GOODBYE";
                        break;

                    default:
                        response = "ERROR: Invalid Request";
                }

                // Send response
                byte[] sendBuffer =
                        response.getBytes(StandardCharsets.UTF_8);

                DatagramPacket sendPacket =
                        new DatagramPacket(
                                sendBuffer,
                                sendBuffer.length,
                                receivePacket.getAddress(),
                                receivePacket.getPort()
                        );

                serverSocket.send(sendPacket);

                // Stop server after responding to QUIT
                if (request.equals("QUIT")) {
                    System.out.println("Client requested server shutdown.");
                    break;
                }
            }

        } catch (java.net.BindException e) {

            System.err.println(
                    "Error: Port " + PORT + " is already in use."
            );

        } catch (Exception e) {

            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Server stopped.");
    }
}