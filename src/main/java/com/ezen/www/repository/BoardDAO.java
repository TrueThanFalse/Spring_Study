package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardDAO {

	int register(BoardVO bvo);
	// 인터페이스에 메서드가 자동완성 되었으면 boardMapper.xml로 이동하여 SQL문 작성

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	void read_countUP(int bno);

	int edit(BoardVO bvo);

	int delete(int bno);

	int getTotalCount(PagingVO pgvo);

}
