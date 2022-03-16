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
public class LessonScores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer year;
    private Integer term;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Lesson lesson;
    private float score;

    public LessonScores(Integer year, Integer term, Student student, Lesson lesson, float score) {
        this.year = year;
        this.term = term;
        this.student = student;
        this.lesson = lesson;
        this.score = score;
    }

    @Override
    public String toString() {
        return "LessonScores{" +
                "id=" + id +
                ", year=" + year +
                ", term=" + term +
                ", student=" + student.getNationalCode() +
                ", lesson=" + lesson.getName() +
                ", score=" + score +
                '}';
    }
}
