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
	public Board BoardDetail(Board board) {
		System.out.println("HJServiceImpl BoardDetail start..");
		Board boardDetail = hd.BoardDetail(board);
		
		return boardDetail;
	}




	

}
