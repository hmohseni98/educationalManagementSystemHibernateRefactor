package Entity;

public abstract class Person {
    private String nationalCode;
    private String firstName;
    private String lastName;
    private String address;
    private String password;

    public Person(String nationalCode, String firstName, String lastName, String address, String password) {
        this.nationalCode = nationalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
