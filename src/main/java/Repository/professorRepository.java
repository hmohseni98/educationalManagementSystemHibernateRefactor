package Repository;

import CustomException.UsernameAlreadyExist;
import Database.MyConnection;
import Entity.Professor;
import Entity.TypeOfEmployment;

import java.sql.*;
import java.util.List;

public class professorRepository implements UserInterface<Professor> {
    private Connection connection = MyConnection.getConnection;

    @Override
    public Integer save(Professor professor) {
        Integer id = null;
        try {
            String save = "INSERT INTO professor (national_code, first_name, last_name, address, password, contract_type, income) " +
                    "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(save, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, professor.getNationalCode());
            preparedStatement.setString(2, professor.getFirstName());
            preparedStatement.setString(3, professor.getLastName());
            preparedStatement.setString(4, professor.getAddress());
            preparedStatement.setString(5, professor.getPassword());
            preparedStatement.setObject(6, professor.getTypeOfEmployment(), Types.OTHER);
            preparedStatement.setInt(7, professor.getIncome());
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
    public void update(Professor professor) {
        try {
            String update = "UPDATE professor " +
                    "SET first_name = ? , last_name = ? , address = ? , password = ? , income = ? " +
                    "WHERE national_code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, professor.getFirstName());
            preparedStatement.setString(2, professor.getLastName());
            preparedStatement.setString(3, professor.getAddress());
            preparedStatement.setString(4, professor.getPassword());
            preparedStatement.setInt(5, professor.getIncome());
            preparedStatement.setString(6, professor.getNationalCode());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("database error");
        }
    }

    @Override
    public void delete(String nationalCode) {
        try {
            String delete = "DELETE FROM professor WHERE nactional_code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, nationalCode);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("database error");
        }
    }

    @Override
    public Professor findById(String nationalCode) {
        Professor professor = null;
        try {
            String findById = "SELECT * FROM account WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setString(1, nationalCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                professor = new Professor(resultSet.getString("national_code"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("password"),
                        TypeOfEmployment.valueOf(resultSet.getString("contract_type")),
                        resultSet.getInt("income"));
            }
        } catch (SQLException e) {
            System.out.println("database error");
        }
        return professor;
    }

    @Override
    public List<Professor> findAll() {
        return null;
    }

    @Override
    public Boolean login(Professor professor) {
        return null;
    }
}
