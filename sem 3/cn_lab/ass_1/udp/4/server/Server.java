import java.net.*;
import java.util.HashMap;

public class Server {

    public static void main(String[] args) {

        try {

            DatagramSocket serverSocket =
                    new DatagramSocket(5000);

            System.out.println(
                    "UDP Name Service Started...");

            HashMap<String, String> dnsTable =
                    new HashMap<>();

            dnsTable.put(
                    "server1",
                    "192.168.1.10");

            dnsTable.put(
                    "server2",
                    "192.168.1.20");

            dnsTable.put(
                    "server3",
                    "192.168.1.30");

            byte[] receiveBuffer =
                    new byte[1024];

            while (true) {

                DatagramPacket request =
                        new DatagramPacket(
                                receiveBuffer,
                                receiveBuffer.length);

                serverSocket.receive(request);

                String hostName =
                        new String(
                                request.getData(),
                                0,
                                request.getLength());

                hostName =
                        hostName.trim()
                                .toLowerCase();

                System.out.println(
                        "Lookup Request: "
                                + hostName);

                String result =
                        dnsTable.getOrDefault(
                                hostName,
                                "NOT FOUND");

                byte[] sendBuffer =
                        result.getBytes();

                DatagramPacket response =
                        new DatagramPacket(
                                sendBuffer,
                                sendBuffer.length,
                                request.getAddress(),
                                request.getPort());

                serverSocket.send(response);

                System.out.println(
                        "Response Sent: "
                                + result);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}