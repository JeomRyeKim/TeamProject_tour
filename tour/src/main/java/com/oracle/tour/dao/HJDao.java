package com.oracle.tour.dao;

import java.util.List;

import com.oracle.tour.dto.Board;
import com.oracle.tour.dto.Member;

public interface HJDao {
	
	Member getMemberDetail(String m_id);

	int total(Board board);
	
	List<Board> listBoard(Board board);

	int getHit(Board board);

	Board BoardDetail(Board board);

	String getNickname(String m_id);

	int insert(Board board);

	int BoardDelete(Board board);
	


}
