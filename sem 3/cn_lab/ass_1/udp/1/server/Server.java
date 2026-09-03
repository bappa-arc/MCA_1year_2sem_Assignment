import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Server {

    public static void main(String[] args) {

        try {

            DatagramSocket serverSocket =
                    new DatagramSocket(5000);

            System.out.println("UDP Date-Time Server Started...");
            System.out.println("Listening on Port 5000...");

            byte[] receiveBuffer =
                    new byte[1024];

            while (true) {

                DatagramPacket receivePacket =
                        new DatagramPacket(
                                receiveBuffer,
                                receiveBuffer.length);

                serverSocket.receive(receivePacket);

                String request =
                        new String(
                                receivePacket.getData(),
                                0,
                                receivePacket.getLength());

                request = request.trim().toUpperCase();

                String response;

                switch (request) {

                    case "DATE":
                        response =
                                LocalDate.now()
                                         .format(
                                            DateTimeFormatter
                                            .ofPattern(
                                            "dd-MM-yyyy"));
                        break;

                    case "TIME":
                        response =
                                LocalTime.now()
                                         .format(
                                            DateTimeFormatter
                                            .ofPattern(
                                            "HH:mm:ss"));
                        break;

                    case "DATETIME":
                        response =
                                LocalDateTime.now()
                                             .format(
                                                DateTimeFormatter
                                                .ofPattern(
                                                "dd-MM-yyyy HH:mm:ss"));
                        break;

                    default:
                        response =
                                "ERROR: Invalid Request";
                }

                byte[] sendBuffer =
                        response.getBytes();

                DatagramPacket sendPacket =
                        new DatagramPacket(
                                sendBuffer,
                                sendBuffer.length,
                                receivePacket.getAddress(),
                                receivePacket.getPort());

                serverSocket.send(sendPacket);

                System.out.println(
                        "Request: " + request +
                        " from " +
                        receivePacket.getAddress()
                                     .getHostAddress()
                        + ":" +
                        receivePacket.getPort());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}