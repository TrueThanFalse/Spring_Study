package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);
	// Object를 List<BoardVO>로 변경

	BoardDTO getDetail(int bno);
	// Object를 BoardVO로 변경

	int edit(BoardDTO bdto);

	int delete(int bno);

	int getTotalCount(PagingVO pgvo);

	int removeFile(FileVO fvo);

	int removeFile2(String uuid);

}
