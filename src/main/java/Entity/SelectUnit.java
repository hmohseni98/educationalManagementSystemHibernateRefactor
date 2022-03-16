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
public class SelectUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer year;
    private Integer term;
    @ManyToOne
    private Student student;
    @ManyToOne
    private PresentingLesson presentingLesson;

    public SelectUnit(Integer year, Integer term, Student student, PresentingLesson presentingLesson) {
        this.year = year;
        this.term = term;
        this.student = student;
        this.presentingLesson = presentingLesson;
    }

    @Override
    public String toString() {
        return "presenting info{" +
                "id=" + id +
                ", year=" + year +
                ", term=" + term +
                ", student=" + student.getNationalCode() +
                ", lesson=" + presentingLesson.getLesson().getName() +
                '}';
    }
}
