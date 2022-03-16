package Entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 30)
    private String name;
    @Column (columnDefinition = "int")
    private Integer unit;

    public Lesson(String name, Integer unit) {
        this.name = name;
        this.unit = unit;
    }

}
