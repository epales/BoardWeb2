package com.ezen.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezen.domain.Member;
import com.ezen.domain.SecurityUser;
import com.ezen.persistence.MemberRepository;
@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> optionalmember = memberRepo.findById(username);
		
		if (optionalmember.isPresent()) {
			// UserDetails 타입의 객체로 변환하여 리턴
			Member member = optionalmember.get();
			return new SecurityUser(member);
		} else {
			throw new UsernameNotFoundException(username + " 사용자 없음");
		}
	}

}
