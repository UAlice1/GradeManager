public class Main {
    public static void main(String[] args) {
        // Test with valid and invalid inputs
        GradeManager manager = new GradeManager(5);

        // Test adding valid grades
        try {
            manager.addGrade(85);
            manager.addGrade(92);
            manager.addGrade(78);
            manager.addGrade(90);
            manager.addGrade(88);
            System.out.println("Added 5 grades successfully");
        } catch (InvalidGradeException e) {
            System.out.println("Error adding grade: " + e.getMessage());
        }

        // Test adding invalid grade
        try {
            manager.addGrade(105);
            System.out.println("Added grade 105 (this shouldn't happen)");
        } catch (InvalidGradeException e) {
            System.out.println("Correctly caught invalid grade: " + e.getMessage());
        }

        // Test array operations
        testGradeOperations(manager);

        // Test with empty manager
        testEmptyManager();

        // Test edge cases
        testEdgeCases();
    }

    private static void testGradeOperations(GradeManager manager) {
        System.out.println("\n=== Testing Grade Operations ===");

        // Display all grades
        System.out.println("Current grades: " + arrayToString(manager.getAllGrades()));

        // Calculate average
        try {
            double average = manager.calculateAverage();
            System.out.printf("Average grade: %.2f\n", average);
        } catch (IllegalStateException e) {
            System.out.println("Error calculating average: " + e.getMessage());
        }

        // Get highest and lowest
        try {
            System.out.println("Highest grade: " + manager.getHighestGrade());
            System.out.println("Lowest grade: " + manager.getLowestGrade());
        } catch (IllegalStateException e) {
            System.out.println("Error getting highest/lowest: " + e.getMessage());
        }

        // Sort grades
        manager.sortGrades();
        System.out.println("Sorted grades: " + arrayToString(manager.getAllGrades()));

        // Search for grades
        int searchGrade = 90;
        int index = manager.searchGrade(searchGrade);
        if (index != -1) {
            System.out.println("Found grade " + searchGrade + " at index " + index);
        } else {
            System.out.println("Grade " + searchGrade + " not found");
        }

        // Test getting grade at index
        try {
            int testIndex = 2;
            System.out.println("Grade at index " + testIndex + ": " + manager.getGradeAt(testIndex));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error accessing grade: " + e.getMessage());
        }

        // Test invalid index
        try {
            int invalidIndex = 10;
            System.out.println("Grade at index " + invalidIndex + ": " + manager.getGradeAt(invalidIndex));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Correctly caught invalid index: " + e.getMessage());
        }
    }

    private static void testEmptyManager() {
        System.out.println("\n=== Testing Empty Manager ===");
        GradeManager emptyManager = new GradeManager();

        try {
            System.out.println("Average grade: " + emptyManager.calculateAverage());
        } catch (IllegalStateException e) {
            System.out.println("Correctly caught empty manager for average: " + e.getMessage());
        }

        try {
            System.out.println("Highest grade: " + emptyManager.getHighestGrade());
        } catch (IllegalStateException e) {
            System.out.println("Correctly caught empty manager for highest grade: " + e.getMessage());
        }

        try {
            System.out.println("Grade at index 0: " + emptyManager.getGradeAt(0));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Correctly caught empty manager for index access: " + e.getMessage());
        }
    }

    private static void testEdgeCases() {
        System.out.println("\n=== Testing Edge Cases ===");

        // Test constructor with invalid capacity
        try {
            GradeManager invalidManager = new GradeManager(-5);
            System.out.println("Created manager with negative capacity (this shouldn't happen)");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly caught invalid constructor argument: " + e.getMessage());
        }

        // Test grade boundary values
        GradeManager boundaryManager = new GradeManager();
        try {
            boundaryManager.addGrade(0);  // Minimum valid grade
            boundaryManager.addGrade(100); // Maximum valid grade
            System.out.println("Added boundary grades (0 and 100) successfully");
        } catch (InvalidGradeException e) {
            System.out.println("Error adding boundary grades: " + e.getMessage());
        }

        // Test invalid grades
        try {
            boundaryManager.addGrade(-1);
            System.out.println("Added grade -1 (this shouldn't happen)");
        } catch (InvalidGradeException e) {
            System.out.println("Correctly caught invalid grade -1: " + e.getMessage());
        }

        try {
            boundaryManager.addGrade(101);
            System.out.println("Added grade 101 (this shouldn't happen)");
        } catch (InvalidGradeException e) {
            System.out.println("Correctly caught invalid grade 101: " + e.getMessage());
        }
    }

    private static String arrayToString(int[] array) {
        if (array == null || array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}