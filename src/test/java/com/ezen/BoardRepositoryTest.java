package com.ezen;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ezen.domain.Board;
import com.ezen.domain.Member;
import com.ezen.domain.Role;
import com.ezen.persistence.BoardRepository;
import com.ezen.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	@Ignore
	public void testInsert() {
		Member member1 = new Member();
		
		member1.setName("이순신");
		member1.setPassword(encoder.encode("1234"));
		member1.setRole(Role.ROLE_ADMIN);
		member1.setEnabled(true);
		member1.setId("admin1");
		
		memberRepo.save(member1);
		
		Member member2 = new Member();
		
		member2.setName("장보고");
		member2.setPassword(encoder.encode("1234"));
		member2.setRole(Role.ROLE_MEMBER);
		member2.setEnabled(true);
		member2.setId("member1");
		
		memberRepo.save(member2);
		
		for(int i = 1; i<=3; i++) {
			Board board = new Board();
		
			
			board.setTitle("이순신이 등록한 게시글 " + i);
			board.setContent("이순신이 등록한 게시글 내용 " + i);
			board.setCreateDate(new Date());
			board.setMember(member1);
			board.setCnt(0L);
			
		boardRepo.save(board);
			
		}
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			
			board.setTitle("장보고가 등록한 게시글 " + i);
			board.setContent("장보고가 등록한 게시글 내용 " + i);
			board.setCreateDate(new Date());	
			board.setMember(member2);
			board.setCnt(0L);
			
		boardRepo.save(board);
			
		}
		
	}
	
	@Test
	@Ignore
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		System.out.println("-------------------------------------------------------------");
		System.out.println("["+board+"]");
		System.out.println("-------------------------------------------------------------");
	}
	
	@Test
	@Ignore
	public void testGetBoardList() {
		
		Member member = memberRepo.findById("member1").get();
		
		for(Board board : member.getBoardList()) {
			System.out.println("-------------------------------------------------------------");
			System.out.println("["+board+"]");
			System.out.println("-------------------------------------------------------------");
		
		}
	}
}
