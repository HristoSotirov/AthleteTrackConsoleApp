package client;

import DTO.UserDTO;
import controller.*;
import service.UsersClubsService;

import java.sql.SQLException;
import java.util.Scanner;

public class Client {
    private final RegistrationController registrationController = new RegistrationController();
    private final UserController userController = new UserController();
    private final ClubController clubController = new ClubController();
    private final UsersClubsController usersClubsController = new UsersClubsController();
    private final WorkoutController workoutController = new WorkoutController();
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
                    2: Sign up
                    3: Exit
                    """);
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();

            switch (command) {
                case "1":
                    Long loggedUserId = registrationController.login();
                    if (userController.getUserTypeByUserId(loggedUserId).trim().equalsIgnoreCase("ADMIN")) {
                        System.out.println(color.BLUE + "\nYou are logged as Admin!" + color.RESET);
                        adminMenu(loggedUserId);
                    } else if (userController.getUserTypeByUserId(loggedUserId).trim().equalsIgnoreCase("CLUB_ADMIN")) {
                        Long loggedWithClubId = usersClubsController.getUserClub(loggedUserId);
                        if (loggedWithClubId == 0L){
                            return;
                        }
                        if (!clubController.isClubVerified(loggedWithClubId)){
                            System.out.println(color.RED + "\nIt looks like your club is not verified.\n" + color.RESET);
                            do {
                                System.out.print("Press (Y) to log out : ");
                                command = scanner.nextLine().trim();
                                if (!"Y".equalsIgnoreCase(command)) {
                                    System.out.println(color.RED + "Invalid command." + color.RESET);
                                }
                            } while (!"Y".equalsIgnoreCase(command));
                            System.out.println(color.BLUE + "\nSee you soon.\n" + color.RESET);
                            break;
                        }
                        System.out.println(color.BLUE + "\nYou are logged as Club Administrator of " + clubController.getClub(loggedWithClubId).getName() + "!" + color.RESET);
                        clubAdminMenu(loggedUserId, loggedWithClubId);
                        break;
                    } else if (userController.getUserTypeByUserId(loggedUserId).trim().equalsIgnoreCase("COACH")) {
                        Long loggedWithClubId = usersClubsController.getUserClub(loggedUserId);
                        if (loggedWithClubId == 0L){
                            return;
                        }
                        System.out.println(color.BLUE + "\nYou are logged as Coach of " + clubController.getClub(loggedWithClubId).getName() + "!" + color.RESET);
                        coachMenu(loggedUserId, loggedWithClubId);
                        break;
                    } else if (userController.getUserTypeByUserId(loggedUserId).trim().equalsIgnoreCase("ATHLETE")) {
                        Long loggedWithClubId = usersClubsController.getUserClub(loggedUserId);
                        if (loggedWithClubId == 0L){
                            return;
                        }
                        System.out.println(color.BLUE + "\nYou are logged as Athlete of " + clubController.getClub(loggedWithClubId).getName() + "!" + color.RESET);
                        athleteMenu(loggedUserId, loggedWithClubId);
                        break;
                    }
                    break;
                case "2":
                    registrationController.registerClubAndClubAdmin();
                    System.out.println(color.BLUE + "\nClub and Club Admin registered successfully!\n" + color.RESET);
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
                    3: Sign up new Admin
                    4: Log out
                    """);

            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();
            switch (command) {
                case "1":
                    profileMenu(loggedUserId);
                    break;
                case "2":
                    clubController.verifyClub(loggedUserId);
                    break;
                case "3":
                    registrationController.registerAdmin();
                    System.out.println(color.BLUE + "\nAdmin registered successfully!" + color.RESET);
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

    private void clubAdminMenu(Long loggedUserId, Long clubId) throws SQLException {

        while(true) {
            System.out.println(color.BLUE + "\nMain menu:" + color.RESET);
            System.out.println("""
                     1: View profile
                     2: Show all coaches
                     3: Show all athletes
                     4: Sign up new coach
                     5: Add a coach
                     6: Remove a coach
                     7: Sign up new athlete
                     8: Add a athlete
                     9: Remove a athlete
                    10: Show analysis for all athletes
                    11: Show analysis for spesific athletes
                    12: Log out
                    """);

            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();
            switch (command) {
                case "1":
                    profileMenu(loggedUserId);
                    break;
                case "2":
                    usersClubsController.getCoahesByClub(clubId);
                    break;
                case "3":
                    usersClubsController.getAthletesByClub(clubId);
                    break;
                case "4":
                    registrationController.registerCoach(clubId);
                    System.out.println(color.BLUE + "\nCoach registered successfully!\n" + color.RESET);
                    break;
                case "5":
                    registrationController.addCoach(clubId);
                    System.out.println(color.BLUE + "\nCoach added successfully!\n" + color.RESET);
                    break;
                case "6":
                    usersClubsController.deleteCoach(clubId);
                    System.out.println(color.BLUE + "\nCoach deleted successfully!\n" + color.RESET);
                    break;
                case "7":
                    registrationController.registerAthlete(clubId);
                    System.out.println(color.BLUE + "\nAthlete registered successfully!\n" + color.RESET);
                    break;
                case "8":
                    registrationController.addAthlete(clubId);
                    System.out.println(color.BLUE + "\nAthlete added successfully!\n" + color.RESET);
                    break;
                case "9":
                    usersClubsController.deleteAthlete(clubId);
                    System.out.println(color.BLUE + "\nAthlete deleted successfully!\n" + color.RESET);
                    break;
                case "10":
                    workoutController.getAnalysisForAllAthletes(clubId);
                    break;
                case "11":
                    workoutController.getAnalysisForManyAthletes(clubId);
                    break;
                case "12":
                    System.out.println(color.BLUE + "\nYou are logged out.\n" + color.RESET);
                    return;
                default:
                    System.out.println(color.RED + "\nInvalid command.\n" + color.RESET);
                    break;
            }
        }
    }

    private void coachMenu(Long loggedUserId, Long clubId) throws SQLException {
        while(true) {
            System.out.println(color.BLUE + "\nMain menu:" + color.RESET);
            System.out.println("""
                    1: View profile
                    2: Show all athletes
                    3: Sign up new athlete
                    4: Add a athlete
                    5: Enter new workout
                    6: Delete workout
                    7: Show analysis for all athletes
                    8: Show analysis for spesific athletes
                    9: Log out
                    """);

            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();
            switch (command) {
                case "1":
                    profileMenu(loggedUserId);
                    break;
                case "2":
                    usersClubsController.getAthletesByClub(clubId);
                    break;
                case "3":
                    registrationController.registerAthlete(clubId);
                    System.out.println(color.BLUE + "\nAthlete registered successfully!\n" + color.RESET);
                    break;
                case "4":
                    registrationController.addAthlete(clubId);
                    System.out.println(color.BLUE + "\nAthlete registered successfully!\n" + color.RESET);
                    break;
                case "5":
                    workoutController.addWorkout(loggedUserId, clubId);
                    break;
                case "6":
                    workoutController.deleteWorkout(clubId);
                    break;
                case "7":
                    workoutController.getAnalysisForAllAthletes(clubId);
                    break;
                case "8":
                    workoutController.getAnalysisForManyAthletes(clubId);
                    break;
                case "9":
                    System.out.println(color.BLUE + "\nYou are logged out.\n" + color.RESET);
                    break;
                default:
                    System.out.println(color.RED + "\nInvalid command.\n" + color.RESET);
                    break;
            }
        }
    }

    private void athleteMenu(Long loggedUserId, Long clubId) throws SQLException {
        while(true) {
            System.out.println(color.BLUE + "\nMain menu:" + color.RESET);
            System.out.println("""
                    1: View profile
                    2: Show analysis
                    3: Log out
                    """);

            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();
            switch (command) {
                case "1":
                    profileMenu(loggedUserId);
                    break;
                case "2":
                    workoutController.getAnalysisForAthlete(loggedUserId);
                    break;
                case "3":
                    System.out.println(color.BLUE + "\nYou are logged out.\n" + color.RESET);
                    return;
                default:
                    System.out.println(color.RED + "\nInvalid command.\n" + color.RESET);
                    break;
            }
        }
    }

    private void profileMenu(Long loggedUserId) throws SQLException {
        while(true) {
            userController.displaayProfile(loggedUserId);
            System.out.println(color.BLUE + "\n" + "Profile menu:" + color.RESET);
            System.out.println("""
                    1: Edit profile
                    2: Back to Main Menu
                    """);
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();
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
            String command = scanner.nextLine().trim();
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
