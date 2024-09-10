package service;

import repository.*;

import java.util.List;

public class UsersClubsService {
    private final UsersClubsRepository usersClubsRepository = new UsersClubsRepository();

    public UsersClubsService() {
    }

    public int getUserClubCount(Long userId) {
        return usersClubsRepository.getUserClubCount(userId);
    }

    public List<Long> getUserClubIds(Long userId) {
        return usersClubsRepository.getUserClubIds(userId);
    }

    public Long getSingleUserClubId(Long userId) {
        return usersClubsRepository.getSingleUserClubId(userId);
    }

    public boolean isUserClubExists(Long userId, Long clubId) {
        return usersClubsRepository.userClubExists(userId, clubId);
    }

    public List<Long> getCoachesByClub(Long clubId){
        return usersClubsRepository.getCoachesByClub(clubId);
    }

    public List<Long> getAthletesByClub(Long clubId){
        return usersClubsRepository.getAthletesByClub(clubId);
    }

    public void deleteUserClub(Long userId, Long clubId) {
        usersClubsRepository.deleteUserClub(userId, clubId);
    }

}
