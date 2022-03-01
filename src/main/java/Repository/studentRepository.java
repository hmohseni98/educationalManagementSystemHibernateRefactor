package Repository;

import CustomException.UsernameAlreadyExist;
import Database.MyConnection;
import Entity.Student;

import java.sql.*;
import java.util.List;

public class studentRepository implements UserInterface<Student> {
    private Connection connection = MyConnection.getConnection;

    @Override
    public Integer save(Student student) {
        Integer id = null;
        try {
            String save = "INSERT INTO student (national_code, first_name, last_name, address, password) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(save , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,student.getNationalCode());
            preparedStatement.setString(2,student.getFirstName());
            preparedStatement.setString(3,student.getLastName());
            preparedStatement.setString(4,student.getAddress());
            preparedStatement.setString(5,student.getPassword());
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
    public void update(Student student) {
        try {
            String update = "UPDATE student " +
                    "SET first_name = ? , last_name = ? , address = ? , password = ? " +
                    "WHERE national_code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setString(5, student.getNationalCode());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("database error");
        }
    }

    @Override
    public void delete(String nationalCode) {
        try {
            String delete = "DELETE FROM student WHERE nactional_code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, nationalCode);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("database error");
        }
    }

    @Override
    public Student findById(String nationalCode) {
        Student student = null;
        try {
            String findById = "SELECT * FROM account WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setString(1, nationalCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student(resultSet.getString("national_code"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("database error");
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Boolean login(Student student) {
        return null;
    }
}
