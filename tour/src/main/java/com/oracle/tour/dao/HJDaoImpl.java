package com.oracle.tour.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.tour.dto.Board;

@Repository
public class HJDaoImpl implements HJDao {
	private final SqlSession session;
	@Autowired
	public HJDaoImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public int total() {
		int tot = 0;
		System.out.println("HJDaoImpl total Start....");
		try {
			tot = session.selectOne("hjBoardTotal");
			System.out.println("HJDaoImpl total tot->" + tot);
		} catch (Exception e) {
			System.out.println("HJDaoImpl total Exception->" + e.getMessage());
		}
		return tot;
	}

	@Override
	public List<Board> listBoard(Board board) {
		List<Board> listBoard = null;
		System.out.println("HJDaoImpl listBoard Start....");
		int b_kind = board.getB_kind();
		String search = board.getSearch();
		String items = board.getItems();
		System.out.println("HJDaoImpl listBoard search : " + search);
		System.out.println("HJDaoImpl listBoard items : " + items);
		try {
			if(b_kind == 0)	{// 전체
				// 검색을 안 하는 경우
				if((items==null && search==null)||( items.length()==0 || search.length()==0)) {
					listBoard = session.selectList("hjBoardList1_old", board);
				} else { // 검색하는 경우
					listBoard = session.selectList("hjBoardList1", board);
				}
			}else {// board 유형별
				
				listBoard = session.selectList("hjBoardList2", board);
			}
			
		} catch (Exception e) {
			System.out.println("HJDaoImpl listBoard Exception->" + e.getMessage());
		}
		return listBoard;
	}

	@Override
	public Board detail(Board board) {
		System.out.println("HJDaoImpl detail Start....");
		Board boardDetail = null;
		try {
			boardDetail = session.selectOne("hjBoardDetail", board);
			System.out.println("HJDaoImpl detail boardDetail.getM_nickname() : " + boardDetail.getM_nickname());
		} catch (Exception e) {
			System.out.println("HJDaoImpl detail Exception->" + e.getMessage());
		}
		return boardDetail;
	}



	
}
