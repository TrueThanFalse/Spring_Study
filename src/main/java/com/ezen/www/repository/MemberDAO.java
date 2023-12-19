package com.ezen.www.repository;

import com.ezen.www.domain.MemberVO;

public interface MemberDAO {

	MemberVO getUser(String id);

	int insert(MemberVO mvo);

	void last_loginUpdate(String id);

	int notPasswordUpdate(MemberVO mvo);

	int PasswordUpdate(MemberVO mvo);

	int withdrawal(String loginID);

}
