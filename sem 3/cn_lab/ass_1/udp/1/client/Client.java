import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            DatagramSocket socket =
                    new DatagramSocket();

            System.out.println(
                    "Commands: DATE, TIME, DATETIME");

            while (true) {

                System.out.print(
                        "\nEnter Request: ");

                String request =
                        sc.nextLine();

                byte[] sendBuffer =
                        request.getBytes();

                DatagramPacket sendPacket =
                        new DatagramPacket(
                                sendBuffer,
                                sendBuffer.length,
                                InetAddress.getByName(
                                        "localhost"),
                                5000);

                socket.send(sendPacket);

                byte[] receiveBuffer =
                        new byte[1024];

                DatagramPacket receivePacket =
                        new DatagramPacket(
                                receiveBuffer,
                                receiveBuffer.length);

                socket.receive(receivePacket);

                String response =
                        new String(
                                receivePacket.getData(),
                                0,
                                receivePacket.getLength());

                System.out.println(
                        "Server Response: "
                                + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}