import java.io.*;
import java.util.*;

class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private double avgMarks;

    Profile(String name, int age, double avgMarks) {
        this.name = name;
        this.age = age;
        this.avgMarks = avgMarks;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public double getAvgMarks(){
        return avgMarks;
    }
}

public class Q17 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // Q17: Store records in Profile.bin
            System.out.print("Enter number of persons: ");
            int n = sc.nextInt();
            sc.nextLine();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Profile.bin"));

            for (int i = 1; i <= n; i++) {

                System.out.println("\nPerson " + i);

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Age: ");
                int age = sc.nextInt();

                System.out.print("Average Marks: ");
                double marks = sc.nextDouble();
                sc.nextLine();

                Profile p = new Profile(name, age, marks);
                oos.writeObject(p);
            }

            oos.close();

            System.out.println("\nRecords stored in Profile.bin");

            // Q18: Read Profile.bin and find highest scorer
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Profile.bin"));

            Profile top = null;

            while (true) {
                try {
                    Profile p = (Profile) ois.readObject();

                    if (top == null || p.getAvgMarks() > top.getAvgMarks()) {
                        top = p;
                    }

                } catch (EOFException e) {
                    break;
                }
            }

            ois.close();

            System.out.println("\nHighest Scorer Details");
            System.out.println("Name : " + top.getName());
            System.out.println("Age : " + top.getAge());
            System.out.println("Average Marks : " + top.getAvgMarks());

        } catch (Exception e) {
            System.out.println(e);
        }

        sc.close();
    }
}