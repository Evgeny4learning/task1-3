package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    //private final Session session = sessionFactory.openSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users " +
                "(id SERIAL PRIMARY KEY, name VARCHAR(100), " +
                "lastName VARCHAR(100), age INT) ";

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            //System.out.println("Table Users created");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("An exception was thrown " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users ";

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            //System.out.println("Table Users dropped");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("An exception was thrown " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User was added");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("An exception was thrown " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("An exception was thrown " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User ", User.class).list();
        }
    }
    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("An exception was thrown " + e.getMessage());
        }
    }
}
   // TRUNCATE <table name> RESTART IDENTITY;