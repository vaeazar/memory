package com.maze.memory.service;

import com.maze.memory.domain.Board;
import com.maze.memory.dto.BoardRequest;
import com.maze.memory.repository.BoardRepository;
import com.maze.memory.utils.ResultMessage;
import com.maze.memory.utils.ResultStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 게시판 crud
 *
 * @author hyejinzz
 * @since 2021-08-09
 **/
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    public ResultMessage createBoard(BoardRequest rq) {
        ResultStatusEnum status = ResultStatusEnum.OK;
        String message = "";
        Object data = null;

        try {

            repository.save(Board.builder()
                    .boardKind(rq.getBoardKind())
                    .boardHeader(rq.getBoardHeader())
                    .boardTitle(rq.getBoardTitle())
                    .boardContent(rq.getBoardContent())
                    .build());

            status = ResultStatusEnum.OK;
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
