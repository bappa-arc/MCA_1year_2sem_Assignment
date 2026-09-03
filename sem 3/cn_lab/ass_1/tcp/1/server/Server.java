import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            String str = in.readLine();

            int length = str.length();
            int vowels = 0;
            int consonants = 0;
            int digits = 0;

            for (char ch : str.toCharArray()) {

                if (Character.isDigit(ch)) {
                    digits++;
                }
                else if (Character.isLetter(ch)) {

                    ch = Character.toLowerCase(ch);

                    if ("aeiou".indexOf(ch) != -1) {
                        vowels++;
                    } else {
                        consonants++;
                    }
                }
            }

            String result =
                    "Length = " + length +
                    "\nVowels = " + vowels +
                    "\nConsonants = " + consonants +
                    "\nDigits = " + digits;

            out.println(result);

            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}