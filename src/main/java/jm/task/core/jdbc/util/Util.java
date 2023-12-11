package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.Property;
import org.hibernate.service.ServiceRegistry;

//import javax.imageio.spi.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    // мб надо в юрл ?useSSL=false&serverTimezone=UTC
    private static final String USER = "postgres";
    private static final String PASSWORD = "jj8Iup";

    public static Connection getConnection() {
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

    public static SessionFactory getSessionFactory() {
     return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        try {
           Configuration configuration = new Configuration();
            //так
//           Properties settings = new Properties();
//           settings.put(Environment.DRIVER, "org.postgresql.Driver");
//           settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
//           settings.put(Environment.USER, "postgres");
//           settings.put(Environment.PASS, "jj8Iup");
//           settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
//
//           settings.put(Environment.SHOW_SQL, "true");
//           //settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "");
//           settings.put(Environment.HBM2DDL_AUTO, "update");
//
//           configuration.setProperties(settings);
//           configuration.addAnnotatedClass(User.class);
//
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties()).build();

            //или так
           configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
           configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
           configuration.setProperty("hibernate.connection.username", "postgres");
           configuration.setProperty("hibernate.connection.password", "jj8Iup");
           configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
           configuration.setProperty("hibernate.show_sql", "true");
           //configuration.setProperty("hibernate.hbm2ddl.auto", "update");
           configuration.addAnnotatedClass(User.class);
           return configuration.buildSessionFactory();
        } catch (Throwable ex){
            ex.printStackTrace();
        }
        return sessionFactory;
    }
}
