package com.oracle.tour.dao;

import java.util.List;

import com.oracle.tour.dto.Board;

public interface HJDao {

	int total();
	
	List<Board> listBoard(Board board);

	Board BoardDetail(Board board);


}
