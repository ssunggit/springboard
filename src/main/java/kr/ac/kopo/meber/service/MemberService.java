package kr.ac.kopo.meber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	public MemberVO login(MemberVO member) {
		MemberVO authMember = memberDAO.selectUser(member);
		return authMember;
	}
	
	public void signup(MemberVO member) {
		memberDAO.insertUser(member);
		
	}
	
	
}
