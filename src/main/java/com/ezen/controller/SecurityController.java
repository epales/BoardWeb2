package com.ezen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.domain.Member;
import com.ezen.service.MemberService;
@Controller
public class SecurityController {
	
	@Autowired
	private MemberService memberService;
	
	/*
	 * 로그인 화면 요청
	 */
	@GetMapping("/login")
	public String loginView() {
		
		return "system/login";
	}
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member loginUser = memberService.getMember(member);
		
		if(loginUser != null &&
				loginUser.getPassword().equals(member.getPassword())) {
			
			// 인증 된 사용자
			model.addAttribute("member", loginUser);
			
			return "redirect:getBoardList";
			
		} else {
		
			return "redirect:login";
		}
	}
	
	/*
	 * 접근권한 없음 페이지 표시
	 */
	@GetMapping("/accessDenied")
	public void accessDenied() {
	}
	@GetMapping("/adminPage")
	public void adminPage() {
		
	}
}
