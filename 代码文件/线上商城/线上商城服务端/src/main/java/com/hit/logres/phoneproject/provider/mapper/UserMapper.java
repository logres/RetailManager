package com.hit.logres.phoneproject.provider.mapper;




import com.hit.logres.phoneproject.api.entity.User;

import java.util.List;

public interface UserMapper {
    void insertUser(User user) throws Exception;

    void deleteUser(Integer id) throws Exception;

    User findUserById(Integer id) throws Exception;

    List<User> getAllUser() throws Exception;

    void updateUser(User user) throws Exception;

    List<User> findUserByName(String name) throws Exception;
}
