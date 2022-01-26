package com.oracle.tour.service;

import java.util.List;

import com.oracle.tour.dto.Board;

public interface HJService {

	int total();

	List<Board> listBoard(Board board);

	Board detail(int b_no);


}
