package Entity;

public class PresentingLesson {
    private String id;
    private String years;
    private String term;
    private Lesson lesson;
    private Professor professor;

    public PresentingLesson(String id, String years, String term, Lesson lesson, Professor professor) {
        this.id = id;
        this.years = years;
        this.term = term;
        this.lesson = lesson;
        this.professor = professor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}

