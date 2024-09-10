package service;

import DTO.AnalysisDTO;
import DTO.WorkoutsDTO;
import entity.Analysis;
import entity.Workouts;
import repository.ClubsRepository;
import repository.UsersRepository;
import repository.WorkoutsRepository;
import repository.WorkoutsTypesRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WorkoutService {

    private final WorkoutsRepository workoutsRepository = new WorkoutsRepository();
    private final WorkoutsTypesRepository workoutsTypesRepository = new WorkoutsTypesRepository();
    private final UsersRepository usersRepository = new UsersRepository();
    private final ClubsRepository clubsRepository = new ClubsRepository();

    public WorkoutService() {

    }

    public void addWorkout(Workouts workout) {
        workoutsRepository.addWorkout(workout);
    }

    public boolean workoutExists(Long athleteId, LocalDateTime doneAt){
        return workoutsRepository.workoutExists(athleteId, doneAt);
    }

    public void deleteWorkout(Long athleteId, LocalDateTime doneAt){
        workoutsRepository.deleteWorkout(athleteId, doneAt);
    }

    public AnalysisDTO getAnalysisForAthleteDTO(Long athleteId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {

        Analysis analysis = workoutsRepository.getAnalysisForAthlete(athleteId, startDate, endDate);

        AnalysisDTO analysisDTO = new AnalysisDTO();
        analysisDTO.setTotalWorkouts(analysis.getTotalWorkouts());
        analysisDTO.setTotalWorkoutsByType(analysis.getTotalWorkoutsByType());

        List<WorkoutsDTO> workoutsDTOList = new ArrayList<>();
        for (Workouts workout : analysis.getWorkouts()) {
            WorkoutsDTO workoutsDTO = new WorkoutsDTO();

            workoutsDTO.setAthlete(usersRepository.getUserById(workout.getAthleteId()).getName());
            workoutsDTO.setClub(clubsRepository.getClubById(workout.getClubId()).getName());
            workoutsDTO.setWorkoutTypeId(workoutsTypesRepository.findById(workout.getWorkoutTypeId()).getTypes());
            workoutsDTO.setDescription(workout.getDescription());
            workoutsDTO.setDoneAt(workout.getDoneAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            workoutsDTO.setEnteredBy(usersRepository.getUserById(workout.getEnteredBy()).getName());

            workoutsDTOList.add(workoutsDTO);
        }

        analysisDTO.setWorkouts(workoutsDTOList);

        return analysisDTO;
    }

}
