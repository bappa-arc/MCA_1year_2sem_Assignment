import java.io.*;
import java.util.*;

public class Q14 {
    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter("Fibo.txt");
            int a = 0, b = 1;
            for (int i = 0; i < 10; i++) {
                fw.write(a + " ");
                int c = a + b;
                a = b;
                b = c;
            }
            fw.close();
            System.out.println("First 10 Fibonacci numbers written to Fibo.txt");
            Scanner sc = new Scanner(new File("Fibo.txt"));
            ArrayList<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            sc.close();
            int n = list.size();
            a = list.get(n - 2);
            b = list.get(n - 1);
            FileWriter appendFile = new FileWriter("Fibo.txt", true);
            while (true) {
                int next = a + b;
                if (next >= 250)
                    break;
                appendFile.write(next + " ");
                a = b;
                b = next;
            }
            appendFile.close();
            System.out.println("Numbers less than 250 appended.");
            Scanner readTxt = new Scanner(new File("Fibo.txt"));
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("Fibo.bin"));
            while (readTxt.hasNextInt()) {
                dos.writeInt(readTxt.nextInt());
            }
            readTxt.close();
            dos.close();
            System.out.println("Binary file Fibo.bin created.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}