package com.microservice.user_ms;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface UserService {

    @Transactional
    public boolean createUser(UserRequest user);
    public String authenticate(String username, String password);
    public User getUser(String username);
    public User getUserbyUsername(String username);
    public List<UserInfoDto> getUsers();
    @Transactional
    public boolean updateUser(String username, String updatedUsername);
    @Transactional
    public boolean updatePassword(String email, String Password);
}
