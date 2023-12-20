package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);
	// Object를 List<BoardVO>로 변경

	BoardVO getDetail(int bno);
	// Object를 BoardVO로 변경

	int edit(BoardVO bvo);

	int delete(int bno);

	int getTotalCount(PagingVO pgvo);

}
