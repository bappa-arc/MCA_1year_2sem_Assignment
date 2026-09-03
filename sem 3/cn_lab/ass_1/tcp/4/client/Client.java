import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            Socket socket =
                    new Socket("localhost", 5000);

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter out =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true);

            while (true) {

                System.out.print("Command: ");

                String cmd =
                        sc.nextLine();

                out.println(cmd);

                String response =
                        in.readLine();

                System.out.println(
                        "Server: "
                                + response);

                if (cmd.equalsIgnoreCase(
                        "QUIT")) {

                    break;
                }
            }

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}