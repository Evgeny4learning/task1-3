package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        User user1 = new User("Веня", "Дыркин", (byte) 41);
        User user2 = new User("Курт", "Кобейн", (byte) 27);
        User user3 = new User("Стивен", "Уилсон", (byte) 56);
        User user4 = new User("Макс", "Кавалера", (byte) 34);

        userService.createUsersTable();
        System.out.println("Table is created: ");

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User with name " + user1.getName() + " added in table");

        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User with name " + user2.getName() + " added in table");

        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User with name " + user3.getName() + " added in table");

        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User with name " + user4.getName() + " added in table");

        System.out.println();

        for (User user : userService.getAllUsers()){
            System.out.println(user);
        }
//        userService.removeUserById(2);

        userService.cleanUsersTable();
        System.out.println("Table is cleaned");

//        for (User user : userService.getAllUsers()){
//            System.out.println(user);
//        }

        userService.dropUsersTable();
        System.out.println("Table was deleted");

    }
}
