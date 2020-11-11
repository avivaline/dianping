package com.graduation.recommendsystem.dianping.request;

import javax.validation.constraints.NotBlank;

public class LoginReq {
    @NotBlank(message = "Phone Number can't be null")
    private String telphone;
    @NotBlank(message = "password can't be null")
    private String password;

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
