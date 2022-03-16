package Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Professor extends Person {
    @Enumerated(EnumType.STRING)
    private TypeOfEmployment typeOfEmployment;
    private Integer income;

    public Professor(Integer nationalCode, String firstName, String lastName, String address, String password, TypeOfEmployment typeOfEmployment, Integer income) {
        super(nationalCode, firstName, lastName, address, password);
        this.typeOfEmployment = typeOfEmployment;
        this.income = income;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nationalCode=" + getNationalCode() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", typeOfEmployment=" + typeOfEmployment + '\'' +
                '}';
    }

}
