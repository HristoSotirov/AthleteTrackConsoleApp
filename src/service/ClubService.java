package service;

import repository.ClubsRepository;

public class ClubService {

    private final ClubsRepository clubsRepository = new ClubsRepository();

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
}
