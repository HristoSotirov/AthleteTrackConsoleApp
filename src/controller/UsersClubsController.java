package controller;

import client.Colors;
import service.ClubService;
import service.UserService;
import service.UsersClubsService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UsersClubsController {
    private final UsersClubsService usersClubsService = new UsersClubsService();
    private final UserService userService = new UserService();
    private final ClubService clubService = new ClubService();
    private final Scanner scanner = new Scanner(System.in);
    private final Colors color = new Colors();

    public UsersClubsController() {
    }

    public Long getUserClub(Long userId) throws SQLException {
        if (usersClubsService.getUserClubCount(userId) == 0) {
            System.out.println(color.RED + "\nIt looks like you are not a member of any club." + color.RESET);
            return 0L;
        } else if (usersClubsService.getUserClubCount(userId) == 1) {
            return usersClubsService.getSingleUserClubId(userId);
        } else {
            System.out.println(color.BLUE + "\nIt looks like you are a member of several clubs." + color.RESET);
            List<Long> clubsId = usersClubsService.getUserClubIds(userId);
            System.out.println(color.BLUE + "\nList of the clubs." + color.RESET);
            for (int i = 0; i <= clubsId.size() - 1; i++) {
                if (i < 9) {
                    System.out.println(" " + (i + 1) + ": " + clubService.getClub(clubsId.get(i)).getName());
                } else {
                    System.out.println((i + 1) + ": " + clubService.getClub(clubsId.get(i)).getName());
                }
            }
            int choice = 0;
            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("Choose a club: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice > 0 && choice <= clubsId.size()) {
                        validChoice = true;
                    } else {
                        System.out.println(color.RED + "Invalid choice. Please choose a number between 1 and " + clubsId.size() + "." + color.RESET);
                    }
                } else {
                    System.out.println(color.RED + "Invalid input. Please enter a number." + color.RESET);
                    scanner.next();
                }
            }
            return clubsId.get(choice - 1);
        }
    }

    public void getCoahesByClub(Long clubId) throws SQLException {
        List<Long> coachId = usersClubsService.getCoachesByClub(clubId);
        if (coachId.size() == 0){
            System.out.println(color.RED + "\nThere are no coaches in this club." + color.RESET);
            return;
        }
        System.out.println(color.BLUE + "\nList of the coaches." + color.RESET);
        for (int i = 0; i <= coachId.size() - 1; i++) {
            if (i < 9) {
                System.out.println(" " + (i + 1) + ": Name: " + userService.getUserById(coachId.get(i)).getName() +
                        "\n    Email: " + userService.getUserById(coachId.get(i)).getEmail() +
                        "\n    Phone: " + userService.getUserById(coachId.get(i)).getPhone());
            } else {
                System.out.println((i + 1) + ": Name: " + userService.getUserById(coachId.get(i)).getName() +
                        "\n    Email: " + userService.getUserById(coachId.get(i)).getEmail() +
                        "\n    Phone: " + userService.getUserById(coachId.get(i)).getPhone());
            }
        }
    }

    public void getAthletesByClub(Long clubId) throws SQLException {
        List<Long> athleteId = usersClubsService.getAthletesByClub(clubId);
        if (athleteId.size() == 0){
            System.out.println(color.RED + "\nThere are no athletes in this club." + color.RESET);
            return;
        }
        System.out.println(color.BLUE + "\nList of the athletes." + color.RESET);
        for (int i = 0; i <= athleteId.size() - 1; i++) {
            if (i < 9) {
                System.out.println(" " + (i + 1) + ": Name: " + userService.getUserById(athleteId.get(i)).getName() +
                        "\n    Email: " + userService.getUserById(athleteId.get(i)).getEmail() +
                        "\n    Phone: " + userService.getUserById(athleteId.get(i)).getPhone());
            } else {
                System.out.println((i + 1) + ": Name: " + userService.getUserById(athleteId.get(i)).getName() +
                        "\n    Email: " + userService.getUserById(athleteId.get(i)).getEmail() +
                        "\n    Phone: " + userService.getUserById(athleteId.get(i)).getPhone());
            }
        }
    }

    public void deleteCoach(Long clubId) throws SQLException {
        List<Long> coachId = usersClubsService.getCoachesByClub(clubId);
        if (coachId.size() == 0){
            System.out.println(color.RED + "\nThere are no coaches in this club." + color.RESET);
            return;
        }
        System.out.println(color.BLUE + "\nList of the coaches." + color.RESET);
        for (int i = 0; i <= coachId.size() - 1; i++) {
            if (i < 9) {
                System.out.println(" " + (i + 1) + ": Name: " + userService.getUserById(coachId.get(i)).getName() +
                        "\n    Email: " + userService.getUserById(coachId.get(i)).getEmail() +
                        "\n    Phone: " + userService.getUserById(coachId.get(i)).getPhone());
            } else {
                System.out.println((i + 1) + ": Name: " + userService.getUserById(coachId.get(i)).getName() +
                        "\n    Email: " + userService.getUserById(coachId.get(i)).getEmail() +
                        "\n    Phone: " + userService.getUserById(coachId.get(i)).getPhone());
            }
        }
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("Choose a coach: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice > 0 && choice <= coachId.size()) {
                    validChoice = true;
                } else {
                    System.out.println(color.RED + "Invalid choice. Please choose a number between 1 and " + coachId.size() + "." + color.RESET);
                }
            } else {
                System.out.println(color.RED + "Invalid input. Please enter a number." + color.RESET);
                scanner.next();
            }
        }
        usersClubsService.deleteUserClub(coachId.get(choice-1), clubId);
    }

    public void deleteAthlete(Long clubId) throws SQLException {
        List<Long> athleteId = usersClubsService.getAthletesByClub(clubId);
        if (athleteId.size() == 0){
            System.out.println(color.RED + "\nThere are no athletes in this club." + color.RESET);
            return;
        }
        System.out.println(color.BLUE + "\nList of the athletes." + color.RESET);
        for (int i = 0; i <= athleteId.size() - 1; i++) {
            if (i < 9) {
                System.out.println(" " + (i + 1) + ": Name: " + userService.getUserById(athleteId.get(i)).getName() +
                        "\n    Email: " + userService.getUserById(athleteId.get(i)).getEmail() +
                        "\n    Phone: " + userService.getUserById(athleteId.get(i)).getPhone());
            } else {
                System.out.println((i + 1) + ": Name: " + userService.getUserById(athleteId.get(i)).getName() +
                        "\n    Email: " + userService.getUserById(athleteId.get(i)).getEmail() +
                        "\n    Phone: " + userService.getUserById(athleteId.get(i)).getPhone());
            }
        }
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("Choose a coach: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice > 0 && choice <= athleteId.size()) {
                    validChoice = true;
                } else {
                    System.out.println(color.RED + "Invalid choice. Please choose a number between 1 and " + athleteId.size() + "." + color.RESET);
                }
            } else {
                System.out.println(color.RED + "Invalid input. Please enter a number." + color.RESET);
                scanner.next();
            }
        }
        usersClubsService.deleteUserClub(athleteId.get(choice-1), clubId);
    }
}
