package com.SecurityService.DTO;

public class AuthResponseDTO {
    //Once this token is initialized, it can't be initilized again
    private final String token;

    public AuthResponseDTO(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
