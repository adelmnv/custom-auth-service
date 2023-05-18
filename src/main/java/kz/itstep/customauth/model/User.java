package kz.itstep.customauth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
public class User {
    @Id
    private Long id;
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login; this.password = password;
    }
}
