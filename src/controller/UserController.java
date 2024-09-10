package controller;

import DTO.UserDTO;
import client.Colors;
import entity.Users;
import service.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);
    private final Colors color = new Colors();

    public UserController() {
    }

    public String getUserTypeByUserId(Long userId) {
        return userService.getUserTypeByUserId(userId);
    }

    public void displaayProfile(Long userId) {
        UserDTO userDTO = userService.getUserById(userId);

        System.out.println(color.BLUE + "\nProfile." + color.RESET);
        System.out.println(
                "Name: " + userDTO.getName() +
                "\nUsername: " + userDTO.getUsername() +
                "\nPhone: " + userDTO.getPhone() +
                "\nEmail: " + userDTO.getEmail() +
                "\nUser type: " + userDTO.getType()
        );
    }

    public void updateUserName(Long userId) {
        String newName;
        do {
            System.out.print("New Name: ");
            newName = scanner.nextLine().trim();

            if (newName.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (newName.trim().isEmpty());
        userService.updateUserName(userId, newName);
    }

    public void updateUsername(Long userId) {
        String newUsername;
        do {
            System.out.print("New Username: ");
            newUsername = scanner.nextLine().trim();

            if (newUsername.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isUsernameUnique(newUsername)) {
                System.out.println(color.RED + "The username already exists. Please choose a different username." + color.RESET);
            }
        } while(!userService.isUsernameUnique(newUsername) || newUsername.trim().isEmpty());
        userService.updateUsername(userId, newUsername);
    }

    public void updateUserPassword(Long userId) {
        String newPassword;
        do {
            System.out.print("New Password: ");
            newPassword = scanner.nextLine().trim();

            if (newPassword.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (newPassword.trim().isEmpty());
        userService.updateUserPassword(userId, newPassword);
    }

    public void updateUserPhone(Long userId) {
        String newPhone;
        do {
            System.out.print("New Phone: ");
            newPhone = scanner.nextLine().trim();

            if (newPhone.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isPhoneUnique(newPhone)) {
                System.out.println(color.RED + "There is already registration with this phone. Please choose a different username." + color.RESET);
            }
        } while(!userService.isPhoneUnique(newPhone) || newPhone.trim().isEmpty());
        userService.updateUserPhone(userId, newPhone);
    }

    public void updateUserEmail(Long userId) {
        String newEmail;
        do {
            System.out.print("Admin Email: ");
            newEmail = scanner.nextLine().trim();

            if (newEmail.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isEmailUnique(newEmail)) {
                System.out.println(color.RED + "There is already registration with this email. Please choose a different username." + color.RESET);
            }
        } while(!userService.isEmailUnique(newEmail) || newEmail.trim().isEmpty());
        userService.updateUserEmail(userId, newEmail);
    }

}
