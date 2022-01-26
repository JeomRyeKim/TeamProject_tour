package com.oracle.tour.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/Board")
	public String BoardList(Board board, String currentPage, Model model) {
		logger.info("BoardList start");
		int total = hs.total();
		System.out.println("HJController BoardList total->" + total);
		System.out.println("HJController BoardList currentPage->" + currentPage);
		
		Paging pg = new Paging(total, currentPage);
		board.setStart(pg.getStart());
		board.setEnd(pg.getEnd());
		List<Board> listBoard = hs.listBoard(board);
		System.out.println("HJController list listEmp.size()->" + listBoard.size());
		model.addAttribute("listBoard", listBoard);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);

		return "HJview/Board";
	}
	
	@GetMapping("/detail")
	public String detail(int b_no, Model model) {
		System.out.println("HJController detail start...");
		Board board = hs.detail(b_no);
		model.addAttribute("board", board);
		
		return "detail";
	}
	
}
