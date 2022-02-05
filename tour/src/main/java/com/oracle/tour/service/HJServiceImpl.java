package com.oracle.tour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.tour.dao.HJDao;
import com.oracle.tour.dto.Board;
import com.oracle.tour.dto.Member;

@Service
@Transactional
public class HJServiceImpl implements HJService {
	private final HJDao hd;

	@Autowired
	public HJServiceImpl(HJDao hd) {
		this.hd = hd;
	}

	@Override
	public Member getMemberDetail(String m_id) {
		System.out.println("HJServiceImpl getMemberDetail start..");
		Member member = hd.getMemberDetail(m_id);
		
		return member;
	}
	
	@Override
	public int total(Board board) {
		System.out.println("HJServiceImpl total start..");
		int totCnt = hd.total(board);
		System.out.println("HJServiceImpl total totCnt->" + totCnt);
		return totCnt;
	}

	@Override
	public List<Board> listBoard(Board board) {
		System.out.println("HJServiceImpl listBoard start..");
		List<Board> listBoard = hd.listBoard(board);
		System.out.println("HJServiceImpl listBoard listBoard.size() : " + listBoard.size());
		
		return listBoard;
	}

	@Override
	public int getHit(Board board) {
		System.out.println("HJServiceImpl getHit start..");
		int b_hit = hd.getHit(board);
		
		return b_hit;
	}


	@Override
	public Board BoardDetail(Board board) {
		System.out.println("HJServiceImpl BoardDetail start..");
		Board boardDetail = hd.BoardDetail(board);
		
		return boardDetail;
	}

	@Override
	public String getNickname(String m_id) {
		System.out.println("HJServiceImpl getNickname start..");
		String m_nickname = hd.getNickname(m_id);
		
		return m_nickname;
	}

	@Override
	public int insert(Board board) {
		System.out.println("HJServiceImpl insert start..");
		int result = hd.insert(board);
		
		return result;
	}

	@Override
	public int BoardDelete(Board board) {
		System.out.println("HJServiceImpl BoardDelete start..");
		int boardDelete = hd.BoardDelete(board);
		
		return boardDelete;
	}




	

}
