package com.ezen.www.contorller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // log 관련 어노테이션
@RequestMapping("/member/*")
public class MemberController {

	@Inject
	private MemberService msv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info("register check 1");
		log.info("mvo >>>>> "+mvo);
		
		int isOK = msv.signUp(mvo);
		log.info("signUp >>>>> "+(isOK > 0 ? "Success" : "Fail"));
		
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String login(MemberVO mvo, Model m, HttpServletRequest request) {
		log.info("login check 1");
		log.info("mvo >>>>> "+mvo);
		
		// 입력 받은 mvo 객체가 DB의 member와 일치하는 것이 있는지 체크
		MemberVO loginMvo = msv.isUser(mvo);
		
		if(loginMvo != null) {
			// 로그인 성공시
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", loginMvo);
			ses.setMaxInactiveInterval(60*10);
		}else {
			// 로그인 실패시
			m.addAttribute("msg_login", -1);
		}
		
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(Model m, HttpServletRequest request) {
		log.info("logout check 1");
		
		// 로그아웃 실행 전 last_login update
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		msv.last_loginUpdate(mvo.getId());
		
		// 세션 객체 삭제 => 세션 끊기
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		m.addAttribute("msg_logout", 1);
		
		return "index";
	}
	
	@GetMapping("/modify")
	public void modify() {}
	
	@PostMapping("/edit")
	public String edit(MemberVO mvo, RedirectAttributes re) {
		log.info("edit check 1");
		int isOK = msv.loginUserUpdate(mvo);
		log.info("edit >>>>> "+(isOK > 0 ? "Success" : "Fail"));
		
		re.addFlashAttribute("msg_edit", isOK);
		// return을 index에서 redirect:/member/logout로 변경하여 아래 코드는 생략
		/*
		수정 완료 후 세션 끊기
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		m.addAttribute("msg_edit", 1);
		 */
		return "redirect:/member/logout";
	}
	
	@GetMapping("/withdrawal")
	public String withdrawal(HttpServletRequest request, Model m) {
		log.info("withdrawal check 1");
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		String loginID = mvo.getId();
		
		int isOK = msv.withdrawal(loginID);
		log.info("withdrawal >>>>> "+(isOK > 0 ? "Success" : "Fail"));
		
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		m.addAttribute("msg_withdrawal", isOK);
		
		return "index";
	}
}
