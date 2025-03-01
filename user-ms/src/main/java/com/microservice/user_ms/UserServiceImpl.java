package com.microservice.user_ms;

import com.microservice.user_ms.config.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public boolean createUser(UserRequest user) {
        Optional<User> userExist = userRepository.findByUsername(user.getUsername());
        if(userExist.isPresent())
            return false;
        Set<Role> roles = user.getRoles().stream().map(role ->
            Role.valueOf(role.toUpperCase())
        ).collect(Collectors.toSet());
            User user1 = User.builder().name(user.getName()).email(user.getEmail())
                .username(user.getUsername()).password(passwordEncoder.encode(user.getPassword())).createdBy(user.getUsername())
                    .lastModifiedBy(user.getUsername()).roles(roles).build();
        userRepository.save(user1);
        return true;
    }

    public boolean fallBackResponse(UserRequest user, Throwable ex){
        logger.trace("fallback executed");
        return false;
    }

    @Override
    public String authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty())
            throw new RuntimeException();

        try{
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (BadCredentialsException ex){
            throw new RuntimeException();
        }



        return jwtService.generateToken(user.get());

    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
       if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    @Cacheable(value = "users", key = "#username")
    public User getUserbyUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        throw new RuntimeException();
    }



    public static UserInfoDto convertUsertouserInfoDto(User user){
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setName(user.getName());
        userInfoDto.setUsername(user.getUsername());
        userInfoDto.setEmail(user.getEmail());

        return userInfoDto;
    }

    @Override
    public List<UserInfoDto> getUsers() {
        List<User> users = userRepository.findAll();
       return users.stream().map(UserServiceImpl::convertUsertouserInfoDto).collect(Collectors.toList());
    }

    @Override
    public boolean updateUser(String username, String updatedUsername) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<User> user1 = userRepository.findByUsername(updatedUsername);
        if(user1.isPresent())
            return false;
        if(user.isPresent()){
            User u = user.get();
            u.setUsername(updatedUsername);
            userRepository.save(u);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(String email, String updatedPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            User u = user.get();

            u.setPassword(passwordEncoder.encode(updatedPassword));
            userRepository.save(u);
            return true;
        }
        return false;
    }

}
