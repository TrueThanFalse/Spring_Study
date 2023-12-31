package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService{
	
	@Inject
	private CommentDAO cdao;
	
	@Inject
	private BoardDAO bdao;

	@Override
	public int post(CommentVO cvo) {
		bdao.boardVOCommentCountUp(cvo.getBno());
		return cdao.post(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		return cdao.getList(bno);
	}

	@Override
	public int commentDelete(int cno, int bno) {
		bdao.boardVOCommentCountDown(bno);
		return cdao.commentDelete(cno);
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.modify(cvo);
	}
	
}
