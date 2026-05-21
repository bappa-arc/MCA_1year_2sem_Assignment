import java.util.Scanner;
import mystack.*;

public class Q4 {

    static void moveDisk(
            SpecialStack source,
            SpecialStack destination,
            String s,
            String d) {

        int disk = source.pop();

        if (disk != -1) {

            if (destination.push(disk)) {

            System.out.println(
                "Move Disk " + disk +
                " from " + s +
                " to " + d
            );

        } else {

            // restore disk if move failed
            source.push(disk);

            System.out.println(
                "Illegal Move Prevented!"
            );
        }
        }
    }

    static void insertDisk(
            int disk,
            SpecialStack destination,
            SpecialStack auxiliary,
            String d,
            String a) {

        // Move smaller disks away temporarily
        while (!destination.isEmpty()
                && destination.peek() < disk
                && !auxiliary.isFull()) {

            moveDisk(destination, auxiliary, d, a);
        }

        // Insert disk
        if (destination.push(disk)) {

            System.out.println(
                "Inserted Disk " + disk +
                " into " + d
            );
        }
        while (!auxiliary.isEmpty()) {

            moveDisk(auxiliary, destination, a, d);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid Size!");
            return;
        }

        int input[] = new int[n];

        System.out.println("Enter haphazard disk values:");

        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }
        SpecialStack source = new SpecialStack(n);
        SpecialStack auxiliary = new SpecialStack(n);
        SpecialStack destination = new SpecialStack(n);

        // Insert values one by one
        for (int i = 0; i < n; i++) {

            int disk = input[i];

            // First disk
            if (destination.isEmpty()) {

                destination.push(disk);

                System.out.println(
                    "Inserted Disk " + disk
                );
            }
            else if (disk <= destination.peek()) {

                destination.push(disk);

                System.out.println(
                    "Inserted Disk " + disk
                );
            }

            // Need Hanoi rearrangement
            else {

                insertDisk(
                    disk,
                    destination,
                    auxiliary,
                    "DESTINATION",
                    "AUXILIARY"
                );
            }
        }
        System.out.println("Final Destination Stack");
        destination.display();

        sc.close();
    }

}
