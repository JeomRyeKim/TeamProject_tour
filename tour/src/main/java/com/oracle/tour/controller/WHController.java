package com.oracle.tour.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.tour.dto.Member;
import com.oracle.tour.service.WHService;

@Controller
public class WHController {
	private static final Logger logger = LoggerFactory.getLogger(WHController.class);
	private final WHService whService;
	@Autowired
	public WHController(WHService whService) {
		this.whService = whService;
	}
	
	@RequestMapping(value = "memberLogin")
	public String goLogin() {
		return "WHview/memberLogin";	
	}
	
	@RequestMapping(value = "memberLogout")
	public String logout(HttpSession session) {
		System.out.println("WHController logout Start..");
		session.invalidate();
		return "WHview/memberLogout";
	}
	
	@RequestMapping(value = "memberJoin")
	public String goJoin() {
		return "WHview/memberJoin";
	}
	
	@RequestMapping(value = "memberFind")
	public String goFind() {
		return "WHview/memberFind";
	}
	@RequestMapping(value="memberUpdate")
	public String goMemberUpdate() {
		return "WHview/memberUpdate";
	}
	@RequestMapping(value = "myInfo")
	public String gomyInfo() {
		return "WHview/myInfo";
	}
	
	@RequestMapping(value = "myWishList")
	public String gomyWishList() {
		return "WHview/myWistList";
	}
	@RequestMapping(value = "myPlanner")
	public String gomyPlanner() {
		return "WHview/myPlanner";
	}
	
	@RequestMapping(value = "myPlannerList")
	public String gomyPlannerList() {
		return "WHview/myPlannerList";
	}
	
	@RequestMapping(value = "myPlannerWrite")
	public String gomyPlannerWrite() {
		return "WHview/myPlannerWrite";
	}
	
	@RequestMapping(value = "memberDelete")
	public String gomemberDelete() {
		return "index";
		
	}
	@RequestMapping(value = "member/login" ,method = RequestMethod.POST)
	public String memberLogin(Model model,HttpSession session,Member member) {
		System.out.println("WHController memberLogin start...");
		int result = 0;
		result = whService.login(member);
		if(result==1) {
			session.setAttribute("M_id", member.getM_id());
			return "WHview/memberLoginView";
		}
		model.addAttribute("msg","아이디와 비밀번호를 알려주세요");
		
		return "forward:WHview/memberLoginFail";
	}
}
