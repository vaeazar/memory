package com.maze.memory.utils;

import lombok.Builder;
import lombok.Data;

@Data
public class ResultMessage {
    private ResultStatusEnum status;
    private String message;
    private Object data;

    @Builder
    public ResultMessage(ResultStatusEnum status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;

    }

}
