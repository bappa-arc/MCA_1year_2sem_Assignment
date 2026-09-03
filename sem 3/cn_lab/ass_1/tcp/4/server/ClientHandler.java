import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ClientHandler extends Thread {

    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    client.getInputStream()));

            PrintWriter out =
                    new PrintWriter(
                            client.getOutputStream(),
                            true);

            String command;

            while ((command = in.readLine()) != null) {

                command = command.trim();

                System.out.println(
                        "[" + client.getPort() + "] "
                                + command);

                if (command.equalsIgnoreCase("TIME")) {

                    out.println(
                            LocalTime.now().format(
                                    DateTimeFormatter
                                            .ofPattern(
                                                    "HH:mm:ss")));

                } else if (
                        command.equalsIgnoreCase("DATE")) {

                    out.println(
                            LocalDate.now());

                } else if (
                        command.equalsIgnoreCase(
                                "HOSTNAME")) {

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
                        command.equalsIgnoreCase(
                                "QUIT")) {

                    out.println("GOODBYE");

                    break;

                } else {

                    out.println(
                            "ERROR: INVALID COMMAND");
                }
            }

            System.out.println(
                    "Client Disconnected: "
                            + client.getPort());

            client.close();

        } catch (Exception e) {

            System.out.println(
                    "Connection Lost: "
                            + client.getPort());
        }
    }
}