package com.ezen.www.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO mdao;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	HttpServletRequest request;

	@Override
	public int signUp(MemberVO mvo) {
		log.info("register check 1");
		// 규칙 1. 아이디가 중복되면 회원가입 실패
		// => 아이디만 주고 DB에서 일치하는 mvo 객체를 리턴 받기
		// 일치하는 유저가 있으면 가입 실패, 없다면 가입 성공
		
		MemberVO tempMvo = mdao.getUser(mvo.getId());
		if(tempMvo != null) {
			// 기존 아이디가 있는 경우 
			return 0;
		}
		
		// password와 id가 null이거나 값이 없다면 가입 불가
		if(mvo.getId() == null || mvo.getId().length() == 0) {
			return 0;
		}
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			return 0;
		}
		
		// 회원가입 진행
		// password는 암호화하여 가입
		// 암호화 => encode
		// 비밀번호 일치 확인 => matches(입력된 pw, 암호화된 pw) => true & false
		String pw = mvo.getPw();
		String encodePw = passwordEncoder.encode(pw);
		mvo.setPw(encodePw);
		
		// 회원가입
		int isOK = mdao.insert(mvo);
		
		return isOK;
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		log.info("login check 2");
		// 로그인 유저 확인
		// 아이디를 주고 해당 아이디의 객체를 리턴 받기
		MemberVO tempMvo = mdao.getUser(mvo.getId()); // 회원가입할 때 사용한 메서드 호출
		
		// 해당 아이디가 없는 경우
		if(tempMvo == null) {
			return null;
		}
		
		// matches 비교
		if(passwordEncoder.matches(mvo.getPw(), tempMvo.getPw())) {
			return tempMvo;
		}
		
		return null;
	}

	@Override
	public void last_loginUpdate(String id) {
		log.info("logout check 2");
		mdao.last_loginUpdate(id);
	}

	@Override
	public int loginUserUpdate(MemberVO mvo) {
		log.info("edit check 2");
		// 비밀번호를 변경하지 않았을 때와 변경했을 때의 경우를 나눠야 함
		// pw의 여부에 따라서 변경사항을 나누어 처리
		// pw가 없다면 기존값으로 설정, pw가 있다면 암호화 처리하여 수정
		
		// 가능하면 DB에서 복잡하게 처리하지말고 JAVA에서 전부 처리해서 DB로 넘어가는 것이 비용적 측면으로 좋다.
		// DB 구문은 최대한 간결한 것이 효율적이고 비용이 적게 든다.
		/*
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			return mdao.notPasswordUpdate(mvo);
		}else {
			String pw = mvo.getPw();
			String encodePw = passwordEncoder.encode(pw);
			mvo.setPw(encodePw);
			return mdao.PasswordUpdate(mvo);
		}
		*/
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			MemberVO sesMvo = (MemberVO)request.getSession().getAttribute("ses");
			mvo.setPw(sesMvo.getPw());
		}else {
			String pw = mvo.getPw();
			String encodePw = passwordEncoder.encode(pw);
			mvo.setPw(encodePw);
		}
		
		return mdao.PasswordUpdate(mvo);
	}

	@Override
	public int withdrawal(String loginID) {
		log.info("withdrawal check 2");
		return mdao.withdrawal(loginID);
	}
}
