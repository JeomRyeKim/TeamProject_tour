package com.oracle.tour.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
	private int    b_kind;		// 게시판(댓굴) 유형 - 1:자유게시판, 2:후기게시판, 3:고객센터, 4:리뷰 댓글
	private int    b_no;		// 게시판(댓글) 번호
	private String m_id;		// 회원 번호
	private String b_title;		// 게시판(댓글) 제목
	private String b_content;	// 게시판(댓글) 내용
	private String b_date;		// 게시판(댓글) 작성일
	private int    b_hit;		// 게시판 조회수
	private String b_filename;	// 게시판(댓글) 파일(이미지)
	private int	   b_Group;		// 게시판(댓글) 그룹
	private int	   b_Step;		// 게시판(댓글) 순서
	private int	   b_Indent;	// 게시판(댓글) 들여쓰기
	private String b_notice;	// 게시판 공지글 여부 - 1:일반글, 2:공지글
	private String b_lock;		// 게시판(댓글) 비밀글 여부 - 1:공개글, 2:비공개글
	private int    b_like_cnt;	// 게시판 좋아요 개수
	
	//조회용
	private int start;
	private int end;
	private String m_nickname;			// 회원 닉네임 - 게시판 리스트에서 조회

	
}
