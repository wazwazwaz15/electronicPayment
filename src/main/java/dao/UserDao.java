package dao;

import dto.UserRegisterRequest;
import model.User;


public interface UserDao {
    public Integer register(UserRegisterRequest request);


    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User getUserByUserID(Integer userID);
}
