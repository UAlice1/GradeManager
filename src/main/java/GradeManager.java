import java.util.Arrays;

// Custom exception for invalid grades
class InvalidGradeException extends IllegalArgumentException {
    public InvalidGradeException(String message) {
        super(message);
    }
}

public class GradeManager {
    private int[] grades;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public GradeManager() {
        this.grades = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public GradeManager(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.grades = new int[initialCapacity];
        this.size = 0;
    }

    // Add a grade to the manager
    public void addGrade(int grade) throws InvalidGradeException {
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException("Grade must be between 0 and 100");
        }

        // Resize array if needed
        if (size == grades.length) {
            grades = Arrays.copyOf(grades, grades.length * 2);
        }

        grades[size++] = grade;
    }

    // Sort grades in ascending order
    public void sortGrades() {
        Arrays.sort(grades, 0, size);
    }

    // Search for a grade (returns index or -1 if not found)
    public int searchGrade(int grade) {
        for (int i = 0; i < size; i++) {
            if (grades[i] == grade) {
                return i;
            }
        }
        return -1;
    }

    // Calculate average grade
    public double calculateAverage() {
        if (size == 0) {
            throw new IllegalStateException("No grades available to calculate average");
        }

        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += grades[i];
        }
        return sum / size;
    }

    // Get highest grade
    public int getHighestGrade() {
        if (size == 0) {
            throw new IllegalStateException("No grades available");
        }

        int max = grades[0];
        for (int i = 1; i < size; i++) {
            if (grades[i] > max) {
                max = grades[i];
            }
        }
        return max;
    }

    // Get lowest grade
    public int getLowestGrade() {
        if (size == 0) {
            throw new IllegalStateException("No grades available");
        }

        int min = grades[0];
        for (int i = 1; i < size; i++) {
            if (grades[i] < min) {
                min = grades[i];
            }
        }
        return min;
    }

    // Get all grades (returns a copy)
    public int[] getAllGrades() {
        return Arrays.copyOf(grades, size);
    }

    // Get grade at specific index
    public int getGradeAt(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index: " + index);
        }
        return grades[index];
    }

    // Get number of grades
    public int getGradeCount() {
        return size;
    }

    // Clear all grades
    public void clearGrades() {
        size = 0;
    }
}