package com.oracle.tour.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.SessionScope;

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
	public String BoardList(Board board, String boardKindStr, String searchType, String keyword, String currentPage, Model model) {
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
		model.addAttribute("listBoard", listBoard);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		model.addAttribute("radioButton", "a");

		return "HJview/Board";
	}
	
	@GetMapping("/HJBoardDetail")
	public String BoardDetail(Board board, Model model) {
		logger.info("BoardDetail start");
		String detailPath = null;
		Board boardDetail = hs.BoardDetail(board);
		System.out.println("HJController BoardDetail boardDetail.getB_title() : " + boardDetail.getB_title());

//		System.out.println("HJController BoardDetail board.getM_id()->" + board.getM_id());
//		System.out.println("HJController BoardDetail board.getM_active_kind()->" + board.getM_active_kind());
		if(board.getM_active_kind() == 1) { // 로그인 여부 추후에 추가하기!
			model.addAttribute("boardDetail", boardDetail);
			detailPath = "HJview/BoardDetail";
		} else {
			System.out.println("HJController BoardDetail 활동중인 회원이 아닙니다!");
			detailPath = "redirect:/index";
		}

		return detailPath;
	}
	
	@RequestMapping(value = "/HJWriteForm")
	public String WriteForm() {
		logger.info("WriteForm start");
		
		return "HJview/WriteForm";
	}
	
	@RequestMapping(value = "/HJWrite")
	public String Write() {
		logger.info("Write start");
		
		return "redirect:HJBoard";
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
