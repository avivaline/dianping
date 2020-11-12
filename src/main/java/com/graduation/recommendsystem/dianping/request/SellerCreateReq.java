package com.graduation.recommendsystem.dianping.request;

import javax.validation.constraints.NotBlank;

public class SellerCreateReq {
    @NotBlank(message = "name can't be null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
