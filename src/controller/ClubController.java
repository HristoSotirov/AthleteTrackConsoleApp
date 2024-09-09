package controller;

import DTO.ClubDTO;
import client.Colors;
import entity.Clubs;
import service.ClubService;
import service.RegistrationService;
import service.SportService;
import service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClubController {
    ;
    private final ClubService clubService = new ClubService();
    private final Colors color = new Colors();
    private final Scanner scanner = new Scanner(System.in);

    public void verifyClub(Long loggedAdminId) throws SQLException {
        while (true){
            List<String> unverifiedClub = clubService.getUnverifiedClubs();
            System.out.println(color.BLUE + "\nList of unverified clubs." + color.RESET);
            if (unverifiedClub.size() == 0) {
                System.out.println(color.BLUE + "\nAll clubs are verified." + color.RESET);
                return;
            }
            System.out.println(" 0: Back to Main Menu");
            for (int i = 0; i <= unverifiedClub.size() - 1; i++) {
                if (i < 9) {
                    System.out.println(" " + (i + 1) + ": " + unverifiedClub.get(i));
                } else {
                    System.out.println((i + 1) + ": " + unverifiedClub.get(i));
                }
            }
            int choice = 0;
            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("Choose a club to verify: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice == 0) {
                        return;
                    }
                    if (choice > 0 && choice <= unverifiedClub.size()) {
                        validChoice = true;
                    } else {
                        System.out.println(color.RED + "Invalid choice. Please choose a number between 1 and " + unverifiedClub.size() + "." + color.RESET);
                    }
                } else {
                    System.out.println(color.RED + "Invalid input. Please enter a number." + color.RESET);
                    scanner.next();
                }
            }
            Long choosenClubId = clubService.getClubIdByName(unverifiedClub.get(choice - 1).trim());
            clubService.verifyClub(choosenClubId, loggedAdminId);
            System.out.println(color.BLUE + "Club is verified successfully." + color.RESET);
        }
    }

    public ClubDTO getClub(Long clubId) throws SQLException {
        return clubService.getClub(clubId);
    }

    public boolean isClubVerified(Long clubId) throws SQLException {
        return clubService.isClubVerified(clubId);
    }
}
