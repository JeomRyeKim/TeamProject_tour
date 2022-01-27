package com.oracle.tour.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String BoardList(Board board, String boardKindStr, String items, String search, String currentPage, HttpServletRequest request, Model model) {
		logger.info("BoardList start");
		int total = hs.total();
		int boardKind = 0;
		if(boardKindStr != null)  boardKind = Integer.parseInt(boardKindStr);
		
		System.out.println("HJController BoardList total->" + total);
		System.out.println("HJController BoardList currentPage->" + currentPage);
		System.out.println("HJController BoardList boardKindString->" + boardKind);
		System.out.println("HJController BoardList items->" + items);
		System.out.println("HJController BoardList search->" + search);
		
		Paging pg = new Paging(total, currentPage);
		board.setStart(pg.getStart());
		board.setEnd(pg.getEnd());
		board.setB_kind(boardKind);
		board.setItems(items);
		board.setSearch(search);
		List<Board> listBoard = hs.listBoard(board);
		System.out.println("HJController BoardList listBoard.size()->" + listBoard.size());
		model.addAttribute("listBoard", listBoard);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		model.addAttribute("radioButton", "a");

		return "HJview/Board";
	}
	
	@GetMapping("/detail")
	public String detail(Board board, Model model) {
		System.out.println("HJController detail start...");
		Board boardDetail = hs.detail(board);
		System.out.println("HJController detail boardDetail.getB_title() : " + boardDetail.getB_title());
		model.addAttribute("detail", boardDetail);
		
		return "HJview/detail";
	}

	@PostMapping(value = "/boardSearch")
	public String boardSearch() {
		
		return "HJview/Board";
	}
	
	
	@PostMapping(value = "/writeForm")
	public String wirteForm() {
		
		return "HJview/WriteForm";
	}
	
	
}
