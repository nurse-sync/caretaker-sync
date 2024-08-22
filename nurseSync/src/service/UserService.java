package service;

import java.util.List;

import pojo.CaretakerPojo;
import pojo.CaretakerPreferences;
import pojo.UserPojo;

public interface UserService {
	boolean createUser(UserPojo user);
    UserPojo getUserById(int userId);
    UserPojo getUserByUsername(String username);
    List<UserPojo> getAllUsers();
    boolean updateUser(UserPojo user);
    boolean deleteUser(int userId);
    List<CaretakerPojo> findMatchingCaretakers(CaretakerPreferences preferences);
    boolean sendRequestToCaretaker(int userId, int caretakerId, String serviceLocation, String patientName, int patientAge, String patientGender);
}
