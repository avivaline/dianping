package com.graduation.recommendsystem.dianping.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterReq {

    @NotBlank(message = "Phone Number can't be null")
    private String telphone;
    @NotBlank(message = "password can't be null")
    private String password;
    @NotBlank(message = "nickName can't be null")
    private String nickName;
    @NotNull(message = "Gender can't be null")
    private Integer gender;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
