package Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {

    private Integer income;

    public Employee(String nationalCode, String firstName, String lastName, String address, String password, Integer income) {
        super(nationalCode, firstName, lastName, address, password);
        this.income = income;
    }

}
