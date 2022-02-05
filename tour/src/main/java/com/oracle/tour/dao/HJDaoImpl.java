package com.oracle.tour.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.tour.dto.Board;
import com.oracle.tour.dto.Member;

@Repository
public class HJDaoImpl implements HJDao {
	private final SqlSession session;
	@Autowired
	public HJDaoImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public Member getMemberDetail(String m_id) {
		System.out.println("HJDaoImpl getMemberDetail start");
		Member member = null;
		try {
			member = session.selectOne("hjMemberDetail", m_id);
		} catch (Exception e) {
			System.out.println("HJDaoImpl getMemberDetail Exception->" + e.getMessage());
		}
		return member;
	}
	
	@Override
	public int total(Board board) {
		System.out.println("HJDaoImpl total Start....");
		int tot = 0;
		int b_kind = board.getB_kind();
		String searchType = board.getSearchType();
		String keyword = board.getKeyword();
		System.out.println("HJDaoImpl total b_kind->" + b_kind + 
				", searchType->" + searchType + ", keyword->" + keyword);
		try {
			if(b_kind == 0) { // 전체
				// 검색X
				if((searchType==null && keyword==null)||(searchType.length()==0 || keyword.length()==0)) {
					System.out.println("HJDaoImpl total - 전체, 검색X");
					tot = session.selectOne("hjBoardTotal_n");
				}  // 검색O
				else if((searchType!=null && keyword!=null)||(searchType.length()!=0 || keyword.length()!=0)) {
					System.out.println("HJDaoImpl total - 전체, 검색O");
					tot = session.selectOne("hjBoardTotal_y", board);	
				}
			}else if(b_kind != 0) { // 유형별
				// 검색X
				if((searchType==null && keyword==null)||(searchType.length()==0 || keyword.length()==0)) {
					System.out.println("HJDaoImpl total - 유형별, 검색X");
					tot = session.selectOne("hjBoardKindTotal_n", board);	
				}  // 검색O
				else if((searchType!=null && keyword!=null)||(searchType.length()!=0 || keyword.length()!=0)) {
					System.out.println("HJDaoImpl total - 유형별, 검색O");
					tot = session.selectOne("hjBoardKindTotal_y", board);	
				}
			}
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
		String searchType = board.getSearchType();
		String keyword = board.getKeyword();
		System.out.println("HJDaoImpl listBoard searchType->" + searchType);
		System.out.println("HJDaoImpl listBoard keyword->" + keyword);
		try {
			if(b_kind == 0 )	{// board 전체 -> b_kind==0
				  // 검색을 안 하는 경우
				if((searchType==null && keyword==null)||(searchType.length()==0 || keyword.length()==0)) {
					listBoard = session.selectList("hjBoardList1_old", board);
				} // 검색하는 경우 
				else if((searchType!=null && keyword!=null)||(searchType.length()!=0 || keyword.length()!=0)) {
					System.out.println("HJDaoImpl listBoard keyword 전체 검색시 hjBoardList1" );
					listBoard = session.selectList("hjBoardList1", board);
					System.out.println("hjBoardList1 listBoard.size()->" + listBoard.size());
				}
			}else if (b_kind != 0) {// board 유형별  -> b_kind!=0
				  // 검색을 안 하는 경우
				if((searchType==null && keyword==null)||(searchType.length()==0 || keyword.length()==0)) {
					System.out.println("hjBoardList2_old b_kind->" + b_kind);
					listBoard = session.selectList("hjBoardList2_old", board);
				} // 검색하는 경우
				else if((searchType!=null && keyword!=null)||(searchType.length()!=0 || keyword.length()!=0)) {
					System.out.println("HJDaoImpl listBoard keyword 유형별 검색시 hjBoardList2" );
					System.out.println("hjBoardList2 b_kind->" + b_kind);
					listBoard = session.selectList("hjBoardList2", board);
					System.out.println("hjBoardList2 listBoard.size()->" + listBoard.size());
				}
			}
		} catch (Exception e) {
			System.out.println("HJDaoImpl listBoard Exception->" + e.getMessage());
		}
		return listBoard;
	}

	@Override
	public int getHit(Board board) {
		System.out.println("HJDaoImpl getHit Start....");
		int b_hit = 0;
		try {
			b_hit = session.update("hjGetHit", board);
			System.out.println("HJDaoImpl getHit b_hit->" + b_hit);
		} catch (Exception e) {
			System.out.println("HJDaoImpl getHit Exception->" + e.getMessage());
		}
		
		return b_hit;
	}

	@Override
	public Board BoardDetail(Board board) {
		System.out.println("HJDaoImpl BoardDetail Start....");
		Board boardDetail = null;
		try {
			boardDetail = session.selectOne("hjBoardDetail", board);
			System.out.println("HJDaoImpl detail boardDetail.getM_nickname()->" + boardDetail.getM_nickname());
			System.out.println("HJDaoImpl detail boardDetail.getM_active_kind()->" + boardDetail.getM_active_kind());
		} catch (Exception e) {
			System.out.println("HJDaoImpl detail Exception->" + e.getMessage());
		}
		return boardDetail;
	}

	@Override
	public String getNickname(String m_id) {
		System.out.println("HJDaoImpl getNickname Start....");
		String m_nickname = null;
		try {
			m_nickname = session.selectOne("hjNicknameSelect", m_id);
		} catch (Exception e) {
			System.out.println("HJDaoImpl getNickname Exception->" + e.getMessage());
		}
		
		return null;
	}

	@Override
	public int insert(Board board) {
		System.out.println("HJDaoImpl insert Start....");
		int result = 0;
		try {
			result = session.insert("hjBoardInsert", board);
		} catch (Exception e) {
			System.out.println("HJDaoImpl insert Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public int BoardDelete(Board board) {
		System.out.println("HJDaoImpl BoardDelete Start....");
		int boardDelete = 0;
		try {
			boardDelete = session.delete("hjBoardDelete", board);
			System.out.println("HJDaoImpl BoardDelete boardDelete->" + boardDelete);
		} catch (Exception e) {
			System.out.println("HJDaoImpl BoardDelete Exception->" + e.getMessage());
		}
		return boardDelete;
	}



	
}
