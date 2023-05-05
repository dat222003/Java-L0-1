package constants;

import java.time.LocalDate;
import java.util.Scanner;

public class Constants {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int MAX_NAME_LENGTH = 100;
    public static final LocalDate MIN_DATE = LocalDate.of(1900, 1, 1);
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final int MAX_ADDRESS_LENGTH = 300;
    public static final float MIN_HEIGHT = 50;
    public static final float MAX_HEIGHT = 300;
    public static final float MIN_WEIGHT = 5;
    public static final float MAX_WEIGHT = 1000;
    public static final int STUDENT_ID_LENGTH = 10;
    public static final int MAX_UNIVERSITY_LENGTH = 300;
    public static final int MIN_START_YEAR = 1900;
    public static final float MIN_GPA = 1;
    public static final float MAX_GPA = 10;
    public static final String DATA_STORAGE_FOLDER = "data";
    public static final String STUDENT_STORAGE_FILE = "data/students.txt";
}
