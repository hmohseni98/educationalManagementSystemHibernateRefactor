package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student extends Person {

    public Student(Integer nationalCode, String firstName, String lastName, String address, String password) {
        super(nationalCode, firstName, lastName, address, password);
    }

    @Override
    public String toString() {
        return "Student{" +
                "nationalCode=" + getNationalCode() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
