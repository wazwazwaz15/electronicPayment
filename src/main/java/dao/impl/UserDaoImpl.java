package dao.impl;

import dao.UserDao;

import dto.UserRegisterRequest;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import rowmapper.UserRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class UserDaoImpl implements UserDao {

    @Value("${user.tablename}")
    private String tableName;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final String NEW_LINE = System.getProperty("line.separator");

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO").append(tableName).append("(username,password,email)").append(NEW_LINE);
        sql.append("VALUES (:username , :password , :email)");

        Map<String, Object> map = new HashMap<>();

        map.put("username", userRegisterRequest.getUserName());
        map.put("password", userRegisterRequest.getPassword());
        map.put("email", userRegisterRequest.getEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql.toString(), new MapSqlParameterSource(map), keyHolder);


        return keyHolder.getKey().intValue();
    }



    @Override
    public User getUserByUsername(String username) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT user_id ,username,password,email,insertdate,updatedate").append(NEW_LINE);
        sql.append("FROM").append(tableName).append(NEW_LINE);
        sql.append("WHERE username = :username");
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);

        List<User> userList = jdbcTemplate.query(sql.toString(), map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT user_id ,username,password,email,insertdate,updatedate").append(NEW_LINE);
        sql.append("FROM").append(tableName).append(NEW_LINE);
        sql.append("WHERE email = :email");
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<User> userList = jdbcTemplate.query(sql.toString(), map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getUserByUserID(Integer userID) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT user_id ,username,password,email,insertdate,updatedate").append(NEW_LINE);
        sql.append("FROM").append(tableName).append(NEW_LINE);
        sql.append("WHERE user_id = :userID");
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);

        List<User> userList = jdbcTemplate.query(sql.toString(), map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}
