package Entity;


import lombok.*;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {

    private Integer income;

    public Employee(Integer nationalCode, String firstName, String lastName, String address, String password, Integer income) {
        super(nationalCode, firstName, lastName, address, password);
        this.income = income;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nationalCode=" + getNationalCode() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", income='" + income + '\'' +
                '}';
    }
}
