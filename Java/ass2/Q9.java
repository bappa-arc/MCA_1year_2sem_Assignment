import java.util.Scanner;
import shapes.*;
import animals.*;
import sorting.*;
import resize.*;

public class Q9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Shape Area");
            System.out.println("2. Animal Sounds");
            System.out.println("3. Bubble Sort");
            System.out.println("4. Tray Resize");
            System.out.println("5. Box Resize");
            System.out.println("0. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    int shapeChoice;
                    System.out.println("\n1. Rectangle");
                    System.out.println("2. Circle");
                    System.out.println("3. Triangle");
                    System.out.print("Choose Shape: ");
                    shapeChoice = sc.nextInt();
                    switch(shapeChoice) {
                        case 1:
                            System.out.print("Enter Length: ");
                            double l = sc.nextDouble();
                            System.out.print("Enter Width: ");
                            double w = sc.nextDouble();
                            Rectangle r = new Rectangle(l, w);
                            System.out.println("Area = " + r.getArea());
                            break;
                        case 2:
                            System.out.print("Enter Radius: ");
                            double radius = sc.nextDouble();
                            Circle c = new Circle(radius);
                            System.out.println("Area = " + c.getArea());
                            break;
                        case 3:
                            System.out.print("Enter Base: ");
                            double b = sc.nextDouble();
                            System.out.print("Enter Height: ");
                            double h = sc.nextDouble();
                            Triangle t = new Triangle(b, h);
                            System.out.println("Area = " + t.getArea());
                            break;
                        default:
                            System.out.println("Invalid Shape Choice");
                    }
                    break;
                case 2:
                    Animal dog = new Dog();
                    Animal cat = new Cat();
                    Animal cow = new Cow();
                    dog.sound();
                    cat.sound();
                    cow.sound();
                    break;
                case 3:
                    System.out.print("Enter Size of Array: ");
                    int n = sc.nextInt();
                    int arr[] = new int[n];
                    System.out.println("Enter Elements:");
                    for(int i = 0; i < n; i++) {
                        arr[i] = sc.nextInt();
                    }
                    BubbleSort bs = new BubbleSort();
                    bs.sort(arr);
                    break;
                case 4:
                    System.out.print("Enter Tray Width: ");
                    int trayWidth = sc.nextInt();
                    System.out.print("Enter Tray Length: ");
                    int trayLength = sc.nextInt();
                    System.out.print("Enter Plate Width: ");
                    int plateWidth = sc.nextInt();
                    System.out.print("Enter Plate Length: ");
                    int plateLength = sc.nextInt();
                    Tray tray = new Tray();
                    tray.L(trayLength);
                    tray.W(trayWidth);
                    tray.setPlateSize(plateWidth, plateLength);
                    tray.display();
                    break;
                case 5:
                    System.out.print("Enter Box Width: ");
                    int boxWidth = sc.nextInt();
                    System.out.print("Enter Box Length: ");
                    int boxLength = sc.nextInt();
                    System.out.print("Enter Box Depth: ");
                    int boxDepth = sc.nextInt();
                    System.out.print("Enter Ball Width: ");
                    int ballWidth = sc.nextInt();
                    System.out.print("Enter Ball Length: ");
                    int ballLength = sc.nextInt();
                    System.out.print("Enter Ball Depth: ");
                    int ballDepth = sc.nextInt();
                    NewBox box = new NewBox(boxWidth, boxLength, boxDepth);
                    box.setBallSize(ballWidth, ballLength, ballDepth);
                    box.display();
                    break;
                case 0:
                    System.out.println("Program Exited");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        } while(choice != 0);
        sc.close();
    }
}