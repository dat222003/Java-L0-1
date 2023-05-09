package functions;

import constants.*;
import models.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private String getStudentName() {
        String name;
        do {
            System.out.print("Enter student's name: ");
            name = scanner.nextLine();
        } while (!Validator.validateName(name));
        return name;
    }

    private LocalDate getStudentDOB() {
        String dateOfBirth;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        do {
            System.out.print("Enter student's date of birth ( " + Constants.DATE_FORMAT + " ): ");
            dateOfBirth = scanner.nextLine();
            try {
                LocalDate parsedDate = LocalDate.parse(dateOfBirth, dateFormatter);
                System.out.println(parsedDate);
                if (Validator.validateDateOfBirth(dateOfBirth, parsedDate))
                    return parsedDate;
            } catch (DateTimeParseException e) {
                System.out.println("Date of birth is not in the correct format.");
            }
        } while (true);
    }


    private String getStudentAddress() {
        String address;
        do {
            System.out.print("Enter student's address: ");
            address = scanner.nextLine();
        } while (!Validator.validateAddress(address));
        return address;
    }

    private float getStudentHeight() {
        String height;
        do {
            System.out.print("Enter student's height: ");
            height = scanner.nextLine();
            try {
                float heightInFloat = Float.parseFloat(height);
                if (Validator.validateHeight(heightInFloat))
                    return heightInFloat;
            } catch (NumberFormatException e) {
                System.out.println("Invalid height. Please enter a valid number.");
            }
        } while (true);
    }

    private float getStudentWeight() {
        String weight;
        do {
            System.out.print("Enter student's weight: ");
            weight = scanner.nextLine();
            try {
                float weightInFloat = Float.parseFloat(weight);
                if (Validator.validateWeight(weightInFloat))
                    return weightInFloat;
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight. Please enter a valid number.");
            }
        } while (true);
    }

    private String getStudentID() {
        String studentId;
        do {
            System.out.print("Enter student's ID: ");
            studentId = scanner.nextLine();
        } while (!Validator.validateStudentId(studentId));
        return studentId;
    }

    private String getStudentUniversity() {
        String university;
        do {
            System.out.print("Enter student's university: ");
            university = scanner.nextLine();
        } while (!Validator.validateUniversity(university));
        return university;
    }

    private int getStudentStartYear() {
        String startYearStr;
        int startYear;
        do {
            System.out.print("Enter student's start year: ");
            startYearStr = scanner.nextLine().trim();
            try {
                startYear = Integer.parseInt(startYearStr);
                if (Validator.validateStartYear(startYear))
                    return startYear;
            } catch (NumberFormatException e) {
                System.out.println("Start year must be a number only.");
            }
        } while (true);
    }


    private float getStudentGPA() {
        String GPA;
        do {
            System.out.print("Enter student's GPA: ");
            GPA = scanner.nextLine();
            try {
                float gpaValue = Float.parseFloat(GPA);
                if (Validator.validateGPA(gpaValue))
                    return gpaValue;
            } catch (NumberFormatException e) {
                System.out.println("Invalid GPA format! GPA must be a number.");
            }
        } while (true);
    }


    public void createStudent() {
        String name = getStudentName();
        LocalDate dateOfBirth = getStudentDOB();
        String address = getStudentAddress();
        float height = getStudentHeight();
        float weight = getStudentWeight();
        String studentId = getStudentID();
        String university = getStudentUniversity();
        int startYear = getStudentStartYear();
        float gpa = getStudentGPA();

        Student student = new Student(name,
                dateOfBirth,
                address,
                height,
                weight,
                studentId,
                university,
                startYear,
                gpa);

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

        updateName(studentToUpdate);
        updateDateOfBirth(studentToUpdate);
        updateAddress(studentToUpdate);
        updateUniversity(studentToUpdate);
        updateStartYear(studentToUpdate);
        updateHeight(studentToUpdate);
        updateWeight(studentToUpdate);
        updateGPA(studentToUpdate);


        System.out.println("Student's records updated!");
        System.out.println(studentToUpdate);
    }

    private void updateName(Student studentToUpdate) {
        while (true) {
            System.out.print("Name (" + studentToUpdate.getName() + "): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Skipping Name field...");
                break;
            }
            if (Validator.validateName(name)) {
                studentToUpdate.setName(name);
                break;
            }
        }
    }

    private void updateDateOfBirth(Student studentToUpdate) {
        while (true) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
            System.out.print("Date of Birth (" + Constants.DATE_FORMAT + ") (" + studentToUpdate.getDateOfBirth().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)) + "): ");
            String dobString = scanner.nextLine().trim();
            if (dobString.isEmpty()) {
                System.out.println("Skipping Date of Birth field...");
                break;
            }
            try {
                LocalDate parsedDate = LocalDate.parse(dobString, dateFormatter);
                LocalDate dateOfBirth = LocalDate.parse(dobString, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
                if (Validator.validateDateOfBirth(dobString, parsedDate)) {
                    studentToUpdate.setDateOfBirth(dateOfBirth);
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Date of birth is not in the correct format.");
            }
        }
    }

    private void updateAddress(Student studentToUpdate) {
        while (true) {
            System.out.print("Address (" + studentToUpdate.getAddress() + "): ");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Skipping Address field...");
                break;
            }
            if (Validator.validateAddress(address)) {
                studentToUpdate.setAddress(address);
                break;
            }
        }
    }

    private void updateUniversity(Student studentToUpdate) {
        while (true) {
            System.out.print("University (" + studentToUpdate.getUniversity() + "): ");
            String university = scanner.nextLine().trim();
            if (university.isEmpty()) {
                System.out.println("Skipping University field...");
                break;
            }
            if (Validator.validateUniversity(university)) {
                studentToUpdate.setUniversity(university);
                break;
            }
        }
    }

    private void updateStartYear(Student studentToUpdate) {
        while (true) {
            System.out.print("Start Year (" + studentToUpdate.getStartYear() + "): ");
            String startYearString = scanner.nextLine().trim();
            if (startYearString.isEmpty()) {
                System.out.println("Skipping Start Year field...");
                break;
            }
            try {
                Integer startYear = Integer.parseInt(startYearString);
                if (Validator.validateStartYear(startYear)) {
                    studentToUpdate.setStartYear(startYear);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Start year must be a number only.");
            }
        }
    }

    private void updateHeight(Student studentToUpdate) {
        while (true) {
            System.out.print("Height (" + studentToUpdate.getHeight() + "): ");
            String heightString = scanner.nextLine().trim();
            if (heightString.isEmpty()) {
                System.out.println("Skipping Height field...");
                break;
            }
            try {
                float heightInFloat = Float.parseFloat(heightString);
                if (Validator.validateHeight(heightInFloat)) {
                    studentToUpdate.setHeight(heightInFloat);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid height. Please enter a valid number.");
            }
        }
    }

    private void updateWeight(Student studentToUpdate) {
        while (true) {
            System.out.print("Weight (" + studentToUpdate.getWeight() + "): ");
            String weightString = scanner.nextLine().trim();
            if (weightString.isEmpty()) {
                System.out.println("Skipping Weight field...");
                break;
            }
            try {
                float weightInFloat = Float.parseFloat(weightString);
                if (Validator.validateWeight(weightInFloat)) {
                    studentToUpdate.setWeight(weightInFloat);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight. Please enter a valid number.");
            }
        }
    }

    private void updateGPA(Student studentToUpdate) {
        while (true) {
            System.out.print("GPA (" + studentToUpdate.getGPA() + "): ");
            String GPAString = scanner.nextLine().trim();
            if (GPAString.isEmpty()) {
                System.out.println("Skipping GPA field...");
                break;
            }
            try {
                float gpaValue = Float.parseFloat(GPAString);
                if (Validator.validateGPA(gpaValue)) {
                    float GPA = Float.parseFloat(GPAString);
                    studentToUpdate.setGPA(GPA);
                    studentToUpdate.setPerformanceLevel(calculatePerformanceLevel(GPA));
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid GPA format! GPA must be a number.");
            }
        }
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

