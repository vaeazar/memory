package com.maze.memory.service;

import com.maze.memory.dto.BoardRequest;
import com.maze.memory.utils.ResultMessage;
import com.maze.memory.utils.ResultStatusEnum;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    public ResultMessage createBoard(BoardRequest rq) {
        ResultStatusEnum status = ResultStatusEnum.OK;
        String message = "";
        Object data = null;

        try {

            status = ResultStatusEnum.BAD_REQUEST;
            message = "성공쓰";

        } catch (Exception e) {

            status = ResultStatusEnum.BAD_REQUEST;
            message = "실패쓰";

        }
        return ResultMessage.builder()
                .status(status)
                .message(message)
                .build();
    }

}
