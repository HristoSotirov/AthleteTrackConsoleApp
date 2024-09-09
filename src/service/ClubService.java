package service;

import DTO.ClubDTO;
import entity.Clubs;
import repository.ClubsRepository;
import repository.SportsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubService {

    private final ClubsRepository clubsRepository = new ClubsRepository();
    private final SportsRepository sportsRepository = new SportsRepository();

    public ClubService() {
    }

    public boolean isClubNameUnique(String name) {
        return clubsRepository.isClubNameUnique(name);
    }

    public boolean isClubPhoneUnique(String phone) {
        return clubsRepository.isClubPhoneUnique(phone);
    }

    public boolean isClubEmailUnique(String email) {
        return clubsRepository.isClubEmailUnique(email);
    }

    public List<String> getUnverifiedClubs() throws SQLException {
        return clubsRepository.getUnverifiedClubs();
    }

    public ClubDTO getClub(Long clubId) throws SQLException {
        Clubs club = clubsRepository.getClubById(clubId);

        ClubDTO clubDTO = new ClubDTO(
                club.getName(),
                sportsRepository.getSportNameById(club.getSportId()),
                club.getAddress(),
                club.getPhone(),
                club.getEmail()
        );


        return clubDTO;
    }

    public boolean isClubVerified(Long clubId) throws SQLException {
        return clubsRepository.isClubVerified(clubId);
    }

    public void verifyClub(Long clubId, Long verifierId) throws SQLException {
        clubsRepository.verifyClub(clubId, verifierId);
    }


    public Long getClubIdByName(String name) {
        return clubsRepository.getClubIdByName(name);
    }
}
