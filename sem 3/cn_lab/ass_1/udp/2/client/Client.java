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

            byte[] sendBuffer =
                    fileName.getBytes();

            DatagramPacket sendPacket =
                    new DatagramPacket(
                            sendBuffer,
                            sendBuffer.length,
                            InetAddress.getByName(
                                    "localhost"),
                            5000);

            socket.send(sendPacket);

            byte[] receiveBuffer =
                    new byte[8192];

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
                    "\n----- Server Response -----");

            if(response.startsWith("ERROR")) {

                System.out.println(response);

            } else {

                java.io.PrintWriter writer = new java.io.PrintWriter( "received_" + fileName);

                writer.print(response);

                writer.close();

                System.out.println( "File saved as received_" + fileName);
            }
            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}