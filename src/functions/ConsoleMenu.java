package functions;

import constants.PerformanceLevel;
import models.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ConsoleMenu {

    public static List<Student> students = new ArrayList<>();

    public void consoleDisplay() {
        char userInput;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Please choose interaction with student : [C]create, [R]read, [U]update, [D]delete, or [q]quit: ");
                userInput = scanner.next().charAt(0);
                userInput = Character.toLowerCase(userInput);
                scanner.nextLine();
                switch (userInput) {
                    case 'c':
                        createStudent(scanner);
                        break;
                    case 'r':
                        displayReadFunctions();
                        break;
                    case 'u':
                        System.out.print("Enter ID to update student: ");
                        String updateIdString = scanner.nextLine();
                        updateStudent(updateIdString);
                        break;
                    case 'd':
                        System.out.print("Enter ID to delete student: ");
                        String deleteIdString = scanner.nextLine();
                        promptForDeleteStudent(deleteIdString);
                        break;
                    case 'q':
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
                System.out.print("Do you want to continue? (y = yes, else = exit): ");
                String input = scanner.nextLine().trim();
                if (!input.equalsIgnoreCase("y")) {
                    System.out.println(input);
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        }
    }

    public void displayReadFunctions() {
        Scanner scanner = new Scanner(System.in);
        if (students.isEmpty()) {
            System.out.println("Please insert student first");
            return;
        }
        System.out.print("1: to find student with id\n" +
                "2: to display student's performance level in percentage\n" +
                "3: to display student's GPA in percentage\n" +
                "4: find students with performance level\n");
        char choice = scanner.next().charAt(0);
        choice = Character.toLowerCase(choice);
        scanner.nextLine();
        switch (choice) {
            case '1':
                System.out.println("Enter id to find student: ");
                String idString = scanner.nextLine();
                findStudentById(idString);
                break;
            case '2':
                printPercentageOfPerformanceLevel();
                break;
            case '3':
                printPercentageOfGPA();
                break;
            case '4':
                printStudentByPerformanceLevel();
                break;
            default:
                System.out.println("Invalid Input");
        }
    }


    public void createStudent(Scanner scanner) {

        String name;
        String dateOfBirth;
        String address;
        String height;
        String weight;
        String studentId;
        String university;
        String startYear;
        String GPA;

        do {
            System.out.print("Enter student's name: ");
            name = scanner.nextLine();
        } while (!Validator.validateName(name));

        do {
            System.out.print("Enter student's date of birth (dd-MM-yyyy): ");
            dateOfBirth = scanner.nextLine();
        } while (!Validator.validateDateOfBirth(dateOfBirth));

        do {
            System.out.print("Enter student's address: ");
            address = scanner.nextLine();
        } while (!Validator.validateAddress(address));

        do {
            System.out.print("Enter student's height: ");
            height = scanner.nextLine();
        } while (!Validator.validateHeight(height));

        do {
            System.out.print("Enter student's weight: ");
            weight = scanner.nextLine();
        } while (!Validator.validateWeight(weight));

        do {
            System.out.print("Enter student's ID: ");
            studentId = scanner.nextLine();
        } while (!Validator.validateStudentId(studentId));

        do {
            System.out.print("Enter student's university: ");
            university = scanner.nextLine();
        } while (!Validator.validateUniversity(university));

        do {
            System.out.print("Enter student's start year: ");
            startYear = scanner.nextLine();
        } while (!Validator.validateStartYear(startYear));

        do {
            System.out.print("Enter student's GPA: ");
            GPA = scanner.nextLine();
        } while (!Validator.validateGPA(GPA));

        DateTimeFormatter dateOfBirthFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Student student = new Student(name,
                LocalDate.parse(dateOfBirth,
                        dateOfBirthFormatter),
                address,
                Float.valueOf(height),
                Float.valueOf(weight),
                studentId,
                university,
                Integer.valueOf(startYear),
                Float.valueOf(GPA));
        student.setPerformanceLevel(
                calculatePerformanceLevel(student.getGPA())
        );
        students.add(student);
        System.out.println(student);
        System.out.println("Student created!");
    }


    public Student findStudentById(String idString) {
        int id;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID entered. Please enter a valid integer ID.");
            return null;
        }

        if (students.isEmpty()) {
            System.out.println("Please insert student first");
            return null;
        }

        for (Student student : students) {
            if (student == null) break;
            if (Objects.equals(student.getId(), id)) {
                System.out.println(student);
                return student;
            }
        }
        System.out.println("Student with ID = " + id + " not found");
        return null;
    }


    public void updateStudent(String idString) {
        Student studentToUpdate = findStudentById(idString);
        if (studentToUpdate == null) return;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter fields to update ( Press Enter to remain old value )");
        while (true) {
            System.out.print("Name (" + studentToUpdate.getName() + "): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Skipping Name field...");
                break;
            } else if (Validator.validateName(name)) {
                studentToUpdate.setName(name);
                break;
            }
        }

        while (true) {
            System.out.print("Date of Birth (dd-MM-yyyy) (" + studentToUpdate.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "): ");
            String dobString = scanner.nextLine().trim();
            if (dobString.isEmpty()) {
                System.out.println("Skipping Date of Birth field...");
                break;
            } else if (Validator.validateDateOfBirth(dobString)) {
                LocalDate dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                studentToUpdate.setDateOfBirth(dob);
                break;
            }
        }

        while (true) {
            System.out.print("Address (" + studentToUpdate.getAddress() + "): ");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Skipping Address field...");
                break;
            } else if (Validator.validateAddress(address)) {
                studentToUpdate.setAddress(address);
                break;
            }
        }


        while (true) {
            System.out.print("University (" + studentToUpdate.getUniversity() + "): ");
            String university = scanner.nextLine().trim();
            if (university.isEmpty()) {
                System.out.println("Skipping University field...");
                break;
            } else if (Validator.validateUniversity(university)) {
                studentToUpdate.setUniversity(university);
                break;
            }
        }

        while (true) {
            System.out.print("Start Year (" + studentToUpdate.getStartYear() + "): ");
            String startYearString = scanner.nextLine().trim();
            if (startYearString.isEmpty()) {
                System.out.println("Skipping Start Year field...");
                break;
            } else if (Validator.validateStartYear(startYearString)) {
                int startYear = Integer.parseInt(startYearString);
                studentToUpdate.setStartYear(startYear);
                break;
            }
        }

        while (true) {
            System.out.print("Height (" + studentToUpdate.getHeight() + "): ");
            String heightString = scanner.nextLine().trim();
            if (heightString.isEmpty()) {
                System.out.println("Skipping Height field...");
                break;
            } else if (Validator.validateHeight(heightString)) {
                float height = Float.parseFloat(heightString);
                studentToUpdate.setHeight(height);
                break;
            }
        }

        while (true) {
            System.out.print("Weight (" + studentToUpdate.getWeight() + "): ");
            String weightString = scanner.nextLine().trim();
            if (weightString.isEmpty()) {
                System.out.println("Skipping Weight field...");
                break;
            } else if (Validator.validateWeight(weightString)) {
                float weight = Float.parseFloat(weightString);
                studentToUpdate.setWeight(weight);
                break;
            }
        }

        while (true) {
            System.out.print("GPA (" + studentToUpdate.getGPA() + "): ");
            String GPAString = scanner.nextLine().trim();
            if (GPAString.isEmpty()) {
                System.out.println("Skipping GPA field...");
                break;
            } else if (Validator.validateGPA(GPAString)) {
                float GPA = Float.parseFloat(GPAString);
                studentToUpdate.setGPA(GPA);
                studentToUpdate.setPerformanceLevel(calculatePerformanceLevel(GPA));
                break;
            }
        }

        System.out.println("Student record updated!");
        System.out.println(studentToUpdate);
    }


    public void promptForDeleteStudent(String idString) {
        int idToDelete;
        try {
            idToDelete = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID entered. Please enter a valid integer ID.");
            return;
        }

        Student studentToDelete = findStudentById(idString);
        if (studentToDelete == null) {
            System.out.println("Student with ID " + idToDelete + " not found");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to delete student with ID = " + idToDelete + " (y = yes, any other key = cancel): ");
        char userInput = scanner.nextLine().toLowerCase().charAt(0);
        if (userInput == 'y') {
            confirmDeleteStudent(idToDelete);
            return;
        }
        System.out.println("Canceled");
    }


    public void confirmDeleteStudent(int idToDelete) {
        boolean isStudentRemoved = students.removeIf(student -> student.getId() == idToDelete);

        if (!isStudentRemoved) {
            System.out.println("Error: Student with ID " + idToDelete + " not found in list");
            return;
        }

        int index = 1;
        for (Student student : students) {
            student.setId(index++);
        }

        Student.removeIdInAssignedIds(String.valueOf(idToDelete));
        Student.DecreaseAutoIncrementId();
        System.out.println("Student Deleted");
    }


    public PerformanceLevel calculatePerformanceLevel(double gpa) {
        if (gpa < 3) return PerformanceLevel.KEM;
        if (gpa < 5) return PerformanceLevel.YEU;
        if (gpa < 6.5) return PerformanceLevel.TRUNG_BINH;
        if (gpa < 7.5) return PerformanceLevel.KHA;
        if (gpa < 9) return PerformanceLevel.GIOI;

        return PerformanceLevel.XUAT_SAC;
    }

    public void printPercentageOfPerformanceLevel() {
        Map<PerformanceLevel, Integer> levelCount = new HashMap<>();
        for (PerformanceLevel level : PerformanceLevel.values()) {
            levelCount.put(level, 0);
        }
        for (Student student : students) {
            PerformanceLevel level = student.getPerformanceLevel();
            levelCount.put(level, levelCount.get(level) + 1);
        }

        Map<PerformanceLevel, Double> performancePercentage = new HashMap<>();
        for (PerformanceLevel level : PerformanceLevel.values()) {
            int count = levelCount.get(level);
            double percentage = ((double) count / students.size()) * 100;
            performancePercentage.put(level, percentage);
        }
        List<Map.Entry<PerformanceLevel, Double>> sortedLevelPercentages = new ArrayList<>(performancePercentage.entrySet());
        sortedLevelPercentages.sort(Map.Entry.<PerformanceLevel, Double>comparingByValue().reversed());
        System.out.println("Performance level percentage: ");
        for (Map.Entry<PerformanceLevel, Double> entry : sortedLevelPercentages) {
            System.out.printf("%s: %.2f%%\n", entry.getKey(), entry.getValue());
        }

    }

    public void printPercentageOfGPA() {
        Set<Float> GPASet = new HashSet<>();
        for (Student student : students) {
            GPASet.add(student.getGPA());
        }
        Map<Float, Integer> GPACount = new HashMap<>();
        for (Float GPA : GPASet) {
            GPACount.put(GPA, 0);
        }
        for (Student student : students) {
            Float GPA = student.getGPA();
            GPACount.put(GPA, GPACount.get(GPA) + 1);
        }
        Map<Float, Double> GPAPercentage = new HashMap<>();
        for (Float GPA : GPASet) {
            int count = GPACount.get(GPA);
            double percentage = ((double) count / students.size()) * 100;
            GPAPercentage.put(GPA, percentage);
        }
        List<Map.Entry<Float, Double>> sortedGPAPercentage = new ArrayList<>(GPAPercentage.entrySet());
        sortedGPAPercentage.sort(Map.Entry.comparingByValue());
        System.out.println("GPA percentage (from lowest to highest):");
        for (Map.Entry<Float, Double> entry : sortedGPAPercentage) {
            System.out.printf("%s: %.2f%%\n", entry.getKey(), entry.getValue());
        }
    }

    public void printStudentByPerformanceLevel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find students by performance level" + Arrays.toString(PerformanceLevel.values()) + ": ");
        String userInput = scanner.nextLine().toUpperCase();
        PerformanceLevel level;
        try {
            level = PerformanceLevel.valueOf(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Input Invalid! Not a valid performance level");
            return;
        }
        boolean isStudentFound = false;
        for (Student student : students) {
            if (student.getPerformanceLevel() == level) {
                System.out.println(student);
                isStudentFound = true;
            }
        }
        if (!isStudentFound) System.out.println("No student found with given performance level");
    }

}
