import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

public class ConsoleInputOutput {

    public static Student[] students = new Student[3];
    public void ConsoleDisplay() {
        char userInput;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Please choose interaction with student : [C]create, [R]read, [U]update, [D]delete, or [q]quit: ");
                userInput = scanner.next().charAt(0);
                userInput = Character.toLowerCase(userInput);
                switch (userInput) {
                    case 'c':
                        CreateStudent();
                        break;
                    case 'r':
                        System.out.print("Enter ID to find student: ");
                        String idString = scanner.next();
                        FindStudentById(idString);
                        break;
                    case 'u':
                        break;
                    case 'd':
                        System.out.print("Enter ID to delete student: ");
                        String deleteIdString = scanner.next();
                        promptForDeleteStudent(deleteIdString);
                        break;
                    case 'q':
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
                continuePrompt(scanner);
            }
        }
    }



    public static void continuePrompt(Scanner scanner) {
        System.out.print("Do you want to continue? (y = yes, else = exit): ");
        char userInput = scanner.next().toLowerCase().charAt(0);
        if (userInput != 'y') {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    public void CreateStudent() {
        Scanner scanner = new Scanner(System.in);

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
        Student student = new Student(
                name,
                LocalDate.parse(dateOfBirth, dateOfBirthFormatter),
                address,
                Float.valueOf(height),
                Float.valueOf(weight),
                studentId,
                university,
                Integer.valueOf(startYear),
                Float.valueOf(GPA)
        );
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
        System.out.println(student);
        System.out.println("Student created!");
    }


    public void FindStudentById(String idString) {
        int id;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID entered. Please enter a valid integer ID.");
            return;
        }

        if (students[0] == null) {
            System.out.println("Please insert student first");
            return;
        }

        for (Student student : students) {
            if (student == null) break;
            if (Objects.equals(student.getId(), id)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with ID = " + id + " not found");
    }



    public void promptForDeleteStudent(String idString) {
        int inputId;
        try {
            inputId = Integer.parseInt(idString);
            if (students[0] == null) {
                System.out.println("Please insert student first");
                return;
            }
            for (int index=0; index < students.length; index++) {
                if (students[index] == null)
                    break;
                if (Objects.equals(students[index].getId(), inputId)) {
                    System.out.print("Do you want to delete student with id = " + inputId + " (y = yes, else = exit): ");
                    Scanner scanner = new Scanner(System.in);
                    char userInput = scanner.next().toLowerCase().charAt(0);
                    if (userInput == 'y') {
                        confirmDeleteStudent(index);
                        return;
                    }
                    System.out.println("Canceled");
                    return;
                }
            }
            System.out.println("student with inputId = " + inputId + " not found ");
        } catch (NumberFormatException exception) {
            System.out.println("Please enter integer only");
        }
    }


    public void confirmDeleteStudent(int indexOfStudent) {
        String oldStudentId = students[indexOfStudent].getStudentId();
        for (int j = indexOfStudent; j < students.length - 1; j++) {
            students[j] = students[j + 1];
            if (students[j] == null)
                break;
            int oldId = students[j].getId();
            students[j].setId(oldId - 1);
        }
        //handle delete last elements
        if (indexOfStudent == students.length - 1) {
            students[indexOfStudent] = null;
        }

        Student.removeIdInAssignedIds(oldStudentId);
        Student.DecreaseAutoIncrementId();
        System.out.println("Student Deleted");
    }


    private static String getInput(Scanner scanner, String fieldName, String currentValue, Function<String, Boolean> validator) {
        String input = "";
        boolean isValid;
        do {
            System.out.print(fieldName + " (" + currentValue + "): ");
            input = scanner.nextLine().trim();
            isValid = input.isEmpty() || validator.apply(input);
            if (!isValid) {
                System.out.println("Invalid input!");
            }
        } while (!isValid);
        return input;
    }



}
