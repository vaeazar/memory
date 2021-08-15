package com.maze.memory.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardRequest {
    private long boardNo;
    private String boardKind;
    private String boardHeader;
    private String boardTitle;
    private String boardContent;

    @Builder
    public BoardRequest(String boardKind, String boardHeader, String boardTitle, String boardContent){
        this.boardKind = boardKind;
        this.boardHeader = boardHeader;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }



}
