package com.ezen.www.service;

import com.ezen.www.domain.MemberVO;

public interface MemberService {

	int signUp(MemberVO mvo);

	MemberVO isUser(MemberVO mvo);

	void last_loginUpdate(String id);

	int loginUserUpdate(MemberVO mvo);

	int withdrawal(String loginID);

}
