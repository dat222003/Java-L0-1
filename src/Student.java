import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends People{
    private static final List<String> assignedIds = new ArrayList<>();
    private String studentId;
    private String university;
    private Date startDate;
    private Double GPA;

    public Student(String name, String dateOfBirth, Double height, Double weight, String studentId, String university, Date startDate, Double GPA) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Double getGPA() {
        return GPA;
    }

    public void setGPA(Double GPA) {
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
