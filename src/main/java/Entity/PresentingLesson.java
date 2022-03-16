package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PresentingLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer year;
    private Integer term;
    @ManyToOne
    private Lesson lesson;
    @ManyToOne
    private Professor professor;

    public PresentingLesson(Integer year, Integer term, Lesson lesson, Professor professor) {
        this.year = year;
        this.term = term;
        this.lesson = lesson;
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "PresentingLesson{" +
                "id=" + id +
                ", year=" + year +
                ", term=" + term +
                ", lesson name=" + lesson.getName() +
                ", lesson unit=" + lesson.getUnit() +
                ", professor name =" + professor.getFirstName() + " " + professor.getLastName() +
                '}';
    }
}

