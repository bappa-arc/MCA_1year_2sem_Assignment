import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Server {

    public static void main(String[] args) {

        try {

            DatagramSocket serverSocket =
                    new DatagramSocket(5000);

            System.out.println("UDP File Server Started...");
            System.out.println("Listening on Port 5000");

            byte[] receiveBuffer =
                    new byte[1024];

            while (true) {

                DatagramPacket receivePacket =
                        new DatagramPacket(
                                receiveBuffer,
                                receiveBuffer.length);

                serverSocket.receive(receivePacket);

                String fileName =
                        new String(
                                receivePacket.getData(),
                                0,
                                receivePacket.getLength());

                System.out.println(
                        "\nFile Requested: "
                                + fileName);

                String response;

                File file =
                        new File(fileName);

                if (!file.exists()) {

                    response =
                            "ERROR: File Not Found";

                } else {

                    response =
                            Files.readString(
                                    file.toPath());
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
                        "Response sent to "
                                + receivePacket
                                .getAddress()
                                .getHostAddress()
                                + ":"
                                + receivePacket
                                .getPort());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}