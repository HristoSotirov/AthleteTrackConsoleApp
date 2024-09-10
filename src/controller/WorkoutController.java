package controller;

import DTO.AnalysisDTO;
import DTO.WorkoutsDTO;
import DTO.WorkoutTypeDTO;
import client.Colors;
import entity.Workouts;
import entity.WorkoutsTypes;
import service.UserService;
import service.UsersClubsService;
import service.WorkoutService;
import service.WorkoutTypeService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class WorkoutController {
    private final WorkoutService workoutService = new WorkoutService();
    private final WorkoutTypeService workoutTypeService = new WorkoutTypeService();
    private final UsersClubsService usersClubsService = new UsersClubsService();
    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);
    private final Colors color = new Colors();

    public WorkoutController(){

    }

    public void addWorkout(Long coachId, Long clubId) throws SQLException {
        List<Long> athletesId = chooseAthletesId(clubId);
        if (athletesId.isEmpty()){
            return;
        }

        Long workoutTypeId = chooseWorkoutTypeId(clubId);

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        LocalDateTime doneAt = enterWorkoutDateTime();

        System.out.println("");

        for (Long athleteId : athletesId) {
            Workouts workout = new Workouts(
                    athleteId,
                    clubId,
                    workoutTypeId,
                    description,
                    doneAt,
                    coachId
            );

            if(workoutService.workoutExists(athleteId, doneAt)){
                System.out.println(color.RED + "It looks like athlete " + userService.getUserById(athleteId).getUsername() + " already has a workout recordet at this date and time." +
                        "\nThe new workout was not saved." + color.RESET);
            } else {
                workoutService.addWorkout(workout);
                System.out.println(color.BLUE + userService.getUserById(athleteId).getUsername() + "'s workout entered successfully!" + color.RESET);
            }
        }

    }

    public List<Long> chooseAthletesId(Long clubId) throws SQLException {

        List<Long> chosenAthletesId = new ArrayList<>();
        List<Long> athleteId = usersClubsService.getAthletesByClub(clubId);
        if (athleteId.size() == 0){
            System.out.println(color.RED + "\nThere are no athletes in this club." + color.RESET);
            return chosenAthletesId;
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

        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("Choose athletes (space-separated numbers, e.g. 1 3 5): ");
            String input = scanner.nextLine().trim();

            if (!input.matches("^[0-9\\s]+$")) {
                System.out.println(color.RED + "Invalid format. Please enter only numbers separated by spaces." + color.RESET);
                continue;
            }

            String[] choices = input.split("\\s+");
            Set<Integer> uniqueChoices = new HashSet<>();
            boolean allValid = true;

            for (String choice : choices) {
                try {
                    int index = Integer.parseInt(choice);
                    if (index > 0 && index <= athleteId.size()) {
                        if (!uniqueChoices.add(index)) {
                            System.out.println(color.RED + "Duplicate choice. Please avoid duplicates." + color.RESET);
                            allValid = false;
                            break;
                        }
                    } else {
                        System.out.println(color.RED + "Invalid choice. Please choose numbers between 1 and " + athleteId.size() + "." + color.RESET);
                        allValid = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(color.RED + "Invalid input: " + choice + " is not a number." + color.RESET);
                    allValid = false;
                    break;
                }
            }

            if (allValid) {
                validChoice = true;
                for (int index : uniqueChoices) {
                    Long athleteIdChosen = athleteId.get(index - 1);
                    chosenAthletesId.add(athleteIdChosen);
                }
            } else {
                System.out.println(color.RED + "Please try again." + color.RESET);
            }
        }

        return chosenAthletesId;
    }

    public Long chooseWorkoutTypeId(Long clubId) throws SQLException {
        List<WorkoutsTypes> workoutsTypes = workoutTypeService.findAll(clubId);

        if (workoutsTypes.isEmpty()) {
            System.out.println(color.RED + "\nThere are no workouts types in this club yet." + color.RESET);

            System.out.print("Do you want to add a new workout type and use it? (Y/N): ");
            String input = scanner.nextLine().trim();

            if (input.trim().toUpperCase().equals("Y")) {
                return addWorkoutType(clubId);

            } else {
                System.out.println(color.RED + "No workout type selected." + color.RESET);
                return 0L;
            }
        }

        System.out.println(color.BLUE + "\nList of the workouts types." + color.RESET);
        System.out.println(" 0: Add and use new workout type");
        for (int i = 0; i <= workoutsTypes.size() - 1; i++) {
            if (i < 9) {
                System.out.println(" " + (i + 1) + ": " + workoutsTypes.get(i).getTypes());
            } else {
                System.out.println((i + 1) + ": " + workoutsTypes.get(i).getTypes());
            }
        }


        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("Choose a workout type: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 0){
                    return addWorkoutType(clubId);
                }
                if (choice > 0 && choice <= workoutsTypes.size()) {
                    validChoice = true;
                } else {
                    System.out.println(color.RED + "Invalid choice. Please choose a number between 1 and " + workoutsTypes.size() + "." + color.RESET);
                }
            } else {
                System.out.println(color.RED + "Invalid input. Please enter a number." + color.RESET);
                scanner.nextLine();
            }
        }
        scanner.nextLine();

        Long choosenWorkoutTypeId = workoutsTypes.get(choice-1).getId();

        return  choosenWorkoutTypeId;
    }


    public Long addWorkoutType(Long clubId) throws SQLException {

        String workoutType;
        do {
            System.out.print("Workout Type: ");
            workoutType = scanner.nextLine().trim();

            if (workoutType.isEmpty()) {
                System.out.println(color.RED + "This field cannot be empty. Please try again." + color.RESET);
                continue;
            }

            if (workoutTypeService.workoutTypeExists(clubId, workoutType)) {
                System.out.println(color.RED + "This type already exists. Please try again." + color.RESET);
            }
        } while (workoutTypeService.workoutTypeExists(clubId, workoutType) || workoutType.trim().isEmpty());

        WorkoutTypeDTO workoutTypeDTO = new WorkoutTypeDTO(clubId, workoutType);

        return workoutTypeService.addWorkoutType(workoutTypeDTO);
    }

    public LocalDateTime enterWorkoutDateTime() {
        LocalDateTime dateTime = null;
        boolean isValidDateTime;

        do {
            System.out.print("Date and time (DD/MM/YYYY HH:MM): ");
            String input = scanner.nextLine();

            try {
                dateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                
                if (dateTime.isAfter(LocalDateTime.now())) {
                    System.out.println(color.RED + "The date and time cannot be in the future. Please enter a valid date and time." + color.RESET);
                    isValidDateTime = false;
                } else {
                    isValidDateTime = true;
                }

            } catch (DateTimeParseException e) {
                System.out.println(color.RED + "Invalid date and time format. Please try again." + color.RESET);
                isValidDateTime = false;
            }

        } while (!isValidDateTime);

        return dateTime;
    }




    public void deleteWorkout(Long clubId) throws SQLException {
        List<Long> athletesId = chooseAthletesId(clubId);
        if (athletesId.isEmpty()){
            return;
        }

        LocalDateTime doneAt = enterWorkoutDateTime();

        System.out.println("");

        for (Long athleteId : athletesId) {
            if(workoutService.workoutExists(athleteId, doneAt)){
                System.out.println(color.BLUE  + userService.getUserById(athleteId).getUsername() + "'s workout was deleted successfully!" + color.RESET);
            } else {
                workoutService.deleteWorkout(athleteId, doneAt);
                System.out.println(color.RED  + userService.getUserById(athleteId).getUsername() + "'s workout was not found!" + color.RESET);
            }
        }

    }

    public void getAnalysisForAthlete(Long athleteId) throws SQLException {
        System.out.println(color.BLUE + "\nEnter start and end date for the analysis." + color.RESET);

        LocalDateTime startDate;
        LocalDateTime endDate;
        do {
            startDate = enterWorkoutDateTime();
            endDate = enterWorkoutDateTime();
            if (startDate.isAfter(endDate)){
                System.out.println(color.RED + "\nStart date cannot be after end date. Please enter the dates again." + color.RESET);

            }
        } while (startDate.isAfter(endDate));



        getAnalysis(athleteId, startDate, endDate);
    }

    public void getAnalysisForManyAthletes(Long clubId) throws SQLException {
        System.out.println(color.BLUE + "\nEnter start and end date for the analysis." + color.RESET);

        LocalDateTime startDate;
        LocalDateTime endDate;
        do {
            startDate = enterWorkoutDateTime();
            endDate = enterWorkoutDateTime();
            if (startDate.isAfter(endDate)){
                System.out.println(color.RED + "\nStart date cannot be after end date. Please enter the dates again." + color.RESET);

            }
        } while (startDate.isAfter(endDate));


        List<Long> athletesId = chooseAthletesId(clubId);
        if (athletesId.isEmpty()){
            return;
        }

        for (Long athleteId : athletesId) {
            getAnalysis(athleteId, startDate, endDate);
        }

    }

    public void getAnalysisForAllAthletes(Long clubId) throws SQLException {
        System.out.println(color.BLUE + "\nEnter start and end date for the analysis." + color.RESET);

        LocalDateTime startDate;
        LocalDateTime endDate;
        do {
            startDate = enterWorkoutDateTime();
            endDate = enterWorkoutDateTime();
            if (startDate.isAfter(endDate)){
                System.out.println(color.RED + "\nStart date cannot be after end date. Please enter the dates again." + color.RESET);

            }
        } while (startDate.isAfter(endDate));


        List<Long> athletesId = usersClubsService.getAthletesByClub(clubId);

        for (Long athleteId : athletesId) {
            getAnalysis(athleteId, startDate, endDate);
        }

    }


    public void getAnalysis(Long athleteId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {

        AnalysisDTO analysisDTO = workoutService.getAnalysisForAthleteDTO(athleteId, startDate, endDate);

        System.out.println(color.BLUE + "\n========== Athlete Workout Analysis =========="  + color.RESET);
        System.out.println("Total workouts: " + analysisDTO.getTotalWorkouts());

        System.out.println("\n---- Workouts by Types ----");
        System.out.printf("%-20s %-10s%n", "Workout Type", "Total");
        System.out.println("----------------------------");

        for (Map.Entry<String, Integer> entry : analysisDTO.getTotalWorkoutsByType().entrySet()) {
            System.out.printf("%-20s %-10d%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n---- Workout Details ----");
        System.out.printf("%-15s %-15s %-20s %-30s %-20s %-15s%n",
                "Athlete", "Club", "Workout Type", "Description", "Date & Time", "Entered By");
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        for (WorkoutsDTO workout : analysisDTO.getWorkouts()) {
            System.out.printf("%-15s %-15s %-20s %-30s %-20s %-15s%n",
                    workout.getAthlete(),
                    workout.getClub(),
                    workout.getWorkoutTypeId(),
                    workout.getDescription(),
                    workout.getDoneAt(),
                    workout.getEnteredBy());
        }

        System.out.println("");
    }


}
