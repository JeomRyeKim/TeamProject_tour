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
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.tour.dto.Member;
import com.oracle.tour.service.MailSendService;
import com.oracle.tour.service.WHService;

@Controller
public class WHController {
	private static final Logger logger = LoggerFactory.getLogger(WHController.class);
	
	@Autowired
	private MailSendService mailSendService;
	
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
	public String gomyInfo(Member member,Model model) {
		System.out.println("WHController gomyInfo start..");
		member = whService.myInfo(member);
		model.addAttribute("member",member);
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
		member = whService.login(member);
			if(member.getM_active_kind()==1&&member.getM_kind()==1) {
				session.setAttribute("m_id", member.getM_id());
				session.setAttribute("m_active_kind", member.getM_active_kind());
				session.setAttribute("m_kind", member.getM_kind());
				return "WHview/memberLoginView";
			}else if(member.getM_active_kind()==2&&member.getM_kind()==1) {
				session.setAttribute("m_id", member.getM_id());
				session.setAttribute("m_active_kind", member.getM_active_kind());
				session.setAttribute("m_kind", member.getM_kind());
				return "WHview/memberLoginView";
			}else if(member.getM_kind()==2) {
				session.setAttribute("m_id", member.getM_id());
				session.setAttribute("m_active_kind", member.getM_active_kind());
				session.setAttribute("m_kind", member.getM_kind());
				return "WHview/memberLoginView";
			}else {
				System.out.println("memberkind="+member.getM_kind());
				model.addAttribute("msg","아이디혹은 비밀번호가 틀렸습니다");
				return "WHview/loginFailView";
			}
		
	
	}
	
	@RequestMapping(value = "/member/join",method = RequestMethod.POST)
	public String memberJoin(Member member,Model model) {
		System.out.println("WHController memberJoin start...");
		whService.memberJoin(member);
		return "WHview/memberJoinSuccess";
		
	}
	@ResponseBody
	@RequestMapping(value="/member/mailCheck")
	public String mailCheck(String m_email) {
		System.out.println("WHController mailCheck start..");
		String check="";
		check=mailSendService.mailSend(m_email);
		System.out.println(check);
		return check;
	}
	
	@ResponseBody
	@RequestMapping(value = "/member/idCheck")
	public String idCheck(String m_id) {
		System.out.println("WHController idCheck start..");
		int result =0;
		result = whService.idCheck(m_id);
		String result1 = Integer.toString(result);
		System.out.println(result1);
		return result1;
		
	}
	@RequestMapping(value = "passwordFind")
	public String passwordFind() {
		System.out.println("WHController passwordFind start..");
		return "WHview/passwordFind";
		
	}
	@ResponseBody
	@RequestMapping(value="/member/pwChange")
	public String pwChange(Member member,Model model) {
		System.out.println("WHController pwChange start..");
		whService.pwChange(member);
		model.addAttribute("msg","비밀번호 변경완료");
		return "member";
	}
	
	@RequestMapping(value = "member/find",method = RequestMethod.POST)
	public String memberFind(Member member,Model model) {
		System.out.println("WHController memberFind start..");
		model.addAttribute("msg","임시비밀번호를 변경해주세요");
		mailSendService.memberFind(member);
		return "redirect:memberLogin";
		
	}
	
}
