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
                    "INSERT INTO user(name,surname,age,gender,phone_number,work_experience,username,password,staff_type,user_type) " +
                            "VALUES(?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
           initUserData(statement,user);
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
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void removeUserById(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id=?");
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUserData(int currentUserId, User currentUser) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE user SET name=?,surname=?,age=?,gender=?,phone_number=?,work_experience=?,username=?,password=?,staff_type=?,user_type=? " +
                            "WHERE id=?");
            initUserData(statement,currentUser);
            statement.setInt(11, currentUserId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initUserData(PreparedStatement statement, User currentUser) throws SQLException {
        statement.setString(1, currentUser.getName());
        statement.setString(2, currentUser.getSurname());
        statement.setInt(3, currentUser.getAge());
        statement.setString(4, currentUser.getGender().name());
        statement.setString(5, currentUser.getPhoneNumber());
        statement.setInt(6, currentUser.getWorkExperience());
        statement.setString(7, currentUser.getUsername());
        statement.setString(8, currentUser.getPassword());
        statement.setString(9, currentUser.getStaffType().name());
        statement.setString(10, currentUser.getUserType().name());
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .surname(resultSet.getString(3))
                .age(resultSet.getInt(4))
                .gender(Gender.valueOf(resultSet.getString(5)))
                .phoneNumber(resultSet.getString(6))
                .workExperience(resultSet.getInt(7))
                .username(resultSet.getString(8))
                .password(resultSet.getString(9))
                .staffType(StaffType.valueOf(resultSet.getString(10)))
                .userType(UserType.valueOf(resultSet.getString(11)))
                .build();
    }


}
