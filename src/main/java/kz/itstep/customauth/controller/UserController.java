package kz.itstep.customauth.controller;

import kz.itstep.customauth.model.User;
import kz.itstep.customauth.repo.UserRepository;
import kz.itstep.customauth.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {
    private final UserRepository userRepository;
    AuthorizationService authorizationService;
    public UserController(UserRepository userRepository, AuthorizationService authorizationService) {
        this.userRepository = userRepository;
        this.authorizationService = authorizationService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        if(user.getLogin()==null){
            return new ResponseEntity<>("no user", HttpStatus.NOT_FOUND);
        }
        else {
            User new_user = new User(user.getLogin(),user.getPassword());
            userRepository.save(new_user);
            return new ResponseEntity<>("your token is: "+ authorizationService.generateToken(user.getLogin()), HttpStatus.OK);
        }
    }

    @PostMapping("/authorization")
    public ResponseEntity<String> authorization(@RequestBody User user){
        if(user.getLogin()==null){
            return new ResponseEntity<>("no user", HttpStatus.NOT_FOUND);
        }
        else{
            if(userRepository.findUserByLoginAndPassword(user.getLogin(), user.getPassword()) != null){
                return new ResponseEntity<>("your token is: "+ authorizationService.generateToken(user.getLogin()), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("no user", HttpStatus.NOT_FOUND);
            }
        }
    }
}
