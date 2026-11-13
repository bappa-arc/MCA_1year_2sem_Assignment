import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;

    // Protocol requires every request to be exactly 8 bytes
    private static final int REQUEST_SIZE = 8;

    // Wait maximum 5 seconds for server response
    private static final int TIMEOUT = 5000;

    public static void main(String[] args) {

        try (
            Scanner sc = new Scanner(System.in);
            DatagramSocket socket = new DatagramSocket()
        ) {

            InetAddress serverAddress =
                    InetAddress.getByName(SERVER_HOST);

            socket.setSoTimeout(TIMEOUT);

            System.out.println("UDP Date-Time Client");
            System.out.println("Commands: DATE, TIME, DATETIME, QUIT");

            while (true) {

                System.out.print("\nEnter Request: ");

                String request = sc.nextLine().trim();

                if (request.isEmpty()) {
                    System.out.println("Error: Request cannot be empty.");
                    continue;
                }

                request = request.toUpperCase();

                // Check whether request fits into 8 bytes
                byte[] requestBytes =
                        request.getBytes(StandardCharsets.UTF_8);

                if (requestBytes.length > REQUEST_SIZE) {
                    System.out.println(
                            "Error: Request must be at most " +
                            REQUEST_SIZE + " bytes."
                    );
                    continue;
                }

                /*
                 * Create exactly 8-byte buffer.
                 *
                 * Remaining bytes are automatically initialized
                 * to 0x00.
                 */
                byte[] sendBuffer = new byte[REQUEST_SIZE];

                System.arraycopy(
                        requestBytes,
                        0,
                        sendBuffer,
                        0,
                        requestBytes.length
                );

                // Create UDP packet
                DatagramPacket sendPacket =
                        new DatagramPacket(
                                sendBuffer,
                                sendBuffer.length,
                                serverAddress,
                                SERVER_PORT
                        );

                // Send request
                socket.send(sendPacket);

                System.out.println(
                        "Sent: " + request +
                        " (" + sendBuffer.length + " bytes)"
                );

                // Receive response
                byte[] receiveBuffer = new byte[1024];

                DatagramPacket receivePacket =
                        new DatagramPacket(
                                receiveBuffer,
                                receiveBuffer.length
                        );

                try {

                    socket.receive(receivePacket);

                    String response = new String(
                            receivePacket.getData(),
                            receivePacket.getOffset(),
                            receivePacket.getLength(),
                            StandardCharsets.UTF_8
                    );

                    System.out.println(
                            "Server Response: " + response
                    );

                } catch (SocketTimeoutException e) {

                    System.out.println(
                            "Error: Server did not respond within " +
                            (TIMEOUT / 1000) + " seconds."
                    );

                    continue;
                }

                // QUIT terminates client
                if (request.equals("QUIT")) {
                    System.out.println("Client exiting...");
                    break;
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Client error: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}