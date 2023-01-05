package com.example.tubes02_prototype;

public class LoginResult {
    String token;

    public LoginResult(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
