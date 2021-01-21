package manager;

import db.DBConnectionProvider;
import exception.DuplicateUserException;
import model.Gender;
import model.StaffType;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) throws DuplicateUserException {
        if (getUserByUsername(user.getUsername()) != null) {
            throw new DuplicateUserException();
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO user(name,surname,age,gender,work_experience,username,password,staff_type,user_type) " +
                            "VALUES(?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getGender().name());
            statement.setInt(5, user.getWorkExperience());
            statement.setString(6, user.getUsername());
            statement.setString(7, user.getPassword());
            statement.setString(8, user.getStaffType().name());
            statement.setString(9, user.getUserType().name());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void deleteUserById(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id=?");
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .surname(resultSet.getString(3))
                .age(resultSet.getInt(4))
                .gender(Gender.valueOf(resultSet.getString(5)))
                .workExperience(resultSet.getInt(6))
                .username(resultSet.getString(7))
                .password(resultSet.getString(8))
                .staffType(StaffType.valueOf(resultSet.getString(9)))
                .userType(UserType.valueOf(resultSet.getString(10)))
                .build();
    }


}
