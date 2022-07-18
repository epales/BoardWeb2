package com.ezen.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ezen.domain.Board;

public interface BoardService {

	void insertBoard(Board board);

	void updateBoard(Board board);

	void deleteBoard(Board board);

	Board getBoard(Board board);

	Page<Board> getBoardList();

}