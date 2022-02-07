package com.oracle.tour.dao;

import java.util.List;

import com.oracle.tour.dto.Board;
import com.oracle.tour.dto.Board_like;
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

	int BLikeChk(Board_like board_like);

	int BLikeChk_n(Board_like board_like);
	
	int insert_bl(Board_like board_like);

	int like_b(Board board);
	
	int update_bl(Board_like board_like);

	int dislike_bl(Board_like board_like);

	int dislike_b(Board board);
	
	String BLikeYN(Board_like board_like);

	int selectLikeCnt(Board board);



}
