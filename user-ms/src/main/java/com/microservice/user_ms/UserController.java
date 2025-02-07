package com.microservice.user_ms;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody UserRequest user){
        if(userService.createUser(user)){
            return ResponseEntity.status(HttpStatus.CREATED).body("You SignedUp Successfully");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("username already exists");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticate(@RequestParam(name = "username") String username,
                                                @RequestParam(name = "password") String password) {
        String token;

        try {
            token = userService.authenticate(username, password);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("please check username or password correctly");
        }
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @GetMapping("/getuser")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal User currentuser){
        User user = userService.getUser(currentuser.getUsername());
        if(user != null){
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/getuser/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserbyUsername(username));
        }
        catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<UserInfoDto>> getUsers(){
        List<UserInfoDto> users = userService.getUsers();
        if(users.size() > 0){
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<String> updateUser(@AuthenticationPrincipal User user, @RequestParam(name = "username") String username){
        if(userService.updateUser(user.getUsername(), username)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Successfully updated");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username already exists");
    }

    @PutMapping("/updatepassword")
    public ResponseEntity<String> updatePassword(@AuthenticationPrincipal User user,
                                                 @RequestParam(name = "upassword") String updatedPassword){
        if(userService.updatePassword(user.getUsername(), updatedPassword)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("password successfully Changed");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong please try again");
    }

}
