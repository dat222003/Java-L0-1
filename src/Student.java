import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends People{
    private static final List<String> assignedIds = new ArrayList<>();
    private String studentId;
    private String university;
    private Integer startYear;
    private Float GPA;

    public Student(String name, LocalDate dateOfBirth, String address, Float height, Float weight, String studentId, String university, Integer startYear, Float GPA) {
        super(name, dateOfBirth, address, height, weight);
        assignedIds.add(studentId);
        this.studentId = studentId;
        this.university = university;
        this.startYear = startYear;
        this.GPA = GPA;
    }

    public static void removeIdInAssignedIds(String studentId) {
        assignedIds.remove(studentId);
    }

    public static List<String> getAssignedIds() {
        return assignedIds;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Float getGPA() {
        return GPA;
    }

    public void setGPA(Float GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", dateOfBirth='" + getDateOfBirth() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", height=" + getHeight() +
                ", weight=" + getWeight() +
                ", studentId='" + studentId + '\'' +
                ", university='" + university + '\'' +
                ", startYear=" + startYear +
                ", GPA=" + GPA +
                '}';
    }

}

