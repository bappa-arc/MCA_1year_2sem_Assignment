import java.util.Scanner;
import java.util.Calendar;

class Person_1 {
    String name;
    int day, month, year;
    Calendar today = Calendar.getInstance();
    int currentYear = today.get(Calendar.YEAR);
    int currentMonth = today.get(Calendar.MONTH) + 1;
    int currentDay = today.get(Calendar.DAY_OF_MONTH);

    Person_1(String name, int d, int m, int y) {
        this.name = name;
        this.day = d;
        this.month = m;
        this.year = y;
    }

    public boolean isValidDate() {
        if (month < 1 || month > 12) return false;

        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
            daysInMonth[2] = 29;
        }

        if (day <= 0 || day > daysInMonth[month]) return false;

        Calendar dob = Calendar.getInstance();
        dob.set(year, month - 1, day);
        if (dob.after(today)) return false;

        return true;
    }

    public int calculateAge() {
        int age = currentYear - year;
        if (currentMonth < month || (currentMonth == month && currentDay < day)) {
            age--;
        }
        return age;
    }

    public String getAgeCategory() {
        int age = calculateAge();
        if (age < 18) return "Under Age.";
        if (age < 30) return "Young Adult.";
        if (age < 45) return "Matured Adult.";
        if (age < 60) return "Middle Aged.";
        return "Senior.";
    }

    public void display() {
        if (!isValidDate()) {
            System.out.println("Error: The date " + day + "/" + month + "/" + year + " is invalid or in the future.");
            return;
        }
        System.out.println("\nName: " + name);
        System.out.println("\nAge: " + calculateAge());
        System.out.println("\nCategory: " + getAgeCategory());
    }
}

public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter name: ");
            String name = sc.nextLine();
            System.out.println("Enter DOB (Day Month Year): ");
            int d = sc.nextInt();
            int m = sc.nextInt();
            int y = sc.nextInt();
            Person_1 p = new Person_1(name, d, m, y);
            p.display();
        } catch (Exception e) {
            System.out.println("Error: Enter valid numeric values for the date.");
        } finally {
            sc.close();
        }
    }
}   