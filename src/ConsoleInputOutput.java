import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

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
                        System.out.print("Enter ID to delete student: ");
                        String updateIdString = scanner.next();
                        updateStudent(updateIdString);
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
                continuePrompt();
            }
        }
    }


    public void continuePrompt() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Do you want to continue? (y = yes, else = exit): ");
            String input = scanner.nextLine().trim();

            if (!input.equalsIgnoreCase("y")) {
                System.out.println("Exiting...");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading user input: " + e.getMessage());
            System.exit(1);
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
                LocalDate.parse(dateOfBirth,
                dateOfBirthFormatter),
                address, Float.valueOf(height),
                Float.valueOf(weight), studentId,
                university, Integer.valueOf(startYear),
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


    public Student FindStudentById(String idString) {
        int id;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID entered. Please enter a valid integer ID.");
            return null;
        }

        if (students[0] == null) {
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

    private int findIndexOfStudentById(int id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }


    public void updateStudent(String idString) {
        Student studentToUpdate = FindStudentById(idString);
        if (studentToUpdate == null) {
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter fields to update (Press Enter to remain old value):");

            updateName(studentToUpdate, scanner);

            updateAddress(studentToUpdate, scanner);

            updateGPA(studentToUpdate, scanner);

            updateUniversity(studentToUpdate, scanner);

            updateStartYear(studentToUpdate, scanner);

            updateHeight(studentToUpdate, scanner);

            updateWeight(studentToUpdate, scanner);

            System.out.println("Student record updated!");
            System.out.println(studentToUpdate);
        }
    }

    private void updateName(Student student, Scanner scanner) {
        while (true) {
            System.out.print("Name (" + student.getName() + "): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Skipping Name field...");
                break;
            } else if (Validator.validateName(name)) {
                student.setName(name);
                break;
            }
        }
    }

    private void updateAddress(Student student, Scanner scanner) {
        while (true) {
            System.out.print("Address (" + student.getAddress() + "): ");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Skipping Address field...");
                break;
            } else if (Validator.validateAddress(address)) {
                student.setAddress(address);
                break;
            }
        }
    }

    private void updateGPA(Student student, Scanner scanner) {
        while (true) {
            System.out.print("GPA (" + student.getGPA() + "): ");
            String GPAString = scanner.nextLine().trim();
            if (GPAString.isEmpty()) {
                System.out.println("Skipping GPA field...");
                break;
            } else if (Validator.validateGPA(GPAString)) {
                float GPA = Float.parseFloat(GPAString);
                student.setGPA(GPA);
                break;
            }
        }
    }

    private void updateUniversity(Student student, Scanner scanner) {
        while (true) {
            System.out.print("University (" + student.getUniversity() + "): ");
            String university = scanner.nextLine().trim();
            if (university.isEmpty()) {
                System.out.println("Skipping University field...");
                break;
            } else if (Validator.validateUniversity(university)) {
                student.setUniversity(university);
                break;
            }
        }
    }

    private void updateStartYear(Student student, Scanner scanner) {
        while (true) {
            System.out.print("Start Year (" + student.getStartYear() + "): ");
            String startYearString = scanner.nextLine().trim();
            if (startYearString.isEmpty()) {
                System.out.println("Skipping Start Year field...");
                break;
            } else if (Validator.validateStartYear(startYearString)) {
                int startYear = Integer.parseInt(startYearString);
                student.setStartYear(startYear);
                break;
            }
        }
    }

    private void updateHeight(Student student, Scanner scanner) {
        while (true) {
            System.out.print("Height (" + student.getHeight() + "): ");
            String heightString = scanner.nextLine().trim();
            if (heightString.isEmpty()) {
                System.out.println("Skipping Height field...");
                break;
            } else if (Validator.validateHeight(heightString)) {
                float height = Float.parseFloat(heightString);
                student.setHeight(height);
                break;
            }
        }
    }

    private void updateWeight(Student student, Scanner scanner) {
        while (true) {
            System.out.print("Weight (" + student.getWeight() + "): ");
            String weightString = scanner.nextLine().trim();
            if (weightString.isEmpty()) {
                System.out.println("Skipping Weight field...");
                break;
            } else if (Validator.validateWeight(weightString)) {
                float weight = Float.parseFloat(weightString);
                student.setWeight(weight);
                break;
            }
        }
    }


    public void promptForDeleteStudent(String idString) {
        int idToDelete;
        try {
            idToDelete = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID entered. Please enter a valid integer ID.");
            return;
        }

        Student studentToDelete = FindStudentById(idString);
        if (studentToDelete == null) {
            System.out.println("Student with ID " + idToDelete + " not found");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to delete student with ID = " + idToDelete + " (y = yes, any other key = cancel): ");
        char userInput = scanner.next().toLowerCase().charAt(0);
        if (userInput == 'y') {
            confirmDeleteStudent(findIndexOfStudentById(idToDelete));
        } else {
            System.out.println("Canceled");
        }
    }


    public void confirmDeleteStudent(int indexOfStudent) {
        String oldStudentId = students[indexOfStudent].getStudentId();
        for (int j = indexOfStudent; j < students.length - 1; j++) {
            students[j] = students[j + 1];
            if (students[j] == null) break;
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


}
