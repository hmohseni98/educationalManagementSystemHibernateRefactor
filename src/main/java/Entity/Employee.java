package Entity;

public class Employee extends Person {
    private Integer income;

    public Employee(String nationalCode, String firstName, String lastName, String address, String password, Integer income) {
        super(nationalCode, firstName, lastName, address, password);
        this.income = income;
    }


    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }
}
