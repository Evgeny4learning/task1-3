package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    // мб надо в юрл ?useSSL=false&serverTimezone=UTC
    private static final String USER = "postgres";
    private static final String PASSWORD = "jj8Iup";

    public static Connection getConnection() throws SQLException {
        Connection con = null;

        try{
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
            //System.out.println("Connection ok");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }

        return con;
    }
}
