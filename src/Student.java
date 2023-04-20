import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends People{
    private static final List<String> assignedIds = new ArrayList<>();
    private String studentId;
    private String university;
    private LocalDate startDate;
    private Float GPA;

    public Student(String name, String dateOfBirth, Float height, Float weight, String studentId, String university, LocalDate startDate, Float GPA) {
        super(name, dateOfBirth, height, weight);
        if (studentId == null) throw new IllegalArgumentException("studentID cannot be null.");

        if (university == null) throw new IllegalArgumentException("university cannot be null.");

        if (startDate == null) throw new IllegalArgumentException("startDate cannot be null.");

        if (assignedIds.contains(studentId)) throw new IllegalArgumentException("This ID has already been assigned to another student.");

        assignedIds.add(studentId);
        this.studentId = studentId;
        this.university = university;
        this.startDate = startDate;
        this.GPA = GPA;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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
                "StudentID='" + studentId + '\'' +
                ", School='" + university + '\'' +
                ", StartDate='" + startDate + '\'' +
                ", GPA=" + GPA +
                "} ";
    }
}
