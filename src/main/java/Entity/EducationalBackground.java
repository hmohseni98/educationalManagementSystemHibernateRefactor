package Entity;

public class EducationalBackground {
    private Integer id;
    private Integer year;
    private Integer term;
    private Student student;
    private LessonScores lessonScores;
    private Float totalAvg;

    public EducationalBackground(Integer id, Integer year, Integer term, Student student, LessonScores lessonScores, Float totalAvg) {
        this.id = id;
        this.year = year;
        this.term = term;
        this.student = student;
        this.lessonScores = lessonScores;
        this.totalAvg = totalAvg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LessonScores getLessonScores() {
        return lessonScores;
    }

    public void setLessonScores(LessonScores lessonScores) {
        this.lessonScores = lessonScores;
    }

    public Float getTotalAvg() {
        return totalAvg;
    }

    public void setTotalAvg(Float totalAvg) {
        this.totalAvg = totalAvg;
    }
}
