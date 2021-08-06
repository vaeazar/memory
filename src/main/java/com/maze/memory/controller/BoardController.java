package com.maze.memory.controller;

import com.maze.memory.dto.BoardRequest;
import com.maze.memory.service.BoardService;
import com.maze.memory.utils.ResultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class BoardController {
    private static BoardService boardservice;

    public ResponseEntity<ResultMessage> crearte(BoardRequest rq) {
        return ResponseEntity.ok(boardservice.createBoard(rq));
    }

//    public ResponseEntity<ResultMessage> read(BoardRequest rq) {
//    }
//
//    public ResponseEntity<ResultMessage> update(BoardRequest rq) {
//    }
//
//    public ResponseEntity<ResultMessage> delete(BoardRequest rq) {
//    }
}
