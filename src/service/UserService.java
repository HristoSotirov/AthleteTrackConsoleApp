package service;

import DTO.UserDTO;
import entity.Users;
import repository.UsersRepository;
import repository.UsersTypesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private final UsersRepository usersRepository = new UsersRepository();
    private final UsersTypesRepository usersTypesRepository = new UsersTypesRepository();

    public UserService() {
    }

    public boolean isUsernameUnique(String username) {
        return usersRepository.isUsernameUnique(username);
    }

    public boolean isPhoneUnique(String phone) {
        return usersRepository.isPhoneUnique(phone);
    }

    public boolean isEmailUnique(String email) {
        return usersRepository.isEmailUnique(email);
    }

    public String getUserTypeByUserId(Long userId) {
        Long userTypeId = usersRepository.getUserById(userId).getUserType();
        String userType = usersTypesRepository.getTypeById(userTypeId);
        return userType;
    }

    public UserDTO getUserById(Long userId) {
        Users user = usersRepository.getUserById(userId);

        UserDTO userDTO = new UserDTO(
                user.getName(),
                user.getUsername(),
                user.getPhone(),
                user.getEmail(),
                usersTypesRepository.getTypeById(user.getUserType())
        );

        return userDTO;
    }

    public void updateUserName(Long userId, String newName) {
        usersRepository.updateUserName(userId, newName);
    }

    public void updateUsername(Long userId, String newUsername) {
        usersRepository.updateUsername(userId, newUsername);
    }

    public void updateUserPassword(Long userId, String newPassword) {
        usersRepository.updateUserPassword(userId, RegistrationService.encryptPassword(newPassword));
    }

    public void updateUserPhone(Long userId, String newPhone) {
        usersRepository.updateUserPhone(userId, newPhone);
    }

    public void updateUserEmail(Long userId, String newEmail) {
        usersRepository.updateUserEmail(userId, newEmail);
    }


}
