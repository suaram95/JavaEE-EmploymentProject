package manager;

import com.sun.jmx.snmp.internal.SnmpAccessControlModel;
import db.DBConnectionProvider;
import model.Task;
import model.TaskStatus;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private Connection connection;

    private UserManager userManager = new UserManager();

    public TaskManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addTask(Task task) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO task(name,description,created_date,deadline,comment,status,user_id)" +
                            "VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, DateUtil.getStringFromDate(task.getCreatedDate()));
            statement.setString(4, DateUtil.getStringFromDate(task.getDeadline()));
            statement.setString(5, task.getComment());
            statement.setString(6, task.getTaskStatus().name());
            statement.setInt(7, task.getUser().getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                task.setId(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM task");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                taskList.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void removeTaskById(int taskId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM task WHERE id=?");
            statement.setInt(1, taskId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Task getTaskFromResultSet(ResultSet resultSet) throws SQLException {
        return Task.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .description(resultSet.getString(3))
                .createdDate(DateUtil.getDateFromString(resultSet.getString(4)))
                .deadline(DateUtil.getDateFromString(resultSet.getString(5)))
                .comment(resultSet.getString(6))
                .taskStatus(TaskStatus.valueOf(resultSet.getString(7)))
                .user(userManager.getUserById(resultSet.getInt(8)))
                .build();
    }


}
