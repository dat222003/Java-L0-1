package functions;

import constants.Constants;
import models.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Validator {
    public static boolean validateName(String name) {
        if (name == null || name.length() > Constants.MAX_NAME_LENGTH) {
            System.out.println("Name must be less than " + Constants.MAX_NAME_LENGTH + ".");
            return false;
        }
        return true;
    }


    public static boolean validateDateOfBirth(String dateOfBirth, LocalDate dateOfBirthParsed) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        if (!dateOfBirth.equals(dateOfBirthParsed.format(dateFormatter))) {
            System.out.println("Date of birth must be a valid calendar date.");
            return false;
        }
        if (dateOfBirthParsed.isBefore(Constants.MIN_DATE)) {
            System.out.println("Date of birth must be after " + Constants.MIN_DATE + ".");
            return false;
        }
        if (dateOfBirthParsed.isAfter(LocalDate.now())) {
            System.out.println("Date of birth must be before now.");
            return false;
        }
        return true;
    }


    public static boolean validateAddress(String address) {
        if (address == null || address.length() > Constants.MAX_ADDRESS_LENGTH) {
            System.out.println("Address must be less than " + Constants.MAX_ADDRESS_LENGTH + " characters.");
            return false;
        }
        return true;
    }


    public static boolean validateHeight(Float height) {
        if (height < Constants.MIN_HEIGHT) {
            System.out.println("Height must be greater than " + Constants.MIN_HEIGHT);
            return false;
        }
        if (height > Constants.MAX_HEIGHT) {
            System.out.println("Height must be smaller than " + Constants.MAX_HEIGHT);
            return false;
        }
        return true;
    }

    public static boolean validateWeight(Float weight) {
        if (weight < Constants.MIN_WEIGHT) {
            System.out.println("weight must be greater than " + Constants.MIN_WEIGHT);
            return false;
        }
        if (weight > Constants.MAX_WEIGHT) {
            System.out.println("weight must be smaller than " + Constants.MAX_WEIGHT);
            return false;
        }
        return true;
    }

    public static boolean validateStudentId(String studentId) {
        if (studentId != null && studentId.length() != Constants.STUDENT_ID_LENGTH) {
            System.out.println("Student ID must be " + Constants.STUDENT_ID_LENGTH + " characters.");
            return false;
        }
        List<String> assignedIds = Student.getAssignedIds();
        if (assignedIds.contains(studentId)) {
            System.out.println("Student ID is already assigned.");
            return false;
        }
        return true;
    }

    public static boolean validateUniversity(String university) {
        if (university != null && university.length() > Constants.MAX_UNIVERSITY_LENGTH) {
            System.out.println("University must be less than " + Constants.MAX_UNIVERSITY_LENGTH + " characters.");
            return false;
        }
        return true;
    }

    public static boolean validateStartYear(Integer startYear) {
        if (startYear == null || startYear.toString().length() != 4) {
            System.out.println("Start year must be a 4-digit number.");
            return false;
        }
        if (startYear < Constants.MIN_START_YEAR) {
            System.out.println("Start year must be after 1900s.");
            return false;
        }
        return true;
    }


    public static boolean validateGPA(Float GPA) {
        if (GPA < Constants.MIN_GPA) {
            System.out.println("GPA must be greater than " + Constants.MIN_GPA + ".");
            return false;
        }
        if (GPA > Constants.MAX_GPA) {
            System.out.println("GPA must be smaller than " + Constants.MAX_GPA + ".");
            return false;
        }
        return true;
    }
}
