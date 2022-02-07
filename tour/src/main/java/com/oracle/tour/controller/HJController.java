package com.oracle.tour.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.tour.dto.Board;
import com.oracle.tour.dto.Board_like;
import com.oracle.tour.dto.Member;
import com.oracle.tour.service.HJService;
import com.oracle.tour.service.Paging;

@Controller
public class HJController {
	private static final Logger logger = LoggerFactory.getLogger(HJController.class);
	private final HJService hs;
	@Autowired
	public HJController(HJService hs) {
		this.hs = hs;
	}
	
	@GetMapping(value = "/HJBoard")
	public String BoardList(Board board, String searchType, String keyword, 
							String currentPage, Model model, Member member, String m_id, 
							HttpServletRequest request) {
		logger.info("BoardList start");
		
		System.out.println("로그인을 한 상태라면 출력값이 있을 것 - m_id->" + m_id);
		
		int total = hs.total(board);
		int b_kind = board.getB_kind();
		System.out.println("HJController total b_kind->" + b_kind + 
				", searchType->" + searchType + ", keyword->" + keyword);
		
		System.out.println("HJController BoardList total->" + total);
		System.out.println("HJController BoardList currentPage->" + currentPage);
		System.out.println("HJController BoardList b_kind->" + b_kind);
		System.out.println("HJController BoardList keyword->" + keyword);
		System.out.println("HJController BoardList searchType->" + searchType);
		
		Paging pg = new Paging(total, currentPage);
		board.setStart(pg.getStart());
		board.setEnd(pg.getEnd());
		board.setB_kind(b_kind);
		board.setKeyword(keyword);
		board.setSearchType(searchType);
		List<Board> listBoard = hs.listBoard(board);
		System.out.println("HJController BoardList listBoard.size()->" + listBoard.size());
		
		model.addAttribute("listBoard", listBoard);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		model.addAttribute("radioButton", "a");
		
		System.out.println("!StringUtils.isEmpty(m_id)->" + !StringUtils.isEmpty(m_id)); // false
		
		if(!StringUtils.isEmpty(m_id)){
			System.out.println("세션 아이디 값이 있을 때 - HJController BoardList getMemberDetail start");
			member = hs.getMemberDetail(m_id);
			System.out.println("HJController BoardList getMemberDetail member.getM_id()->" + member.getM_id());
			System.out.println("HJController BoardList getMemberDetail member.getM_active_kind()->" + member.getM_active_kind());
			System.out.println("HJController BoardList getMemberDetail member.getM_nickname()->" + member.getM_nickname());
			System.out.println("HJController BoardList getMemberDetail member.getM_kind()->" + member.getM_kind());
			model.addAttribute("member", member);
		}
		
		return "HJview/Board";
	}
	
	@GetMapping("/HJBoardDetail")
	public String BoardDetail(Board board, Board_like board_like, Model model) {
		logger.info("BoardDetail start");
		// 조회수 올리는 메소드
		int b_hit = hs.getHit(board);
		
		// 파라미터를 Board board로 보내는 이유는 board의 b_kind와 b_no가 복합키라 2개의 파라미터를 값으로 보내야하기 때문
		Board boardDetail = hs.BoardDetail(board); 
		System.out.println("HJController BoardDetail boardDetail.getB_title()->" + boardDetail.getB_title());
		model.addAttribute("boardDetail", boardDetail);
		
		// 좋아요
		String m_id = board.getM_id();
		System.out.println("로그인 했다면 HJController BoardDetail m_id->" + m_id);
		
		// 좋아요 y or n여부?
		// 로그인 했을 때
		if(m_id != null) {
			String b_like_check = hs.BLikeYN(board_like);
			System.out.println("HJController BoardDetail b_like_check->" + b_like_check);
			model.addAttribute("b_like_check", b_like_check);
		}else { // 로그인 안 했을 때
			model.addAttribute("b_like_check", "n");
		}
		return "HJview/BoardDetail";
	}
	
	//좋아요 실행
	@RequestMapping("/HJBoardLike")
	@ResponseBody
	public String BoardLike(Board_like board_like, Board board) {
		System.out.println("HJController BoardLike start");
		System.out.println("HJController BoardLike board_like.getB_kind()" + board_like.getB_kind());
		System.out.println("HJController BoardLike board_like.getB_no()" + board_like.getB_no());
		System.out.println("HJController BoardLike board_like.getM_id()" + board_like.getM_id());
		System.out.println("HJController BoardLike board.getB_kind()" + board.getB_kind());
		System.out.println("HJController BoardLike board.getB_no()" + board.getB_no());
		System.out.println("HJController BoardLike board.getM_id()" + board.getM_id());
		
		// 좋아요 했는지 y여부 가져오기 - 좋아요 했으면 1, 없으면 0
		int BLikeChk = hs.BLikeChk(board_like);
		System.out.println("HJController BoardLike BLikeChk->" + BLikeChk);
		int BLikeChk_n = 0;
		int insert_bl = 0;
		int like_b = 0;
		int update_bl = 0;
		int dislike_bl = 0;
		int dislike_b = 0;
		
		// 좋아요 y가 아니다 - 1. n이다  2. 좋아요 처음이다 <- 좋아요+1해야함
		if(BLikeChk == 0 ) {
			// 좋아요 했는지 n여부 가져오기 - 0:좋아요 처음, 1:n이다
			BLikeChk_n = hs.BLikeChk_n(board_like);
			System.out.println("HJController BoardLike BLikeChk_n->" + BLikeChk_n);
			// 좋아요 처음이다 
			if(BLikeChk_n == 0) { 
				// 좋아요를 처음 누를 때 b_like_check = "y"으로 insert
				insert_bl = hs.insert_bl(board_like);
				System.out.println("HJController BoardLike insert_bl->" + insert_bl);
				// b_like_cnt +1처리
				like_b = hs.like_b(board);
				System.out.println("HJController BoardLike like_b->" + like_b);
				System.out.println("HJController like_b board.getB_like_cnt()->" + board.getB_like_cnt());
			}else { // n이다
				// 기존에 'n'으로 되어있던 값을 b_like_check = "y" 수정처리
				update_bl = hs.update_bl(board_like);
				System.out.println("HJController BoardLike update_bl->" + update_bl);
				// b_like_cnt +1처리
				like_b = hs.like_b(board);
				System.out.println("HJController BoardLike like_b->" + like_b);
				System.out.println("HJController like_b board.getB_like_cnt()->" + board.getB_like_cnt());
			}
		}else { // 좋아요y 다 <- 좋아요-1해야함
			// b_like_check 'y' -> 'n'으로 변경
			dislike_bl = hs.dislike_bl(board_like);
			System.out.println("HJController BoardLike dislike_bl->" + dislike_bl);
			System.out.println("HJController dislike_bl board_like.getB_like_check()->" + board_like.getB_like_check());
			// b_like_cnt -1처리
			dislike_b = hs.dislike_b(board);
			System.out.println("HJController BoardLike dislike_b->" + dislike_b);
			System.out.println("HJController dislike_b board.getB_like_cnt()->" + board.getB_like_cnt());
		}
		
		// b_like_check y or n 여부 가져오기
		String b_like_check = hs.BLikeYN(board_like);
		System.out.println("HJController BoardLike b_like_check->" + b_like_check);
		// b_like_cnt 가져오기
		int b_like_cnt = hs.selectLikeCnt(board);
		System.out.println("HJController BoardLike b_like_cnt->" + b_like_cnt);
		
		JSONObject obj = new JSONObject();

		obj.put("BLikeChk", BLikeChk);			// 좋아요 했는지 y여부
		obj.put("BLikeChk_n", BLikeChk_n);		// 좋아요 했는지 n여부
		obj.put("insert_bl", insert_bl);		// 좋아요를 처음 누를 때 b_like_check = "y"으로 insert
		obj.put("like_b", like_b);				// b_like_cnt +1처리
		obj.put("update_bl", update_bl);		// 기존에 'n'으로 되어있던 값을 b_like_check = "y" 수정처리
		obj.put("dislike_bl", dislike_bl);		// b_like_check 'y' -> 'n'으로 변경
		obj.put("dislike_b", dislike_b);		// b_like_cnt -1처리
		obj.put("b_like_check", b_like_check);	// b_like_check 가져오기
		obj.put("b_like_cnt", b_like_cnt);		// b_like_cnt 가져오기
		
		return obj.toJSONString();
	}
	
	
	
	@RequestMapping(value = "/HJWriteForm")
	public String WriteForm(Model model) {
		logger.info("WriteForm start");
		
		return "HJview/WriteForm";
	}
	
	@RequestMapping(value = "/HJWrite", method = RequestMethod.POST)
	public String Write(HttpServletRequest request, MultipartFile file1, Board board, Model model) throws Exception {
		logger.info("Write start");
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/tour_board_image/");
		
		System.out.println("Write POST start");
		logger.info("OriginalFilename : " + file1.getOriginalFilename());
		logger.info("Size : " + file1.getSize());
		logger.info("ContentType : " + file1.getContentType());
		logger.info("uploadPath : " + uploadPath);
		
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		logger.info("savedName : " + savedName);
//		model.addAttribute("savedName", savedName);
//		model.addAttribute("uploadPath", uploadPath);
		board.setM_nickname(request.getParameter("m_nickname"));
		board.setM_id(request.getParameter("m_id"));
		board.setB_kind(Integer.parseInt(request.getParameter("b_kind")));
		board.setB_lock(request.getParameter("b_lock"));
		board.setB_title(request.getParameter("b_title"));
		board.setB_contents(request.getParameter("b_contents"));
		board.setB_filename(request.getParameter("b_filename"));
		
		// 파일 업로드
		
		
		
		int result = hs.insert(board); // 성공시 1, 실패시 0
		System.out.println("board.getB_filename()->" + board.getB_filename());
		System.out.println("board.getM_nickname()->" + board.getM_nickname());
		if (result > 0) return "forward:HJBoard";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeForm";
		}
	}
	private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws Exception {
		UUID uid = UUID.randomUUID(); // UUID : 세계적으로 유일한 번호
		// requestPath = requestPath + "/resource/image";
		System.out.println("uploadPath->" + uploadPath);
		
		// Directory 생성 <- 폴더 = 디렉토리 같은 의미
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) { // 폴더가 존재하지 않을 때
			fileDirectory.mkdirs();	// 폴더를 만들어주는 로직
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalName;
		logger.info("savedName : " + savedName);
		File target = new File(uploadPath, savedName); // 진짜로 업로드 처리
//		File target = new File(requestPath, savedName);
		FileCopyUtils.copy(fileData, target); //org.springframework.util.FileCopyUtils;
		
		return savedName;
	}
	
//	@RequestMapping(value = "uploadFileDelete", method = RequestMethod.GET)
//	public String uploadFileDelete(HttpServletRequest request, Model model) throws Exception {
//		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
//		String deleteFile = uploadPath + "036b6fc5-f078-48e4-9cf9-27e9f4da7719_jung1.jpg";
//		logger.info("deleteFile : " + deleteFile);
//		System.out.println("uploadFileDelete POST start");
//		int delResult = uploadFileDelete(deleteFile);
//		logger.info("deleteFile result : " + deleteFile);
//		model.addAttribute("deleteFile", deleteFile);
//		model.addAttribute("delResult", delResult);
//				
//		return "forward:HJBoardDetail";
//	}
//
//	private int uploadFileDelete(String deleteFileName) throws Exception {
//		int result = 0;
//		logger.info("deleteFileName result : " + deleteFileName);
//		File file = new File(deleteFileName);
//		
//		if(file.exists()) { // 파일이 존재할 때
//			if(file.delete()) { // 파일이 존재해서 삭제했을 때
//				System.out.println("파일 삭제 성공");
//				result = 1;
//			}else { // 파일이 존재하지만 삭제하지 못 했을 때
//				System.out.println("파일 삭제 실패");
//				result = 0;
//			}
//		}else { // 파일이 없을 때
//			System.out.println("파일이 존재하지 않습니다.");
//			result = -1;
//		}
//		return result;
//	}
	
	@RequestMapping(value = "/HJBoardDelete")
	public String BoardDelete(Board board, Model model) {
		logger.info("BoardDelete start");
		int boardDelete = hs.BoardDelete(board);
		System.out.println("HJController BoardDelete boardDelete->" + boardDelete);
		
		return "redirect:HJBoard";
	}	
	
	@PostMapping(value = "/HJBoardmodify")
	public String Boardmodify(Model model) {
		logger.info("Boardmodify start");
		
		
		return "Boardmodify";
	}	
	
	@RequestMapping("/HJboardReply_view")
	public String boardReply_view(Board board, Model model) {
		logger.info("boardReply_view start");
		
		Board boardDetail = hs.BoardDetail(board); 
		System.out.println("HJController boardReply_view boardDetail.getB_title()->" + boardDetail.getB_title());
		System.out.println("HJController boardReply_view boardDetail.getM_kind()->" + boardDetail.getM_kind());
		model.addAttribute("boardDetail", boardDetail);
		
		return "HJview/boardReply";
	}
	
	@RequestMapping("/HJboardReply")
	public String boardReply(Board board, Model model) {
		logger.info("boardReply start");
		
		
		return "forward:HJBoard";
	}
	
	
}
