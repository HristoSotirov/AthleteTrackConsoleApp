package service;

import repository.SportsRepository;

import java.util.List;

public class SportService {

    private final SportsRepository sportsRepository = new SportsRepository();

    public SportService() {
    }

    public int displayAllSport() {
        List<String> allSports = sportsRepository.getAllSportsInAlphabeticalOrder();

        System.out.println("Sports:");
        for (int i = 0; i <= allSports.size() - 1 ; i++) {
            if (i < 9) {
                System.out.println(" " + (i + 1) + ": " + allSports.get(i));
            } else {
                System.out.println((i + 1) + ": " + allSports.get(i));
            }
        }
        return allSports.size();
    }

    public Long chooseSport(int num) {
        List<String> allSports = sportsRepository.getAllSportsInAlphabeticalOrder();
        return sportsRepository.getSportIdByName(allSports.get(num - 1));
    }

    public String getSportNameById(Long sportId) {
        return sportsRepository.getSportNameById(sportId);
    }
}