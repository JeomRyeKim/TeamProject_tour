package com.oracle.tour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.tour.dao.HJDao;
import com.oracle.tour.dto.Board;

@Service
@Transactional
public class HJServiceImpl implements HJService {
	private final HJDao hd;

	@Autowired
	public HJServiceImpl(HJDao hd) {
		this.hd = hd;
	}

	@Override
	public int total() {
		System.out.println("HJServiceImpl total start..");
		int totCnt = hd.total();
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
	public Board detail(int b_no) {
		System.out.println("HJServiceImpl detail start..");
		Board board = hd.detail(b_no);
		
		return board;
	}

	@Override
	public List<Board> getKindList(Board board, String kind) {
		System.out.println("HJServiceImpl getKindList start");
		List<Board> getKindList = hd.getKindList(board, kind);
		
		return getKindList;
	}




	

}
