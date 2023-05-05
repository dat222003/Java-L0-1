package functions;

import java.util.*;

public class ConsoleMenu {

    StudentServices studentServices = new StudentServices();
    public void consoleDisplay() {
        char userInput;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Please choose interaction with student : [C]create, [R]read, [U]update, [D]delete, [S]Save or [q]quit: ");
                userInput = scanner.next().charAt(0);
                userInput = Character.toLowerCase(userInput);
                scanner.nextLine();
                switch (userInput) {
                    case 'c':
                        studentServices.createStudent();
                        break;
                    case 'r':
                        displayReadFunctions();
                        break;
                    case 'u':
                        System.out.print("Enter ID to update student: ");
                        String updateIdString = scanner.nextLine();
                        studentServices.updateStudent(updateIdString);
                        break;
                    case 'd':
                        System.out.print("Enter ID to delete student: ");
                        String deleteIdString = scanner.nextLine();
                        studentServices.promptForDeleteStudent(deleteIdString);
                        break;
                    case 's':
                        studentServices.writeStudentsToFile();
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
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        }
    }

    public void displayReadFunctions() {
        if (studentServices.isStudentListEmpty()) {
            System.out.println("Please insert student first");
            return;
        }
        System.out.print("1: to find student with id\n" +
                "2: to display student's performance level in percentage\n" +
                "3: to display student's GPA in percentage\n" +
                "4: find students with performance level\n");

        Scanner scanner = new Scanner(System.in);
        char choice = scanner.next().charAt(0);
        choice = Character.toLowerCase(choice);
        scanner.nextLine();
        switch (choice) {
            case '1':
                System.out.println("Enter id to find student: ");
                String idString = scanner.nextLine();
                studentServices.findStudentById(idString);
                break;
            case '2':
                studentServices.printPercentageOfPerformanceLevel();
                break;
            case '3':
                studentServices.printPercentageOfGPA();
                break;
            case '4':
                studentServices.printStudentByPerformanceLevel();
                break;
            default:
                System.out.println("Invalid Input");
        }
    }
}