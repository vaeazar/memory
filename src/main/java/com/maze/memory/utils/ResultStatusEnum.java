package com.maze.memory.utils;

public enum ResultStatusEnum {

    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST");

    int statusCode;
    String code;

    ResultStatusEnum(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
