package Entity;


public class Professor extends Person{
    private TypeOfEmployment typeOfEmployment;
    private Integer income;

    public Professor(String nationalCode, String firstName, String lastName, String address, String password, TypeOfEmployment typeOfEmployment, Integer income) {
        super(nationalCode, firstName, lastName, address, password);
        this.typeOfEmployment = typeOfEmployment;
        this.income = income;
    }

    public TypeOfEmployment getTypeOfEmployment() {
        return typeOfEmployment;
    }

    public void setTypeOfEmployment(TypeOfEmployment typeOfEmployment) {
        this.typeOfEmployment = typeOfEmployment;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }
}
