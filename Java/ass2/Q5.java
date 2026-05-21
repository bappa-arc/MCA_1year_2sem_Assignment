

class InvalidEmpDataException extends Exception {
    public InvalidEmpDataException(String message) {
        super(message);
    }
}

class Emp {
    protected int id;
    protected int depCode;

    public Emp(int id, int depCode) throws InvalidEmpDataException {
        validateDepCode(depCode);
        this.id = id;
        this.depCode = depCode;
    }

    protected static void validateDepCode(int depCode) throws InvalidEmpDataException {
        if (depCode < 0 || depCode > 5) {
            throw new InvalidEmpDataException("Invalid Dep Code: " + depCode + ". Must be between 0 and 5.");
        }
    }

    @Override
    public String toString() {
        return "Emp{id=" + id + ", depCode=" + depCode + "}";
    }
}

class GradedEmp extends Emp {
    private char grade;

    public GradedEmp(int id, int depCode, char grade) throws InvalidEmpDataException {
        super(id, depCode == 0 ? 10 : depCode);
        validateGrade(grade);
        this.grade = grade;
    }

    public GradedEmp(int id, int depCode, double[] skillTestMarks) throws InvalidEmpDataException {
        this(id, depCode, gradeFromAverage(skillTestMarks));
    }

    private static void validateGrade(char grade) throws InvalidEmpDataException {
        if (grade < 'A' || grade > 'E') {
            throw new InvalidEmpDataException("Invalid Grade: " + grade + ". Must be A to E.");
        }
    }

    private static char gradeFromAverage(double[] marks) throws InvalidEmpDataException {
        if (marks == null || marks.length == 0) {
            throw new InvalidEmpDataException("Skill test marks must contain at least one score.");
        }
        double sum = 0;
        for (double mark : marks) {
            if (mark < 0 || mark > 100) {
                throw new InvalidEmpDataException("Invalid skill test mark: " + mark + ". Must be 0-100.");
            }
            sum += mark;
        }
        double average = sum / marks.length;

        if (average >= 80) return 'A';
        if (average >= 65) return 'B';
        if (average >= 50) return 'C';
        if (average >= 35) return 'D';
        return 'E';
    }

    @Override
    public String toString() {
        return "GradedEmp{id=" + id + ", depCode=" + depCode + ", grade=" + grade + "}";
    }
}

public class Q5 {
    public static void main(String[] args) {
        try {
            Emp baseEmp = new Emp(101, 3);
            System.out.println(baseEmp);

            GradedEmp derivedEmp = new GradedEmp(201, 0, 'B');
            System.out.println(derivedEmp);

            GradedEmp derivedFromMarks = new GradedEmp(301, 2, new double[] { 88, 74, 92 });
            System.out.println(derivedFromMarks);

            // This line would throw an exception because Dep Code 7 is invalid
            // Emp invalidEmp = new Emp(401, 7);

            // This line would throw an exception because grade 'F' is invalid
            // GradedEmp invalidGrade = new GradedEmp(501, 1, 'F');
        } catch (InvalidEmpDataException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
