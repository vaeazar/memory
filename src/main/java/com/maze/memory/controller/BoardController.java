package com.maze.memory.controller;

import com.maze.memory.domain.response.ResponseFormat;
import com.maze.memory.dto.BoardRequest;
import com.maze.memory.service.BoardService;
import com.maze.memory.utils.MazeConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    private static BoardService boardservice;

    //생성
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseFormat> createBoard(BoardRequest rq) {
        return boardservice.createBoard(rq);
    }

    //전체 글
    @GetMapping(value = "/read/all")
    public ResponseEntity<ResponseFormat> readBoardAll(
            @RequestParam(required = false) int pageNum,
            @RequestParam(required = false) int pageSize
    ) {
        return boardservice.readBoardAll(pageNum, pageSize);
    }

    //userId로 검색
    @GetMapping(value = "/read/user/{userid}")
    public ResponseEntity<ResponseFormat> readBoardByUserId(
            @PathVariable("userid") long userid,
            @RequestParam(required = false) int pageNum,
            @RequestParam(required = false) int pageSize
    ) {
        return boardservice.readBoardByUserId(userid, pageNum, pageSize);
    }

    //머릿말로 검색
    @GetMapping(value = "/read/header/{header}")
    public ResponseEntity<ResponseFormat> readBoardByHeader(
            @PathVariable("header") String header,
            @RequestParam(required = false) int pageNum,
            @RequestParam(required = false) int pageSize

    ) {
        return boardservice.readBoardByHeader(header, pageNum, pageSize);
    }

    //제목, 글 내용으로 검색
    @GetMapping(value = "/read/contents/{contents}")
    public ResponseEntity<ResponseFormat> readBoardContents(
            @PathVariable("contents") String contents,
            @RequestParam(required = false, defaultValue = MazeConstant.FIND_TITLE) String searchType,
            @RequestParam(required = false) int pageNum,
            @RequestParam(required = false) int pageSize
    ) {
        return boardservice.readBoardContents(contents, searchType, pageNum, pageSize);
    }

    //글 업데이트
    @PatchMapping(value = "/update")
    public ResponseEntity<ResponseFormat> updateBoard(BoardRequest rq) {
        return boardservice.updateBoard(rq);
    }

    //글 삭제
    @DeleteMapping(value = "/delete/{boardNo}")
    public ResponseEntity<ResponseFormat> deleteBoard(@RequestParam(required = false) long boardNo) {
        return boardservice.deleteBoard(boardNo);
    }

}
