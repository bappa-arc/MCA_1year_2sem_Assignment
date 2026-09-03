import java.io.*;
import java.net.*;

public class Server {

    private static final int CHUNK_SIZE = 1024;

    public static void main(String[] args) {

        try {

            DatagramSocket serverSocket =
                    new DatagramSocket(5000);

            System.out.println(
                    "Chunk UDP Server Started");

            byte[] receiveBuffer =
                    new byte[1024];

            while (true) {

                DatagramPacket request =
                        new DatagramPacket(
                                receiveBuffer,
                                receiveBuffer.length);

                serverSocket.receive(request);

                String fileName =
                        new String(
                                request.getData(),
                                0,
                                request.getLength());

                System.out.println(
                        "\nRequested File: "
                                + fileName);

                File file =
                        new File(fileName);

                InetAddress clientAddress =
                        request.getAddress();

                int clientPort =
                        request.getPort();

                if (!file.exists()) {

                    String error =
                            "ERROR: FILE NOT FOUND";

                    DatagramPacket errorPacket =
                            new DatagramPacket(
                                    error.getBytes(),
                                    error.length(),
                                    clientAddress,
                                    clientPort);

                    serverSocket.send(errorPacket);

                    continue;
                }

                FileInputStream fis =
                        new FileInputStream(file);

                byte[] fileBuffer =
                        new byte[CHUNK_SIZE];

                int bytesRead;

                int sequence = 0;

                while ((bytesRead =
                        fis.read(fileBuffer)) != -1) {

                    String header =
                            sequence + "|";

                    byte[] headerBytes =
                            header.getBytes();

                    byte[] packetData =
                            new byte[
                                    headerBytes.length
                                    + bytesRead];

                    System.arraycopy(
                            headerBytes,
                            0,
                            packetData,
                            0,
                            headerBytes.length);

                    System.arraycopy(
                            fileBuffer,
                            0,
                            packetData,
                            headerBytes.length,
                            bytesRead);

                    DatagramPacket packet =
                            new DatagramPacket(
                                    packetData,
                                    packetData.length,
                                    clientAddress,
                                    clientPort);

                    serverSocket.send(packet);

                    System.out.println(
                            "Sent Chunk "
                                    + sequence);

                    sequence++;
                }

                String endMessage = "END";

                DatagramPacket endPacket =
                        new DatagramPacket(
                                endMessage.getBytes(),
                                endMessage.length(),
                                clientAddress,
                                clientPort);

                serverSocket.send(endPacket);

                fis.close();

                System.out.println(
                        "File Transfer Complete");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}