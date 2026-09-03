import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Server {

    private static final Object serviceLock = new Object();

    private static volatile boolean clientBusy = false;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            System.out.println("=================================");
            System.out.println(" Command TCP Server Started");
            System.out.println(" Port: 5000");
            System.out.println(" Ctrl+C to stop server");
            System.out.println("=================================");

            while (true) {

                Socket client = serverSocket.accept();

                String ip =
                        client.getInetAddress().getHostAddress();

                int port =
                        client.getPort();

                System.out.println("\nNew connection request");
                System.out.println("IP   : " + ip);
                System.out.println("Port : " + port);

                new Thread(() -> handleClient(client)).start();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void handleClient(Socket client) {

        try {

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    client.getInputStream()));

            PrintWriter out =
                    new PrintWriter(
                            client.getOutputStream(),
                            true);

            if (clientBusy) {

                out.println(
                        "WAIT: Another client is currently being served.");

                System.out.println(
                        "Client placed in waiting state: "
                        + client.getInetAddress().getHostAddress()
                        + ":"
                        + client.getPort());
            }

            synchronized (serviceLock) {

                clientBusy = true;

                out.println("READY: You can now send commands.");

                System.out.println("\nServing client:");
                System.out.println(
                        "IP   : "
                        + client.getInetAddress()
                                .getHostAddress());

                System.out.println(
                        "Port : "
                        + client.getPort());

                String command;

                while ((command = in.readLine()) != null) {

                    command = command.trim();

                    System.out.println(
                            "Received: " + command);

                    if (command.equalsIgnoreCase("TIME")) {

                        out.println(
                                LocalTime.now().format(
                                        DateTimeFormatter.ofPattern(
                                                "HH:mm:ss")));

                    } else if (
                            command.equalsIgnoreCase("DATE")) {

                        out.println(
                                LocalDate.now().format(
                                        DateTimeFormatter.ofPattern(
                                                "dd-MM-yyyy")));

                    } else if (
                            command.equalsIgnoreCase("HOSTNAME")) {

                        out.println(
                                InetAddress
                                        .getLocalHost()
                                        .getHostName());

                    } else if (
                            command.toUpperCase()
                                    .startsWith("ECHO ")) {

                        out.println(
                                command.substring(5));

                    } else if (
                            command.equalsIgnoreCase("QUIT")) {

                        out.println("GOODBYE");

                        break;

                    } else {

                        out.println(
                                "ERROR: INVALID COMMAND");
                    }
                }

                clientBusy = false;

                System.out.println(
                        "Client disconnected: "
                        + client.getInetAddress()
                                .getHostAddress()
                        + ":"
                        + client.getPort());

            }

            client.close();

        } catch (Exception e) {

            clientBusy = false;

            System.out.println(
                    "Client disconnected unexpectedly.");
        }
    }
}