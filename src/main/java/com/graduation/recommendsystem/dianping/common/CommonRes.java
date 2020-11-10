package com.graduation.recommendsystem.dianping.common;

public class CommonRes {

    // the result of return value: success or fail
    private String status;

    //if status == success, return json data
    //or status == fail, them return normal error code
    private Object data;

    // a normal method to return created object
    public static CommonRes create(Object result) {
        return CommonRes.create(result,"success");
    }

    public static CommonRes create(Object result, String status) {
        CommonRes commonRes = new CommonRes();
        commonRes.setStatus(status);
        commonRes.setData(result);
        return commonRes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
