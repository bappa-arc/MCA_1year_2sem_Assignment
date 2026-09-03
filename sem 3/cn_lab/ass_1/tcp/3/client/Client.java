import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Server IP: ");
            String ip = sc.nextLine();

            Socket socket =
                    new Socket(ip, 5000);

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter out =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true);

            System.out.println(
                    "Connected to server.");

            String serverMessage;

            while (true) {

                serverMessage = in.readLine();

                if (serverMessage == null) {
                    System.out.println(
                            "Server disconnected.");
                    socket.close();
                    return;
                }

                System.out.println(
                        "Server: " + serverMessage);

                if (serverMessage.startsWith("READY")) {
                    break;
                }
            }

            System.out.println();
            System.out.println(
                    "Available commands:");

            System.out.println(
                    "TIME");
            System.out.println(
                    "DATE");
            System.out.println(
                    "HOSTNAME");
            System.out.println(
                    "ECHO <message>");
            System.out.println(
                    "QUIT");

            while (true) {

                System.out.print("\nCommand> ");

                String command =
                        sc.nextLine();

                out.println(command);

                String response =
                        in.readLine();

                if (response == null) {

                    System.out.println(
                            "Server disconnected.");

                    break;
                }

                System.out.println(
                        "Server: "
                        + response);

                if (command.equalsIgnoreCase("QUIT")) {
                    break;
                }
            }

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}