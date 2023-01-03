package com.example.tubes02_prototype;

public class LoginUser {
    protected String email;
    protected String password;
    protected String role;
    protected String token;

    public LoginUser(String email, String password, String role, String token) {
        this.email = email;
        this. password = password;
        this.role = role;
        this.token = token;
    }
}
