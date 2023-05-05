package functions;

import constants.Constants;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMenu {

    private final StudentServices studentServices;

    public ConsoleMenu() {
        studentServices = new StudentServices();
    }

    public void consoleDisplay() {
        try (Scanner scanner = Constants.SCANNER) {
            while (true) {
                System.out.print("Please choose an option:\n" +
                        "1. Create a student\n" +
                        "2. Find student by ID\n" +
                        "3. Display student's performance level in percentage\n" +
                        "4. Display student's GPA in percentage\n" +
                        "5. Find students by performance level\n" +
                        "6. Update student information\n" +
                        "7. Delete a student\n" +
                        "8. Get all students created\n" +
                        "0. Save and quit\n" +
                        "Your choice: ");
                try {
                    int userInput = scanner.nextInt();
                    scanner.nextLine();
                    //Got error when using enhanced switch: Enhanced 'switch' blocks are not supported at language level '8'
                    switch (userInput) {
                        case 1:
                            studentServices.createStudent();
                            break;
                        case 2:
                            studentServices.findStudentById();
                            break;
                        case 3:
                            studentServices.printPercentageOfPerformanceLevel();
                            break;
                        case 4:
                            studentServices.printPercentageOfGPA();
                            break;
                        case 5:
                            studentServices.printStudentsByPerformanceLevel();
                            break;
                        case 6:
                            studentServices.updateStudent();
                            break;
                        case 7:
                            studentServices.promptForDeleteStudent();
                            break;
                        case 8:
                            studentServices.printAllStudents();
                            break;
                        case 0:
                            saveAndQuit();
                        default:
                            System.out.println("Invalid input! Must be from 0 -> 8");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Must be a number");
                    scanner.nextLine();
                }
                System.out.print("Do you want to continue? (y = yes, else = exit): ");
                String input = scanner.nextLine().trim();
                if (!input.equalsIgnoreCase("y")) {
                    saveAndQuit();
                }
            }
        }
    }


    public void saveAndQuit() {
        studentServices.writeStudentsToFile();
        studentServices.closeScanner();
        System.out.println("Exiting...");
        System.exit(0);
    }
}
