package es.odec.pruebas.models;

public class AuthUser {
    public String token;
    public User user;

    public AuthUser(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
