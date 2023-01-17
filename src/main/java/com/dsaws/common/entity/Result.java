package com.dsaws.common.entity;

public class Result<T> {

    private int code;

    private String message;

    private Translation[] data;

    public Result(int code, String message, Translation[] data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Translation[] getData() {
        return data;
    }

    public void setData(Translation[] data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
