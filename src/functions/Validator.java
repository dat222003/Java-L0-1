package functions;

import constants.Constants;
import models.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Validator {
    public static boolean validateName(String name) {
        if (name == null) {
            return false;
        }

        if (name.length() > Constants.MAX_NAME_LENGTH) {
            System.out.println("Name must be less than " + Constants.MAX_NAME_LENGTH + ".");
            return false;
        }

        return true;
    }

    public static boolean validateDateOfBirth(String dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(dateOfBirth, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Date of birth is not in the correct format.");
            return false;
        }

        //handle non exist days (ex:31-02-2009)
        String parsedDateString = parsedDate.format(dateFormatter);
        if (!parsedDateString.equals(dateOfBirth)) {
            System.out.println("Date of birth must be a valid calendar date.");
            return false;
        }

        if (parsedDate.isBefore(Constants.MIN_DATE)) {
            System.out.println("Date of birth must be after " + Constants.MIN_DATE + ".");
            return false;
        }

        if (parsedDate.isAfter(LocalDate.now())) {
            System.out.println("Date of birth must be before now.");
            return false;
        }

        return true;
    }

    public static boolean validateAddress(String address) {
        if (address == null) {
            return false;
        }

        if (address.length() > Constants.MAX_ADDRESS_LENGTH) {
            System.out.println("Address must be less than " + Constants.MAX_ADDRESS_LENGTH + " characters.");
            return false;
        }

        return true;
    }

    public static boolean validateHeight(String height) {
        if (height == null) {
            return false;
        }

        try {
            float heightInFloat = Float.parseFloat(height);
            if (heightInFloat < Constants.MIN_HEIGHT) {
                System.out.println("Height must be greater than " + Constants.MIN_HEIGHT);
                return false;
            }

            if (heightInFloat > Constants.MAX_HEIGHT) {
                System.out.println("Height must be smaller than " + Constants.MAX_HEIGHT);
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid height. Please enter a valid number.");
            return false;
        }


        return true;
    }

    public static boolean validateWeight(String weight) {
        if (weight == null) {
            return false;
        }

        try {
            float weightInFloat = Float.parseFloat(weight);
            if (weightInFloat < Constants.MIN_WEIGHT) {
                System.out.println("weight must be greater than " + Constants.MIN_WEIGHT);
                return false;
            }

            if (weightInFloat > Constants.MAX_WEIGHT) {
                System.out.println("weight must be smaller than " + Constants.MAX_WEIGHT);
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid weight. Please enter a valid number.");
            return false;
        }

        return true;
    }

    public static boolean validateStudentId(String studentId) {
        if (studentId == null) {
            return false;
        }
        if (studentId.length() != Constants.STUDENT_ID_LENGTH) {
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
        if (university == null) {
            return false;
        }

        if (university.length() > Constants.MAX_UNIVERSITY_LENGTH) {
            System.out.println("University must be less than " + Constants.MAX_UNIVERSITY_LENGTH + " characters.");
            return false;
        }

        return true;
    }

    public static boolean validateStartYear(String startYear) {
        if (startYear == null) {
            return false;
        }
        try {
            if (startYear.length() != 4) {
                System.out.println("Start year must be a 4-digit.");
                return false;
            }
            if (Integer.parseInt(startYear) < Constants.MIN_START_YEAR) {
                System.out.println("Start year must be after 1900s.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Start year must be number only");
            return false;
        }
        return true;
    }

    public static boolean validateGPA(String GPA) {
        if (GPA == null) {
            return false;
        }

        try {
            float gpaValue = Float.parseFloat(GPA);
            if (gpaValue < Constants.MIN_GPA) {
                System.out.println("GPA must be greater than " + Constants.MIN_GPA + ".");
                return false;
            }
            if (gpaValue > Constants.MAX_GPA) {
                System.out.println("GPA must be smaller than " + Constants.MAX_GPA + ".");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid GPA format! GPA must be a number.");
            return false;
        }
    }
}
