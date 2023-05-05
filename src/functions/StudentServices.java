package functions;

import constants.*;
import models.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class StudentServices {
    public static List<Student> students = new ArrayList<>();

    public static Scanner scanner = Constants.SCANNER;

    public void printAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void createStudent() {
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
            System.out.print("Enter student's date of birth ( " + Constants.DATE_FORMAT + " ): ");
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

        DateTimeFormatter dateOfBirthFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
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
    }


    public void findStudentById() {
        if (students.isEmpty()) {
            System.out.println("StudentList is empty, Please insert student first");
            return;
        }
        System.out.println("Enter id to find student: ");
        String idString = scanner.nextLine().trim();
        getStudent(idString);
    }

    private Student getStudent(String idString) {
        int id;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID entered. Please enter a valid integer ID.");
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


    public void updateStudent() {
        if (isStudentListEmpty()) {
            System.out.println("StudentList is empty");
            return;
        }
        System.out.print("Enter ID to update student: ");
        String idString = scanner.nextLine().trim();

        Student studentToUpdate = getStudent(idString);
        if (studentToUpdate == null) return;

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
            System.out.print("Date of Birth (" + Constants.DATE_FORMAT + ") (" + studentToUpdate.getDateOfBirth().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)) + "): ");
            String dobString = scanner.nextLine().trim();
            if (dobString.isEmpty()) {
                System.out.println("Skipping Date of Birth field...");
                break;
            } else if (Validator.validateDateOfBirth(dobString)) {
                LocalDate dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
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

        System.out.println("Student's records updated!");
        System.out.println(studentToUpdate);
    }


    public void promptForDeleteStudent() {
        if (isStudentListEmpty()) {
            System.out.println("StudentList is empty");
            return;
        }
        System.out.print("Enter ID to delete student: ");
        String deleteIdString = scanner.nextLine();

        int idToDelete;
        try {
            idToDelete = Integer.parseInt(deleteIdString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID entered. Please enter a valid integer ID.");
            return;
        }

        Student studentToDelete = getStudent(deleteIdString);
        if (studentToDelete == null) {
            System.out.println("Student with ID " + idToDelete + " not found");
            return;
        }

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
        if (isStudentListEmpty()) {
            System.out.println("StudentList is empty");
            return;
        }
        PerformanceLevel[] levels = PerformanceLevel.values();
        int[] levelCount = new int[levels.length];
        for (Student student : students) {
            levelCount[student.getPerformanceLevel().ordinal()]++;
        }

        double totalStudents = students.size();
        System.out.println("Performance level percentage (from lowest to highest)");

        for (int i = 0; i < levels.length; i++) {
            double percentage = levelCount[i] / totalStudents * 100;
            System.out.printf("%s: %.2f%%\n", levels[i], percentage);
        }
    }


    public void printPercentageOfGPA() {
        if (isStudentListEmpty()) {
            System.out.println("StudentList is empty");
            return;
        }

        List<Float> gpaOfStudentList = students.stream()
                .map(Student::getGPA)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("GPA percentage (from lowest to highest)");
        for (Float gpa : gpaOfStudentList) {
            long count = students.stream()
                    .filter(student -> student.getGPA().equals(gpa))
                    .count();
            double percentage = count / (double) students.size() * 100;
            System.out.printf("%.1f: %.2f%%\n", gpa, percentage);
        }
    }

    public void printStudentsByPerformanceLevel() {
        if (isStudentListEmpty()) {
            System.out.println("StudentList is empty");
            return;
        }
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

    public boolean isStudentListEmpty() {
        return students.isEmpty();
    }

    public void writeStudentsToFile() {
        if (isStudentListEmpty()) {
            System.out.println("StudentList is empty");
            return;
        }

        String fileName = Constants.STUDENT_STORAGE_FILE;
        try (FileWriter fileWriter = new FileWriter(fileName, false);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            System.out.println("Saving to " + fileName + " .......");
            for (Student student : students) {
                bufferedWriter.write(student.toString());
                bufferedWriter.newLine();
            }
            System.out.println("Students saved");

        } catch (IOException exception) {
            System.out.println("Error: Unable to save data to " + fileName);
            File dataFolder = new File(Constants.DATA_STORAGE_FOLDER);
            if (!dataFolder.exists()) {
                createDataFolderThenSave();
            } else {
                exception.printStackTrace();
            }
        }
    }


    private void createDataFolderThenSave() {
        System.out.println("The '" + Constants.DATA_STORAGE_FOLDER + "' folder does not exist.");
        System.out.print("Do you want to create it? ( Y = yes/ else = no ): ");
        String response = scanner.nextLine().toLowerCase();
        if (!response.equals("y")) {
            System.out.println("Students were not saved.");
            return;
        }
        boolean success = new File(Constants.DATA_STORAGE_FOLDER).mkdirs();
        if (!success) {
            System.out.println("Failed to create '" + Constants.DATA_STORAGE_FOLDER + "' folder.");
            return;
        }
        System.out.println("Created '" + Constants.DATA_STORAGE_FOLDER + "' folder.");
        writeStudentsToFile();
    }

    public void closeScanner() {
        scanner.close();
    }

}

