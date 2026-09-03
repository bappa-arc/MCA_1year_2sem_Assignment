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
                    "Enter Host Name: ");

            String hostName =
                    sc.nextLine();

            byte[] sendBuffer =
                    hostName.getBytes();

            DatagramPacket request =
                    new DatagramPacket(
                            sendBuffer,
                            sendBuffer.length,
                            InetAddress.getByName(
                                    "localhost"),
                            5000);

            socket.send(request);

            byte[] receiveBuffer =
                    new byte[1024];

            DatagramPacket response =
                    new DatagramPacket(
                            receiveBuffer,
                            receiveBuffer.length);

            socket.receive(response);

            String result =
                    new String(
                            response.getData(),
                            0,
                            response.getLength());

            System.out.println(
                    "\nIP Address: "
                            + result);

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}