package Entity;



public class SelectUnit {
    private Integer id;
    private String year;
    private String term;
    private Student student;
    private PresentingLesson presentingLesson;

    public SelectUnit(Integer id, String year, String term, Student student, PresentingLesson presentingLesson) {
        this.id = id;
        this.year = year;
        this.term = term;
        this.student = student;
        this.presentingLesson = presentingLesson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public PresentingLesson getPresentingLesson() {
        return presentingLesson;
    }

    public void setPresentingLesson(PresentingLesson presentingLesson) {
        this.presentingLesson = presentingLesson;
    }
}
