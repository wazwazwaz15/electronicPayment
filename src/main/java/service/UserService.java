package service;

import dto.UserRegisterRequest;
import model.User;


public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserByUsername (String username);

    User getUserByUserId (Integer userID);

    User getUserByEmail(String email);


}
