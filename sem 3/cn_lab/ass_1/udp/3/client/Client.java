import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {

            Scanner sc =
                    new Scanner(System.in);

            DatagramSocket socket =
                    new DatagramSocket();

            System.out.print(
                    "Enter filename: ");

            String fileName =
                    sc.nextLine();

            byte[] requestData =
                    fileName.getBytes();

            DatagramPacket request =
                    new DatagramPacket(
                            requestData,
                            requestData.length,
                            InetAddress.getByName(
                                    "localhost"),
                            5000);

            socket.send(request);

            FileOutputStream fos =
                    new FileOutputStream(
                            "received_" + fileName);

            while (true) {

                byte[] receiveBuffer =
                        new byte[2048];

                DatagramPacket packet =
                        new DatagramPacket(
                                receiveBuffer,
                                receiveBuffer.length);

                socket.receive(packet);

                String received =
                        new String(
                                packet.getData(),
                                0,
                                packet.getLength());

                if (received.equals("END")) {

                    System.out.println(
                            "Transfer Complete");

                    break;
                }

                if (received.startsWith("ERROR")) {

                    System.out.println(
                            received);

                    fos.close();

                    new File(
                            "received_"
                                    + fileName)
                            .delete();

                    return;
                }

                int separator =
                        received.indexOf('|');

                int sequence =
                        Integer.parseInt(
                                received.substring(
                                        0,
                                        separator));

                byte[] data =
                        received.substring(
                                separator + 1)
                                .getBytes();

                fos.write(data);

                System.out.println(
                        "Received Chunk "
                                + sequence);
            }

            fos.close();

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}