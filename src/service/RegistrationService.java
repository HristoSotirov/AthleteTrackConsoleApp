package service;

import DTO.LoginDTO;
import DTO.RegisterClubAdminDTO;
import entity.Clubs;
import entity.Users;
import entity.UsersClubs;
import repository.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegistrationService {

    private final UsersRepository usersRepository = new UsersRepository();
    private final UsersClubsRepository usersClubsRepository = new UsersClubsRepository();
    private final ClubsRepository clubsRepository = new ClubsRepository();
    private final SportsRepository sportsRepository = new SportsRepository();
    private final UsersTypesRepository usersTypesRepository = new UsersTypesRepository();

    public RegistrationService() {
    }

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

    public Long login(LoginDTO loginDTO) throws SQLException {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        Long loggedUserId  = usersRepository.login(username, encryptPassword(password));

        return loggedUserId;
    }


    public void registerClubAndClubAdmin(RegisterClubAdminDTO registerClubAdminDTO) throws SQLException {

        Clubs newClub = new Clubs(
                registerClubAdminDTO.getClubName(),
                registerClubAdminDTO.getSportId(),
                registerClubAdminDTO.getAddress(),
                registerClubAdminDTO.getClubPhone(),
                registerClubAdminDTO.getClubEmail()
        );

        clubsRepository.addClub(newClub);

        Users newClubAdmin = new Users(
                registerClubAdminDTO.getUserName(),
                registerClubAdminDTO.getUserUsername(),
                encryptPassword(registerClubAdminDTO.getUserPassword()),
                registerClubAdminDTO.getUserPhone(),
                registerClubAdminDTO.getUserEmail(),
                usersTypesRepository.getIdByType("CLUB_ADMIN")
        );

        usersRepository.addUser(newClubAdmin);

        UsersClubs userClub = new UsersClubs(
                usersRepository.getUserIdByUsername(registerClubAdminDTO.getUserUsername()),
                clubsRepository.getClubIdByName(registerClubAdminDTO.getClubName())
        );

        usersClubsRepository.addUserClub(userClub);

    }
}
