import java.util.Arrays;
import java.util.Scanner;
class InvalidEmployeeDataException extends Exception {
    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}

class Emp {
    protected int id;
    protected int depCode;
    public Emp(int id, int depCode) throws InvalidEmployeeDataException {
        if (depCode < 0 || depCode > 5) { 
            throw new InvalidEmployeeDataException("Invalid Base Employee Department Code: " + depCode + ". Allowed range is 0 to 5.");
        }
        this.id = id;
        this.depCode = depCode;
    }
    protected Emp(int id, int depCode, int maxRange) throws InvalidEmployeeDataException {
        if (depCode < 0 || depCode > maxRange) { 
            throw new InvalidEmployeeDataException("Invalid Department Code: " + depCode + ". Allowed range is 0 to " + maxRange + ".");
        }
        this.id = id;
        this.depCode = depCode;
    }
    public void display() {
        System.out.print("Emp ID: " + id + " | Dep Code: " + depCode);
    }
}

class GradedEmp extends Emp {
    private double[] skillTestMarks;
    public GradedEmp(int id, int depCode, double[] skillTestMarks) throws InvalidEmployeeDataException {
        super(id, depCode == 0 ? 10 : depCode, 10);
        if (skillTestMarks == null || skillTestMarks.length == 0) {
            throw new InvalidEmployeeDataException("Skill test marks cannot be null or empty.");
        }
        for (double mark : skillTestMarks) {
            if (mark < 0 || mark > 100) {
                throw new InvalidEmployeeDataException("Invalid test mark discovered: " + mark + ". Must be between 0 and 100.");
            }
        }
        this.skillTestMarks = skillTestMarks;
    }
    public char calculateGrade() {
        double sum = 0;
        for (double mark : skillTestMarks) {
            sum += mark;
        }
        double average = sum / skillTestMarks.length;
        if (average >= 85) return 'A';
        else if (average >= 70) return 'B';
        else if (average >= 55) return 'C';
        else if (average >= 40) return 'D';
        else return 'E';
    }
    @Override
    public void display() {
        super.display();
        System.out.println(" | Marks: " + Arrays.toString(skillTestMarks) + " | Grade: " + calculateGrade());
    }
}

public class Q5 {
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
                    System.out.print("Enter Employee ID: ");
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
                    System.out.print("Enter Employee ID: ");
                    int gEmpId = scanner.nextInt();
                    System.out.print("Enter Department Code [0-5]: ");
                    int gDepCode = scanner.nextInt();
                    System.out.print("How many skill tests did this employee take? ");
                    int totalTests = scanner.nextInt();
                    if (totalTests <= 0) {
                        System.out.println(">> Test count must be greater than 0.");
                        break;
                    }
                    double[] marks = new double[totalTests];
                    for (int i = 0; i < totalTests; i++) {
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
                    System.out.println("\nExiting System");
                    break;
                default:
                    System.out.println("Invalid selection. Please choose an option between 1 and 3.");
            }
            System.out.println("\n-------------------------------------------------");
        }
        scanner.close();
    }
}