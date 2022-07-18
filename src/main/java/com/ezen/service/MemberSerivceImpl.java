package com.ezen.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.domain.Member;
import com.ezen.persistence.MemberRepository;

@Service
public class MemberSerivceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public Member getMember(Member member) {
		// (1) id로 회원 검색
		Optional<Member> user = memberRepo.findById(member.getId());
		
		// (2) 회원이 존재하는지 확인
		if(user.isPresent()) { // Optional 명령어
			return user.get();
		} else {
			return null;
		}
	}
}
