package com.maze.memory.service;

import com.maze.memory.domain.Board;
import com.maze.memory.domain.response.ResponseFormat;
import com.maze.memory.dto.BoardRequest;
import com.maze.memory.repository.BoardRepository;
import com.maze.memory.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    //create
    @Transactional
    public ResponseEntity<ResponseFormat> createBoard(BoardRequest rq) {

        try {

            Board board = Board.builder()
                    .boardKind(rq.getBoardKind())
                    .boardHeader(rq.getBoardHeader())
                    .boardTitle(rq.getBoardTitle())
                    .boardContent(rq.getBoardContent())
                    .boardCreateDate(ZonedDateTime.now())
                    .build();

            boardRepository.save(board);
            return ResponseUtils.getResponseOk(board);

        } catch (Exception e) {
            return ResponseUtils.getBadRequest(e.getMessage());
        }
    }

    //전체조회
    public ResponseEntity<ResponseFormat> readBoardAll(int pageNum, int pageSize) {
        try {

            List<Board> boards = boardRepository.findAll(PageRequest.of(pageNum, pageSize)).getContent();
            return ResponseUtils.getResponseOk(boards);
        } catch (Exception e) {
            return ResponseUtils.getBadRequest(e.getMessage());
        }
    }


    //유저 이름으로 게시글 검색
    public ResponseEntity<ResponseFormat> readBoardByUserId(long userid, int pageNum, int pageSize) {
        try {

            List<Board> boards = boardRepository.findByUserNo(userid, PageRequest.of(pageNum, pageSize)).getContent();
            return ResponseUtils.getResponseOk(boards);
        } catch (Exception e) {
            return ResponseUtils.getBadRequest(e.getMessage());
        }
    }

    //머릿말로 검색
    public ResponseEntity<ResponseFormat> readBoardByHeader(String header, int pageNum, int pageSize) {
        try {

            List<Board> boards = boardRepository.findByBoardHeader(header, PageRequest.of(pageNum, pageSize)).getContent();
            return ResponseUtils.getResponseOk(boards);
        } catch (Exception e) {
            return ResponseUtils.getBadRequest(e.getMessage());
        }
    }

    //글 제목으로 게시글 검색

    public ResponseEntity<ResponseFormat> readBoardContents(String contents, String searchType, int pageNum, int pageSize) {
        try {
            List<Board> boards = boardRepository.findByContents(contents, searchType, PageRequest.of(pageNum, pageSize)).getContent();
            return ResponseUtils.getResponseOk(boards);
        } catch (Exception e) {
            return ResponseUtils.getBadRequest(e.getMessage());
        }
    }


    @Transactional
    //update
    public ResponseEntity<ResponseFormat> updateBoard(BoardRequest rq) {
        try {
            Board board = boardRepository.findById(rq.getBoardNo()).get();
            if(board !=null) {
                board.updateBoard(rq);
                boardRepository.save(board);
            }else{
                throw new Exception();
            }
            return ResponseUtils.getResponseOk(board);
        } catch (Exception e) {
            return ResponseUtils.getBadRequest(e.getMessage());
        }
    }

    @Transactional
    //delete
    public ResponseEntity<ResponseFormat> deleteBoard(long boardNo) {
        try {
            boardRepository.deleteById(boardNo);
            return ResponseUtils.getResponseOk(boardNo);


        } catch (Exception e) {
            return ResponseUtils.getBadRequest(e.getMessage());
        }

    }

}
