package shop.menu;

import shop.menu.admin.AdminMenu;
import shop.menu.user.UserMenu;
import shop.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginMenu implements Menu {
    private Scanner scanner = new Scanner(System.in);
    private List<String> options = new ArrayList<>();
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private UserServiceImpl userService = new UserServiceImpl();

    public void addOptions() {
        options.add("1. Login");
        options.add("2. Register user");
        options.add("0. Exit");
    }

    public void getEmail() {
        System.out.println("Enter email");
        email = scanner.nextLine();
        if (!isEmailValid(email)) {
            System.out.println("Invalid email");
            getEmail();
        }
    }

    public void getLoginAndPassword() {
        getEmail();
        System.out.println("Enter password");
        password = scanner.nextLine();
    }

    public void getUserInfo() {
        getLoginAndPassword();
        System.out.println("Enter name");
        name = scanner.nextLine();
        System.out.println("Enter phone number");
        phoneNumber = scanner.nextLine();
    }

    public static boolean isEmailValid(String email) {
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    @Override
    public void show() {
        addOptions();
        showOptions(options);

        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    getLoginAndPassword();
                    if (userService.login(email, password)) {
                        userService.getMenu();
                    } else {
                        System.out.println("Invalid. Try again or register");
                        showOptions(options);
                    }
                    break;
                case 2:
                    getUserInfo();
                    userService.registerUser(email, password, name, phoneNumber);
                    userService.putNewUser();
                case 0:
                    close();
                    break;
                default:
                    showOptions(options);
                    break;
            }
        }
    }

    @Override
    public void close() {
        System.exit(0);
    }
}