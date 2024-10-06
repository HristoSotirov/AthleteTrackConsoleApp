package controller;

import java.sql.SQLException;
import java.util.Scanner;

import DTO.LoginDTO;
import DTO.RegisterClubAdminDTO;
import DTO.UserDTO;
import client.Colors;
import service.*;

public class RegistrationController {

    private final RegistrationService registrationService = new RegistrationService();
    private final UserService userService = new UserService();
    private final UsersClubsService usersClubsService = new UsersClubsService();
    private final ClubService clubService = new ClubService();
    private final SportService sportService = new SportService();
    private final Colors color = new Colors();
    private final Scanner scanner = new Scanner(System.in);

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
            clubName = scanner.nextLine().trim();

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
            clubAddress = scanner.nextLine().trim();

            if (clubAddress.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (clubAddress.trim().isEmpty());

        String clubPhone;
        do {
            System.out.print("Club Phone: ");
            clubPhone = scanner.nextLine().trim();

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
            clubEmail = scanner.nextLine().trim();

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
            adminName = scanner.nextLine().trim();

            if (adminName.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (adminName.trim().isEmpty());


        String adminUsername;
        do {
            System.out.print("Admin Username: ");
            adminUsername = scanner.nextLine().trim();

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
            adminPassword = scanner.nextLine().trim();

            if (adminPassword.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (adminPassword.trim().isEmpty());

        String adminPhone;
        do {
            System.out.print("Admin Phone: ");
            adminPhone = scanner.nextLine().trim();

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
            adminEmail = scanner.nextLine().trim();

            if (adminEmail.trim().isEmpty()) {
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
    }

    public void registerAdmin() throws SQLException {
        System.out.println(color.BLUE + "\nEnter club admin information. " + color.RESET);

        String adminName;
        do {
            System.out.print("Admin Name: ");
            adminName = scanner.nextLine().trim();

            if (adminName.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (adminName.trim().isEmpty());


        String adminUsername;
        do {
            System.out.print("Admin Username: ");
            adminUsername = scanner.nextLine().trim();

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
            adminPassword = scanner.nextLine().trim();

            if (adminPassword.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (adminPassword.trim().isEmpty());

        String adminPhone;
        do {
            System.out.print("Admin Phone: ");
            adminPhone = scanner.nextLine().trim();

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
            adminEmail = scanner.nextLine().trim();

            if (adminEmail.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isEmailUnique(adminEmail)) {
                System.out.println(color.RED + "There is already registration with this email. Please choose a different username." + color.RESET);
            }
        } while(!userService.isEmailUnique(adminEmail) || adminEmail.trim().isEmpty());

        UserDTO userDTO = new UserDTO(
                adminName,
                adminUsername,
                adminPhone,
                adminEmail,
                "ADMIN"
        );

        registrationService.registerAdmin(userDTO, adminPassword);

    }

    public void registerCoach(Long clubId) throws SQLException {
        System.out.println(color.BLUE + "\nEnter coach information. " + color.RESET);

        String coachName;
        do {
            System.out.print("Coach Name: ");
            coachName = scanner.nextLine().trim();

            if (coachName.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (coachName.trim().isEmpty());


        String coachUsername;
        do {
            System.out.print("Coach Username: ");
            coachUsername = scanner.nextLine().trim();

            if (coachUsername.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isUsernameUnique(coachUsername)) {
                System.out.println(color.RED + "There is already registration with this username. Please choose a different username." + color.RESET);
            }
        } while(!userService.isUsernameUnique(coachUsername) || coachUsername.trim().isEmpty());

        String coachPassword;
        do {
            System.out.print("Coach Password: ");
            coachPassword = scanner.nextLine().trim();

            if (coachPassword.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (coachPassword.trim().isEmpty());

        String coachPhone;
        do {
            System.out.print("Coach Phone: ");
            coachPhone = scanner.nextLine().trim();

            if (coachPhone.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isPhoneUnique(coachPhone)) {
                System.out.println(color.RED + "There is already registration with this phone. Please choose a different username." + color.RESET);
            }
        } while(!userService.isPhoneUnique(coachPhone) || coachPhone.trim().isEmpty());

        String coachEmail;
        do {
            System.out.print("Coach Email: ");
            coachEmail = scanner.nextLine().trim();

            if (coachEmail.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isEmailUnique(coachEmail)) {
                System.out.println(color.RED + "There is already registration with this email. Please choose a different username." + color.RESET);
            }
        } while(!userService.isEmailUnique(coachEmail) || coachEmail.trim().isEmpty());

        UserDTO userDTO = new UserDTO(
                coachName,
                coachUsername,
                coachPhone,
                coachEmail,
                "COACH"
        );

        registrationService.registerCoach(userDTO, coachPassword, clubId);

    }

    public void addCoach(Long clubId) throws SQLException {
        System.out.println(color.BLUE + "\nEnter coach information. " + color.RESET);

        String coachUsername;
        Long coachId;
        do {
            System.out.print("Coach Username: ");
            coachUsername = scanner.nextLine().trim();
            coachId = userService.getUserIdByUsername(coachUsername);

            if (coachUsername.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (userService.isUsernameUnique(coachUsername)) {
                System.out.println(color.RED + "User not found. Try again." + color.RESET);
                continue;
            }
            if (!userService.getUserById(coachId).getType().equals("COACH")) {
                System.out.println(color.RED + "User is not a coach." + color.RESET);
            }
            if (!usersClubsService.isUserClubExists(coachId, clubId)) {
                System.out.println(color.RED + "Coach is already at your club." + color.RESET);
                return;
            }
        } while(userService.isUsernameUnique(coachUsername) || coachUsername.trim().isEmpty()
                || !userService.getUserById(coachId).getType().equals("COACH"));


        registrationService.addCoach(coachId, clubId);

    }

    public void registerAthlete(Long clubId) throws SQLException {
        System.out.println(color.BLUE + "\nEnter athlete information. " + color.RESET);

        String athleteName;
        do {
            System.out.print("Athlete Name: ");
            athleteName = scanner.nextLine().trim();

            if (athleteName.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (athleteName.trim().isEmpty());


        String athleteUsername;
        do {
            System.out.print("Athlete Username: ");
            athleteUsername = scanner.nextLine().trim();

            if (athleteUsername.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isUsernameUnique(athleteUsername)) {
                System.out.println(color.RED + "There is already registration with this username. Please choose a different username." + color.RESET);
            }
        } while(!userService.isUsernameUnique(athleteUsername) || athleteUsername.trim().isEmpty());

        String athletePassword;
        do {
            System.out.print("Athlete Password: ");
            athletePassword = scanner.nextLine().trim();

            if (athletePassword.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
            }
        } while (athletePassword.trim().isEmpty());

        String athletePhone;
        do {
            System.out.print("Athlete Phone: ");
            athletePhone = scanner.nextLine().trim();

            if (athletePhone.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isPhoneUnique(athletePhone)) {
                System.out.println(color.RED + "There is already registration with this phone. Please choose a different username." + color.RESET);
            }
        } while(!userService.isPhoneUnique(athletePhone) || athletePhone.trim().isEmpty());

        String athleteEmail;
        do {
            System.out.print("Athlete Email: ");
            athleteEmail = scanner.nextLine().trim();

            if (athleteEmail.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (!userService.isEmailUnique(athleteEmail)) {
                System.out.println(color.RED + "There is already registration with this email. Please choose a different username." + color.RESET);
            }
        } while(!userService.isEmailUnique(athleteEmail) || athleteEmail.trim().isEmpty());

        UserDTO userDTO = new UserDTO(
                athleteName,
                athleteUsername,
                athletePhone,
                athleteEmail,
                "ATHLETE"
        );

        registrationService.registerAthlete(userDTO, athletePassword, clubId);

    }

    public void addAthlete(Long clubId) throws SQLException {
        System.out.println(color.BLUE + "\nEnter athlete information. " + color.RESET);

        String athleteUsername;
        Long athleteId;
        do {
            System.out.print("Athlete Username: ");
            athleteUsername = scanner.nextLine().trim();
            athleteId = userService.getUserIdByUsername(athleteUsername);

            if (athleteUsername.trim().isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }
            if (userService.isUsernameUnique(athleteUsername)) {
                System.out.println(color.RED + "User not found. Try again." + color.RESET);
                continue;
            }
            if (!userService.getUserById(athleteId).getType().equals("ATHLETE")) {
                System.out.println(color.RED + "User is not a athlete." + color.RESET);
            }
            if (usersClubsService.isUserClubExists(athleteId, clubId)) {
                System.out.println(color.RED + "Athlete is already at your club." + color.RESET);
                return;
            }
        } while(userService.isUsernameUnique(athleteUsername) || athleteUsername.trim().isEmpty()
                || !userService.getUserById(athleteId).getType().equals("ATHLETE"));


        registrationService.addAthlete(athleteId, clubId);

    }
}
