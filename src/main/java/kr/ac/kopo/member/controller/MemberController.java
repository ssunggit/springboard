package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.ac.kopo.meber.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;

@SessionAttributes("loginVO") // 모델에게 요청 -> loginVO를 세션에 등록하도록 
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 로그인 폼(get)
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		// spring form 태그에서 사용할 memberVO를 공유영역에 등록시킨다.
		request.setAttribute("memberVO", new MemberVO());
		
		// WEB-INF/jsp/member/login.jsp
		return "member/login";
	}
	
	/**
	 * 로그인 (post)
	 */
	@PostMapping("/login")												// Model : 리퀘스트 영역 - 컨트롤러에서 디스팻처서블릿 
	public String loginPost(@Valid MemberVO member, BindingResult result, Model model, HttpSession session) {
		/*
		 * 1. MemberVO 의 member가 null값이 아닌 valid 한 값이 들어왔는지 확인
		 * -> BindingResult를 가지고 확인
		 * 2.1 null 값을 가진 error 값이 들어온 경우
		 * 		forward member/login.jsp
		 * 2.2 정상값이 들어온 경우, id/password 유효한지 select문으로 체크
		 *		2.2.1 유효한 id/password - db 존재
		 *			  welcome!!! 홍길동님 반가워요
		 *			  session에 해당 유효한 memberVO 등록 
		 *		2.2.2 유효하지 않은 id/password - db 존재 x
		 *			- 사용자에게 메세지. id/password 유효하지 않으니, 다시 입력하세요.
		 *			  forward member/login.jsp
		 * 3. interceptor  
		 */
		System.out.println(result);
		System.out.println(member);
		 if(result.hasErrors()) {
			 
			 // null 값 존재. => 다시 입력하세요
			 // WEB-INF/jsp/member/login.jsp
			 return "member/login";
		 }else {
			 // null 값이 존재하지 않음 => db 작업 . id/password 가 유효한 사용자 확인
			 // db 작업
			 MemberVO authMember =  memberService.login(member);
			 // authMember 가 null 일 경우 처리 해야함-> 다시 입력 받도록
			 System.out.println("authMember : " + authMember);
			 if(authMember == null) {
				 // 유효하지 않은 id/password
				 model.addAttribute("loginmsg", "id와 password가 유효하지 않습니다.");
				 // WEB-INF/jsp/member/login.jsp
				 return "member/login";
			 }else {
//				 session.setAttribute("loginVO", authMember);
				 // model은 리퀘스트 영역이라 어노테이션 지정해줘야한다.
				 model.addAttribute("loginVO", authMember);				 
//				 return "redirect:/board";
				 
				 // dest 가 널일 경우 (로그인을 먼저하는 경우) 인터셉터를 거치지 않는다. 
				 // -> 널 처리
				 String dest = (String)session.getAttribute("dest");
				 System.out.println("dest: "+dest);
				 if(dest == null) {			 
					 return "redirect:/";
				 }else {					 
					 return "redirect:" + dest;
				 }
			 }
		 }
			 
	}
	
	@RequestMapping("/logout")					// SessionStatus : spring거	
	public String logout(HttpSession session, SessionStatus sessionstatus) {
		session.invalidate();
		
//		모델로 등록하여 어노테이션으로 등록한 세션 정보 삭제
		sessionstatus.setComplete();
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String signupGet(Model model) {
		
		MemberVO memberVO = new MemberVO();
		model.addAttribute("memberVO", memberVO);
		return "member/signup";		
	}
	
	@PostMapping("/signup")
	public String signupPost(@Valid @ModelAttribute("memberVO") MemberVO member, BindingResult result) {
		if (result.hasErrors()) {
			return "member/singup";
		}else {
			memberService.signup(member);
			return "redirect:/login";
		}
	}
	
	
	
	
}
