package Repository;

import CustomException.UsernameAlreadyExist;
import Database.MyConnection;
import Entity.Employee;
import Entity.Student;

import java.sql.*;
import java.util.List;

public class employeeRepository implements UserInterface<Employee> {
    private Connection connection = MyConnection.getConnection;


    @Override
    public Integer save(Employee employee) {
        Integer id = null;
        try {
            String save = "INSERT INTO employee (national_code, first_name, last_name, address, password, income) " +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(save , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,employee.getNationalCode());
            preparedStatement.setString(2,employee.getFirstName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setString(4,employee.getAddress());
            preparedStatement.setString(5,employee.getPassword());
            preparedStatement.setInt(6,employee.getIncome());
            preparedStatement.execute();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UsernameAlreadyExist();
        }
        return id;
    }

    @Override
    public void update(Employee employee) {
        try {
            String update = "UPDATE employee " +
                    "SET first_name = ? , last_name = ? , address = ? , password = ? , income = ? " +
                    "WHERE national_code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPassword());
            preparedStatement.setInt(5, employee.getIncome());
            preparedStatement.setString(6, employee.getNationalCode());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("database error");
        }
    }

    @Override
    public void delete(String nationalCode) {
        try {
            String delete = "DELETE FROM employee WHERE nactional_code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, nationalCode);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("database error");
        }
    }

    @Override
    public Employee findById(String nationalCode) {
        Employee employee = null;
        try {
            String findById = "SELECT * FROM account WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setString(1, nationalCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee(resultSet.getString("national_code"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("password"),
                        resultSet.getInt("income"));
            }
        } catch (SQLException e) {
            System.out.println("database error");
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Boolean login(Employee employee) {
        return null;
    }
}
