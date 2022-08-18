package kr.ac.kopo.meber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	
							// 3초
	@Scheduled(fixedDelay = 3000)
	public void printSchedule() {
		// 크롤링과 같은 거에 활용
		
		// 3초에 한번씩 출력
		//System.out.println("스케줄러에 의한 메세지");
	}
	
	// crontab
	// 5초
	@Scheduled(cron = " */5 * * * * *")
	public void printSchedule2() {		
		// 5초에 한번씩 출력
		System.out.println("스케줄러에 의한 메세지2");
	}
	
	
}
