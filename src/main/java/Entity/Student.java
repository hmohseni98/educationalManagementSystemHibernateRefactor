package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student extends Person {

    public Student(String nationalCode, String firstName, String lastName, String address, String password) {
        super(nationalCode, firstName, lastName, address, password);
    }
}
