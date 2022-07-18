package com.ezen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.domain.Board;
import com.ezen.domain.Member;
import com.ezen.persistence.BoardRepository;
import com.ezen.service.BoardService;

@Controller
@SessionAttributes("member")
public class BoardController {

	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member,
					Board board, Model model) {
		if(member.getId() == null) {
			
			return "redirect:login";
		
		}
		List<Board> boardList = boardService.getBoardList().getContent();
		
		model.addAttribute("boardList", boardList);
		
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		
		Board boardList = boardRepo.findById(board.getSeq()).get();
		
		model.addAttribute("board", boardList);
		
		return "board/getBoard";
	}

	@GetMapping("/insertBoard")
	public String insertBoardView() {
		
		return "board/insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		Member member = new Member();
		member.setId("member1");
		board.setMember(member);
		boardService.insertBoard(board);
		
		return "redirect:getBoardList";
	}
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		
		boardService.updateBoard(board);
		
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		
		boardService.deleteBoard(board);
		
		return "redirect:getBoardList";
	}
	

}
