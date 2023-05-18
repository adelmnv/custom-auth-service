package kz.itstep.customauth.service;

import kz.itstep.customauth.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final UserRepository userRepository;
    private final Integer SHIFT = 4;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(String login){
        if(login == null)
            return "";
        else{
           return shepherLogin(login);
        }
    }

    private String shepherLogin(String login){
        StringBuilder token = new StringBuilder();
        for(char s : login.toCharArray()){
            token.append((char)(s+4));

        }
        return token.toString();
    }

    private String deshepherLogin(String token){
        StringBuilder login = new StringBuilder();
        for(char s : token.toCharArray()){
            login.append((char)(s-4));

        }
        return login.toString();
    }

    public boolean isValid(String token){
        String login = deshepherLogin(token);
        if(userRepository.findUserByLogin(login) != null)
            return true;
        else
            return false;
    }
}
