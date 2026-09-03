import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server Started...");
            System.out.println("Waiting for Client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client Connected");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            String fileName = in.readLine();

            File file = new File(fileName);

            if (!file.exists()) {
                out.println("FILE_NOT_FOUND");
            } else {

                out.println("FILE_FOUND");

                BufferedReader fileReader =
                        new BufferedReader(
                                new FileReader(file));

                String line;

                while ((line = fileReader.readLine()) != null) {
                    out.println(line);
                }

                out.println("EOF");

                fileReader.close();
            }

            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}