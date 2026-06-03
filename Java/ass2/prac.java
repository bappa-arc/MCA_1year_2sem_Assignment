import java.util.Scanner;

public class prac {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("  Employee & Graded Employee Verification System ");
        System.out.println("=================================================");

        while (running) {
            System.out.println("\nSelect a Test Scenario:");
            System.out.println("1) Test Standard Employee Class (Emp)");
            System.out.println("2) Test Graded Employee Class (GradedEmp - with dynamic grading)");
            System.out.println("3) Exit");
            System.out.print("Enter choice (1-3): ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- [Scenario 1: Standard Employee] ---");
                    System.out.print("Enter Employee ID (e.g., 101): ");
                    int empId = scanner.nextInt();
                    System.out.print("Enter Department Code [0-5]: ");
                    int depCode = scanner.nextInt();

                    try {
                        Emp emp = new Emp(empId, depCode);
                        System.out.print(">> Success! Created Object: ");
                        emp.display();
                        System.out.println();
                    } catch (InvalidEmployeeDataException e) {
                        System.out.println(">> Trap Success! Custom Exception Caught: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n--- [Scenario 2: Graded Employee] ---");
                    System.out.print("Enter Employee ID (e.g., 201): ");
                    int gEmpId = scanner.nextInt();
                    
                    // Tip for testing: Entering '0' here will test requirement 6(b) conversion to '10'
                    System.out.print("Enter Department Code [0-5] (Try '0' to watch it map to '10'): ");
                    int gDepCode = scanner.nextInt();

                    System.out.print("How many skill tests did this employee take? ");
                    int totalTests = scanner.nextInt();
                    
                    if (totalTests <= 0) {
                        System.out.println(">> Test count must be greater than 0.");
                        break;
                    }

                    double[] marks = new double[totalTests];
                    for (int i = 0; i < totalTests; i++) {
                        // Tip for testing: Entering negative values or scores > 100 will trigger the custom validation trap
                        System.out.print("  Enter score for test #" + (i + 1) + " (0-100): ");
                        marks[i] = scanner.nextDouble();
                    }

                    try {
                        GradedEmp gEmp = new GradedEmp(gEmpId, gDepCode, marks);
                        System.out.print(">> Success! Created Object: ");
                        gEmp.display();
                    } catch (InvalidEmployeeDataException e) {
                        System.out.println(">> Trap Success! Custom Exception Caught: " + e.getMessage());
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("\nExiting System. Happy Testing!");
                    break;

                default:
                    System.out.println("Invalid selection. Please choose an option between 1 and 3.");
            }
            System.out.println("\n-------------------------------------------------");
        }
        scanner.close();
    }
    
}
