package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	// implements BoardService 작성하는 것을 잊지 말고 반드시 작성해야 BoardService 인터페이스와 연동됨
	
	@Inject
	private BoardDAO bdao;

	@Override
	public int register(BoardVO bvo) {
		log.info("register check 2");
		return bdao.register(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("list check 2");
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info("detail check 2");
		bdao.read_countUP(bno);
		// commit을 spring이 자동으로 해줌.? 맞나? 검색 필요
		return bdao.getDetail(bno);
	}

	@Override
	public int edit(BoardVO bvo) {
		log.info("edit check 2");
		return bdao.edit(bvo);
	}

	@Override
	public int delete(int bno) {
		log.info("delete check 2");
		return bdao.delete(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}
}
