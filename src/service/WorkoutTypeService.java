package service;

import DTO.WorkoutTypeDTO;
import entity.WorkoutsTypes;
import repository.WorkoutsTypesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutTypeService {

    private final WorkoutsTypesRepository workoutsTypesRepository = new WorkoutsTypesRepository();

    public WorkoutTypeService() {
    }

    public Long addWorkoutType(WorkoutTypeDTO workoutTypeDTO){

        WorkoutsTypes workoutType = new WorkoutsTypes(
                workoutTypeDTO.getClubId(),
                workoutTypeDTO.getTypes()
        );

        return workoutsTypesRepository.addWorkoutType(workoutType).getId();
    }


    public List<WorkoutsTypes> findAll(Long clubId) {
        return workoutsTypesRepository.findAll(clubId);
    }


    public boolean workoutTypeExists(Long clubId, String type) {
        return workoutsTypesRepository.workoutTypeExists(clubId, type);
    }

}
