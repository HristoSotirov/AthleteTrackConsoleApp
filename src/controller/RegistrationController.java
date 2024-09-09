package controller;

import java.sql.SQLException;
import java.util.Scanner;

import DTO.LoginDTO;
import DTO.RegisterClubAdminDTO;
import client.Colors;
import service.ClubService;
import service.RegistrationService;
import service.SportService;
import service.UserService;

public class RegistrationController {

    private final RegistrationService registrationService = new RegistrationService();
    private final UserService userService = new UserService();
    private final ClubService clubService = new ClubService();
    private final SportService sportService = new SportService();
    private final Colors color = new Colors();
    private final Scanner scanner = new Scanner(System.in);

    // Empty constructor
    public RegistrationController() {}

    public Long login() throws SQLException {
        System.out.println(color.BLUE + "\nPlease enter your login credentials." + color.RESET);

        Long loggedUserId;

        do {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            loggedUserId = registrationService.login(new LoginDTO(username, password));

            if (loggedUserId == null) {
                System.out.println(color.RED + "Invalid username or password." + color.RESET);
            }
        } while (loggedUserId == null);


        return loggedUserId;
    }

    public void registerClubAndClubAdmin() throws SQLException {
        System.out.println(color.BLUE + "\nEnter club information." + color.RESET);

        String clubName;
        do {
            System.out.print("Club Name: ");
            clubName = scanner.nextLine();

            if (clubName.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }

            if (!clubService.isClubNameUnique(clubName)) {
                System.out.println(color.RED + "There is already registration with this name. Please choose a different name." + color.RESET);
            }
        } while(!clubService.isClubNameUnique(clubName) || clubName.trim().isEmpty());

        int choiceUpperLimit = sportService.displayAllSport();
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("Choose a sport: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice > 0 && choice <= choiceUpperLimit) {
                    validChoice = true;
                } else {
                    System.out.println(color.RED + "Invalid choice. Please choose a number between 1 and " + choiceUpperLimit + "." + color.RESET);
                }
            } else {
                System.out.println(color.RED + "Invalid input. Please enter a number." + color.RESET);
                scanner.next();
            }
        }
        Long sportId = sportService.chooseSport(choice);
        scanner.nextLine();

        String clubAddress;
        do {
            System.out.print("Club Address: ");
            clubAddress = scanner.nextLine();

            if (clubAddress.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (clubAddress.trim().isEmpty());

        String clubPhone;
        do {
            System.out.print("Club Phone: ");
            clubPhone = scanner.nextLine();

            if (clubPhone.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!clubService.isClubPhoneUnique(clubPhone)) {
                System.out.println(color.RED + "There is already registration with this phone. Please choose a different name." + color.RESET);
            }
        } while(!clubService.isClubPhoneUnique(clubPhone) || clubPhone.trim().isEmpty());

        String clubEmail;
        do {
            System.out.print("Club Email: ");
            clubEmail = scanner.nextLine();

            if (clubEmail.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!clubService.isClubEmailUnique(clubEmail)) {
                System.out.println(color.RED + "There is already registration with this email. Please choose a different name." + color.RESET);
            }
        } while(!clubService.isClubEmailUnique(clubEmail) || clubEmail.trim().isEmpty());

        System.out.println(color.BLUE + "\nEnter club admin information. " + color.RESET);

        String adminName;
        do {
            System.out.print("Admin Name: ");
            adminName = scanner.nextLine();

            if (adminName.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (adminName.trim().isEmpty());


        String adminUsername;
        do {
            System.out.print("Admin Username: ");
            adminUsername = scanner.nextLine();

            if (adminUsername.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isUsernameUnique(adminUsername)) {
                System.out.println(color.RED + "There is already registration with this username. Please choose a different username." + color.RESET);
            }
        } while(!userService.isUsernameUnique(adminUsername) || adminUsername.trim().isEmpty());

        String adminPassword;
        do {
            System.out.print("Admin Password: ");
            adminPassword = scanner.nextLine();

            if (adminPassword.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (adminPassword.trim().isEmpty());

        String adminPhone;
        do {
            System.out.print("Admin Phone: ");
            adminPhone = scanner.nextLine();

            if (adminPhone.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isPhoneUnique(adminPhone)) {
                System.out.println(color.RED + "There is already registration with this phone. Please choose a different username." + color.RESET);
            }
        } while(!userService.isPhoneUnique(adminPhone) || adminPhone.trim().isEmpty());

        String adminEmail;
        do {
            System.out.print("Admin Email: ");
            adminEmail = scanner.nextLine();

            if (clubName.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isEmailUnique(adminEmail)) {
                System.out.println(color.RED + "There is already registration with this email. Please choose a different username." + color.RESET);
            }
        } while(!userService.isEmailUnique(adminEmail) || adminEmail.trim().isEmpty());

        RegisterClubAdminDTO newClubAdmin = new RegisterClubAdminDTO(
                clubName,
                sportId,
                clubAddress,
                clubPhone,
                clubEmail,
                adminName,
                adminUsername,
                adminPassword,
                adminPhone,
                adminEmail
        );

        registrationService.registerClubAndClubAdmin(newClubAdmin);

        System.out.println(color.BLUE + "Admin registered successfully!" + color.RESET);
    }
}
