package com.graduation.recommendsystem.dianping.common;

public enum EmBusinessError {

    // common error type begin at 10000
    NO_OBJECT_FOUND(10001,"request object doesn't exit"),
    UNKNOWN_ERROR(10002,"unknown error"),
    NO_HANDLER_FOUND(10003,"no handler found"),
    BIND_EXCEPTION_ERROR(10004,"bind exception error"),;

    private Integer errCode;

    private String errMsg;

    EmBusinessError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
