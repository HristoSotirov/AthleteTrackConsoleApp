package client;

import controller.RegistrationController;
import controller.UserController;

import java.sql.SQLException;
import java.util.Scanner;

public class Client {
    private final RegistrationController registrationController = new RegistrationController();
    private final UserController userController = new UserController();
    private final Scanner scanner = new Scanner(System.in);

    private final Colors color = new Colors();

    public Client() {
    }

    public static void main(String[] args) throws SQLException {
        Client client = new Client();

        System.out.println("\nWelcome to Athlete Track App.\n");

        client.loginRegistartionMenu();
    }

    private void loginRegistartionMenu() throws SQLException {
        System.out.println("""
                    Please enter your login credentials.
                    If you don't have an account, reach out to your trainer or club administrator for assistance.
                    If your club is not yet registered, please complete the registration process.       
                    """);

        while(true) {
            System.out.println(color.BLUE + "Menu:" + color.RESET);
            System.out.println("""
                    1: Login
                    2: Registration
                    3: Exit
                    """);
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    Long loggedUserId = registrationController.login();
                    System.out.println(userController.getUserTypeByUserId(loggedUserId));
                    if (userController.getUserTypeByUserId(loggedUserId).trim().equalsIgnoreCase("ADMIN")) {
                        System.out.println(color.BLUE + "You are logged as Admin!" + color.RESET);
                        adminMenu(loggedUserId);
                    } else if (userController.getUserTypeByUserId(loggedUserId).trim().equalsIgnoreCase("CLUB_ADMIN")) {
                        System.out.println(color.BLUE + "You are logged as Club Administrator!\n" + color.RESET);

                    } else if (userController.getUserTypeByUserId(loggedUserId).trim().equalsIgnoreCase("COACH")) {
                        System.out.println(color.BLUE + "You are logged as Coach!" + color.RESET);

                    } else if (userController.getUserTypeByUserId(loggedUserId).trim().equalsIgnoreCase("ATHLETE")) {
                        System.out.println(color.BLUE + "You are logged as ATHLETE!" + color.RESET);

                    }
                    break;
                case "2":
                    registrationController.registerClubAndClubAdmin();
                    break;
                case "3":
                    System.out.println(color.BLUE + "\nSee you soon." + color.RESET);
                    return;
                default:
                    System.out.println(color.RED + "\nInvalid command.\n" + color.RESET);
                    break;
            }
        }
    }

    private void adminMenu(Long loggedUserId) throws SQLException {
        while(true) {
            System.out.println(color.BLUE + "\nMain menu:" + color.RESET);
            System.out.println("""
                    1: View profile
                    2: Verify a club
                    3: Add new Admin
                    4: Log out
                    """);

            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    profileMenu(loggedUserId);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    System.out.println(color.BLUE + "\nYou are logged out.\n" + color.RESET);
                    return;
                default:
                    System.out.println(color.RED + "\nInvalid command.\n" + color.RESET);
                    break;
            }
        }
    }

    private void clubAdminMenu(Long loggedUserId) throws SQLException {

    }

    private void coachMenu(Long loggedUserId) throws SQLException {

    }

    private void athleteMenu(Long loggedUserId) throws SQLException {

    }

    private void profileMenu(Long loggedUserId) throws SQLException {
        while(true) {
            userController.displaayProfile(loggedUserId);
            System.out.println(color.BLUE + "Profile menu:" + color.RESET);
            System.out.println("""
                    1: Edit profile
                    2: Back to Main Menu
                    """);
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    editProfileMenu(loggedUserId);
                    break;
                case "2":
                    return;
                default:
                    System.out.println(color.RED + "\nInvalid command.\n" + color.RESET);
                    break;
            }
        }
    }

    private void editProfileMenu(Long loggedUserId) throws SQLException {
        while (true) {
            System.out.println(color.BLUE + "\nEdit profile menu:" + color.RESET);
            System.out.println("""
                     1: Edit name
                     2: Edit username
                     3: Edit password
                     4: Edit phone
                     5: Edit email
                     6: Back to profile menu
                     """);
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    userController.updateUserName(loggedUserId);
                    System.out.println(color.BLUE + "\nField edited successfully." + color.RESET);
                    break;
                case "2":
                    userController.updateUsername(loggedUserId);
                    System.out.println(color.BLUE + "\nField edited successfully." + color.RESET);
                    break;
                case "3":
                    userController.updateUserPassword(loggedUserId);
                    System.out.println(color.BLUE + "\nField edited successfully." + color.RESET);
                    break;
                case "4":
                    userController.updateUserPhone(loggedUserId);
                    System.out.println(color.BLUE + "\nField edited successfully." + color.RESET);
                    break;
                case "5":
                    userController.updateUserEmail(loggedUserId);
                    System.out.println(color.BLUE + "\nField edited successfully." + color.RESET);
                    break;
                case "6":
                    return;
                default:
                    System.out.println(color.RED + "\nInvalid command.\n" + color.RESET);
                    break;
            }
        }
    }


}
