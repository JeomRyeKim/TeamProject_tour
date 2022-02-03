package com.oracle.tour.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.tour.dto.Board;
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
	public String BoardList(Board board, String boardKindStr, String searchType, String keyword, 
							String currentPage, Model model) {
		logger.info("BoardList start");
		
		int total = hs.total();
		int boardKind = 0;
		if(boardKindStr != null)  boardKind = Integer.parseInt(boardKindStr);
		
		System.out.println("HJController BoardList total->" + total);
		System.out.println("HJController BoardList currentPage->" + currentPage);
		System.out.println("HJController BoardList boardKindString->" + boardKind);
		System.out.println("HJController BoardList keyword->" + keyword);
		System.out.println("HJController BoardList searchType->" + searchType);
		System.out.println("HJController BoardList board.getM_active_kind()->" + board.getM_active_kind());
		
		Paging pg = new Paging(total, currentPage);
		board.setStart(pg.getStart());
		board.setEnd(pg.getEnd());
		board.setB_kind(boardKind);
		board.setKeyword(keyword);
		board.setSearchType(searchType);
		List<Board> listBoard = hs.listBoard(board);
		System.out.println("HJController BoardList listBoard.size()->" + listBoard.size());
		
//		String m_nickname = hs.getNickname(m_id);
//		System.out.println("WriteForm getNickname->" + m_nickname);
//		model.addAttribute("m_nickname", m_nickname);
		model.addAttribute("listBoard", listBoard);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		model.addAttribute("radioButton", "a");

		return "HJview/Board";
	}
	
	@GetMapping("/HJBoardDetail")
	public String BoardDetail(Board board, Model model) {
		logger.info("BoardDetail start");
		Board boardDetail = hs.BoardDetail(board);
		System.out.println("HJController BoardDetail boardDetail.getB_title() : " + boardDetail.getB_title());
		model.addAttribute("boardDetail", boardDetail);

		return "HJview/BoardDetail";
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
	
	@RequestMapping(value = "uploadFileDelete", method = RequestMethod.GET)
	public String uploadFileDelete(HttpServletRequest request, Model model) throws Exception {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String deleteFile = uploadPath + "036b6fc5-f078-48e4-9cf9-27e9f4da7719_jung1.jpg";
		logger.info("deleteFile : " + deleteFile);
		System.out.println("uploadFileDelete POST start");
		int delResult = uploadFileDelete(deleteFile);
		logger.info("deleteFile result : " + deleteFile);
		model.addAttribute("deleteFile", deleteFile);
		model.addAttribute("delResult", delResult);
				
		return "forward:HJBoardDetail";
	}

	private int uploadFileDelete(String deleteFileName) throws Exception {
		int result = 0;
		logger.info("deleteFileName result : " + deleteFileName);
		File file = new File(deleteFileName);
		
		if(file.exists()) { // 파일이 존재할 때
			if(file.delete()) { // 파일이 존재해서 삭제했을 때
				System.out.println("파일 삭제 성공");
				result = 1;
			}else { // 파일이 존재하지만 삭제하지 못 했을 때
				System.out.println("파일 삭제 실패");
				result = 0;
			}
		}else { // 파일이 없을 때
			System.out.println("파일이 존재하지 않습니다.");
			result = -1;
		}
		return result;
	}
	
	
	@PostMapping(value = "/HJBoardmodify")
	public String Boardmodify() {
		logger.info("Boardmodify start");
		
		
		return "redirect:HJBoard";
	}	
	
	@RequestMapping("/HJboardReply")
	public String boardReply() {
		logger.info("boardReply start");
		
		return "HJview/boardReply";
	}
	
	
}
