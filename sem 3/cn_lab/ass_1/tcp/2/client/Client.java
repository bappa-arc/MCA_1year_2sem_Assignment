import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {

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

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter filename: ");
            String fileName = sc.nextLine();

            out.println(fileName);

            String status = in.readLine();

            if (status.equals("FILE_NOT_FOUND")) {

                System.out.println("File does not exist on server.");

            } else {

                PrintWriter writer =
                        new PrintWriter(
                                new FileWriter(
                                        "received_" + fileName));

                String line;

                while (!(line = in.readLine()).equals("EOF")) {

                    writer.println(line);
                }

                writer.close();

                System.out.println(
                        "File received successfully.");

            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}