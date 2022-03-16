package Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Person {
    @Id
    private Integer nationalCode;
    @Column (length = 30)
    private String firstName;
    @Column (length = 50)
    private String lastName;
    @Column (length = 100)
    private String address;
    @Column (columnDefinition = "char(10)")
    private String password;

}
