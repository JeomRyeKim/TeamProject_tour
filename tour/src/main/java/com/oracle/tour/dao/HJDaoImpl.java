package com.oracle.tour.dao;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.tour.dto.Board;
import com.oracle.tour.dto.Board_comment;
import com.oracle.tour.dto.Board_like;
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
	public int insert(Board board) {
		System.out.println("HJDaoImpl insert Start....");
		System.out.println("HJDaoImpl insert board.getB_filename()->" + board.getB_filename());
		int result = 0;
		try {
			result = session.insert("hjBoardInsert", board);
			System.out.println("HJDaoImpl insert result->" + result);
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

	@Override
	public int BLikeChk(Board_like board_like) {
		System.out.println("HJDaoImpl BLikeChk Start....");
		int BLikeChk = 0;
		try {
			BLikeChk = session.selectOne("hjBLikeChk", board_like);
			System.out.println("HJDaoImpl board.getM_id()->" + board_like.getM_id());
			System.out.println("HJDaoImpl BLikeChk->" + BLikeChk);
		} catch (Exception e) {
			System.out.println("HJDaoImpl BLikeChk Exception->" + e.getMessage());
		}
		return BLikeChk;
	}
	@Override
	public int BLikeChk_n(Board_like board_like) {
		System.out.println("HJDaoImpl BLikeChk_n Start....");
		int BLikeChk_n = 0;
		try {
			BLikeChk_n = session.selectOne("hjBLikeChk_n", board_like);
			System.out.println("HJDaoImpl board.getM_id()->" + board_like.getM_id());
			System.out.println("HJDaoImpl BLikeChk_n->" + BLikeChk_n);
		} catch (Exception e) {
			System.out.println("HJDaoImpl BLikeChk_n Exception->" + e.getMessage());
		}
		return BLikeChk_n;
	}
	// 좋아요를 처음 누를 때 b_like_check = "y"으로 insert
	@Override
	public int insert_bl(Board_like board_like) {
		System.out.println("HJDaoImpl insert_bl Start....");
		int insert_bl = 0;
		try {
			insert_bl = session.insert("hjinsert_bl", board_like);
			System.out.println("HJDaoImpl insert_bl->" + insert_bl);
		} catch (Exception e) {
			System.out.println("HJDaoImpl insert_bl Exception->" + e.getMessage());
		}
		return insert_bl;
	}
	// b_like_cnt +1처리
	@Override
	public int like_b(Board board) {
		System.out.println("HJDaoImpl like_b Start....");
		int like_b = 0;
		try {
			like_b = session.update("hjLike_b", board);
			System.out.println("HJDaoImpl like_b->" + like_b);
		} catch (Exception e) {
			System.out.println("HJDaoImpl like_b Exception->" + e.getMessage());
		}
		return like_b;
	}
	// b_like_check = "y" 수정처리
	@Override
	public int update_bl(Board_like board_like) {
		System.out.println("HJDaoImpl update_bl Start....");
		int update_bl = 0;
		try {
			update_bl = session.update("hjUpdate_bl", board_like);
		} catch (Exception e) {
			System.out.println("HJDaoImpl update_bl Exception->" + e.getMessage());
		}
		return update_bl;
	}
	// b_like_check 'y' -> 'n'으로 변경
	@Override
	public int dislike_bl(Board_like board_like) {
		System.out.println("HJDaoImpl dislike_bl Start....");
		int dislike_bl = 0;
		try {
			dislike_bl = session.update("hjDislike_bl", board_like);
			System.out.println("HJDaoImpl dislike_bl->" + dislike_bl);
			System.out.println("HJDaoImpl dislike_bl board_like.getB_like_check()->" + board_like.getB_like_check());
		} catch (Exception e) {
			System.out.println("HJDaoImpl dislike_bl Exception->" + e.getMessage());
		}
		return dislike_bl;
	}
	// b_like_cnt -1처리
	@Override
	public int dislike_b(Board board) {
		System.out.println("HJDaoImpl dislike_b Start....");
		int dislike_b = 0;
		try {
			dislike_b = session.update("hjDislike_b", board);
			System.out.println("HJDaoImpl dislike_b->" + dislike_b);
			System.out.println("HJDaoImpl dislike_b board.getB_like_cnt()->" + board.getB_like_cnt());
		} catch (Exception e) {
			System.out.println("HJDaoImpl dislike_b Exception->" + e.getMessage());
		}
		return dislike_b;
	}
	// b_like_check y or n 여부 가져오기
	@Override
	public String BLikeYN(Board_like board_like) {
		System.out.println("HJDaoImpl BLikeYN Start....");
		String b_like_check = null;
		try {
			b_like_check = session.selectOne("hjBLikeYN", board_like);
			System.out.println("HJDaoImpl BLikeYN b_like_check->" + b_like_check);
		} catch (Exception e) {
			System.out.println("HJDaoImpl BLikeYN Exception->" + e.getMessage());
		}
		return b_like_check;
	}
	// b_like_cnt 가져오기
	@Override
	public int selectLikeCnt(Board board) {
		System.out.println("HJDaoImpl selectLikeCnt Start....");
		int b_like_cnt = 0;
		try {
			b_like_cnt = session.selectOne("hjselectLikeCnt", board);
			System.out.println("HJDaoImpl selectLikeCnt b_like_cnt->" + b_like_cnt);
		} catch (Exception e) {
			System.out.println("HJDaoImpl selectLikeCnt Exception->" + e.getMessage());
		}
		return b_like_cnt;
	}

	// 게시글 수정
	@Override
	public int Boardmodify(Board board) {
		System.out.println("HJDaoImpl Boardmodify Start....");
		int modifyChk = 0;
		try {
			modifyChk = session.update("hjBoardmodify", board);
		} catch (Exception e) {
			System.out.println("HJDaoImpl Boardmodify Exception->" + e.getMessage());
		}
		return modifyChk;
	}
	
	// 새로 입력하는 댓글이 기존의 댓글과 b_Group =같고   &  기존의 댓글보다 b_Step >작으면  =>  b_Step + 1
	@Override
	public int updateReply(Board board) {
		System.out.println("HJDaoImpl updateReply Start....");
		System.out.println("HJDaoImpl updateReply board.getB_Group()->" + board.getB_Group());
		System.out.println("HJDaoImpl updateReply board.getB_Step()->" + board.getB_Step());
		System.out.println("HJDaoImpl updateReply board.getB_Indent()->" + board.getB_Indent());
		int replyShapeChk = 0;
		try {
			replyShapeChk = session.update("hjUpdateReply", board);
			System.out.println("HJDaoImpl updateReply replyShapeChk->" + replyShapeChk);
		} catch (Exception e) {
			System.out.println("HJDaoImpl updateReply Exception->" + e.getMessage());
		}
		return replyShapeChk;
	}
	// 답변 입력
	@Override
	public int insertReply(Board board) {
		System.out.println("HJDaoImpl insertReply Start....");
		System.out.println("HJDaoImpl insertReply board.getB_Group()->" + board.getB_Group());
		System.out.println("HJDaoImpl insertReply board.getB_Step()->" + board.getB_Step());
		System.out.println("HJDaoImpl insertReply board.getB_Indent()->" + board.getB_Indent());
		int insertReplyChk = 0;
		try {
			insertReplyChk = session.insert("hjInsertReplyChk", board);
			System.out.println("HJDaoImpl updateReply insertReplyChk->" + insertReplyChk);
		} catch (Exception e) {
			System.out.println("HJDaoImpl insertReply Exception->" + e.getMessage());
		}
		return insertReplyChk;
	}
	
	// 게시글 댓글 입력
	@Override
	public int commentInsert(Board_comment board_comment) {
		System.out.println("HJDaoImpl commentInsert Start....");
		int getCommentChk = 0;
		try {
			getCommentChk =session.insert("hjCommentInsert", board_comment);
			System.out.println("HJDaoImpl commentInsert getCommentChk->" + getCommentChk);
		} catch (Exception e) {
			System.out.println("HJDaoImpl commentInsert Exception->" + e.getMessage());
		}
		return getCommentChk;
	}
	// 게시판에 댓글 개수 +1하기
	@Override
	public int getBoardComCntUpdate(Board_comment board_comment) {
		System.out.println("HJDaoImpl getBoardComCntUpdate Start....");
		int boardComCntUpdate = 0;
		try {
			boardComCntUpdate = session.update("hjBoardComCntUpdate", board_comment);
			System.out.println("HJDaoImpl getBoardComCntUpdate boardComCntUpdate->" + boardComCntUpdate);
		} catch (Exception e) {
			System.out.println("HJDaoImpl getBoardComCntUpdate Exception->" + e.getMessage());
		}
		return boardComCntUpdate;
	}
	// 게시글 댓글 목록 가져오기
	@Override
	public List<Board> getComList(Board_comment board_comment) {
		System.out.println("HJDaoImpl getComList Start....");
		List<Board> commentList = null;
		try {
			commentList = session.selectList("hjGetComList", board_comment);
			System.out.println("HJDaoImpl getComList commentList.size()->" + commentList.size());
		} catch (Exception e) {
			System.out.println("HJDaoImpl getComList Exception->" + e.getMessage());
		}
		return commentList;
	}
	// 해당 게시글 댓글 개수 가져오기
	@Override
	public int getCommentCnt(Board_comment board_comment) {
		System.out.println("HJDaoImpl getCommentCnt Start....");
		int commentCnt = 0;
		try {
			commentCnt = session.selectOne("hjCommentCnt", board_comment);
			System.out.println("HJDaoImpl getCommentCnt commentCnt->" + commentCnt);
		} catch (Exception e) {
			System.out.println("HJDaoImpl getCommentCnt Exception->" + e.getMessage());
		}
		return commentCnt;
	}
	// 댓글 수정
	@Override
	public int commentUpdate(Board_comment board_comment) {
		System.out.println("HJDaoImpl commentUpdate Start....");
		int commentUpdateChk = 0;
		try {
			commentUpdateChk = session.update("hjCommentUpdate", board_comment);
			System.out.println("HJDaoImpl commentUpdate commentUpdateChk->" + commentUpdateChk);
		} catch (Exception e) {
			System.out.println("HJDaoImpl commentUpdate Exception->" + e.getMessage());
		}
		return commentUpdateChk;
	}
	// 수정한 데이터 select
	@Override
	public List<Board_comment> getModifyComList(Board_comment board_comment) {
		System.out.println("HJDaoImpl getModifyComList Start....");
		List<Board_comment> BCModifyComList = null;
		try {
			BCModifyComList = session.selectOne("hjmodifyComList", board_comment);
		} catch (Exception e) {
			System.out.println("HJDaoImpl getModifyComList Exception->" + e.getMessage());
		}
		return BCModifyComList;
	}
	// 댓글 삭제
	@Override
	public int commnetDelete(Board_comment board_comment) {
		System.out.println("HJDaoImpl commnetDelete Start....");
		int commentDelChk = 0;
		try {
			commentDelChk = session.delete("hjCommnetDelete", board_comment);
			System.out.println("HJDaoImpl commnetDelete commentDelChk->" + commentDelChk);
		} catch (Exception e) {
			System.out.println("HJDaoImpl getModifyComList Exception->" + e.getMessage());
		}
		return commentDelChk;
	}
	// 게시판에 댓글 개수 -1하기
	@Override
	public int getBoardComCntDelete(Board_comment board_comment) {
		System.out.println("HJDaoImpl getCommentCnt Start....");
		int boardComCntDelete = 0;
		try {
			boardComCntDelete = session.update("hjBoardComCntDelete", board_comment);
		} catch (Exception e) {
			System.out.println("HJDaoImpl getBoardComCntDelete Exception->" + e.getMessage());
		}
		return boardComCntDelete;
	}

	
}
