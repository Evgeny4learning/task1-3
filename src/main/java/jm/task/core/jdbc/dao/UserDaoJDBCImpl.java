package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users" +
                "(id SERIAL PRIMARY KEY, name VARCHAR(100), " +
                "lastName VARCHAR(100), age INT)";

        try (Connection con = Util.getConnection();
             Statement statement = con.createStatement()){

            statement.executeUpdate(sql);
            //System.out.println("Table Users created");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";

        try (Connection con = Util.getConnection();
             Statement statement = con.createStatement()){

            statement.executeUpdate(sql);
            //System.out.println("Table Users dropped");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users(name, lastName, age) VALUES(?, ?, ?)";

        try (Connection con = Util.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)){

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();
            //System.out.println("User was added");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id=?";

        try (Connection con = Util.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)){

            statement.setLong(1, id);

            statement.executeUpdate();
            //System.out.println("User was removed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT id, name, lastName, age FROM Users";

        try (Connection con = Util.getConnection();
             Statement statement = con.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }

            //System.out.println("List of users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";

        try (Connection con = Util.getConnection();
             Statement statement = con.createStatement()){

            statement.executeUpdate(sql);
            //System.out.println("Table was cleaned");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
