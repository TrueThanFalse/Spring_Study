package com.ezen.www.handler;

import com.ezen.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingHandler {

	private int startPage; // 페이징네이션 시작 번호 => 1, 11, 21 ...
	private int endPage; // 10, 20, 30 ...
	private boolean prev, next;
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		// pgvo와 totalCount는 BoardController에서 파라미터로 받을 예정
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		// (1~10 클릭):10, (11~20 클릭):20 ...
		// 1/10.0 => 0.1 => 올림 => 1 => 1*10 = 10
		// 2/10.0 => 0.2 => 올림 => 1 => 1*10 = 10
		// 11/10.0 => 1.1 => 올림 => 2 => 2*10 = 20
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)pgvo.getQty()) * pgvo.getQty();
		this.startPage = endPage - 9;
		
		// 한 페이지의 값이 다 차지 않은 나머지의 페이지를 하나의 페이지로 만들기 위해서 올림을 사용
		// 111 / 10 => 11.1 => 올림 => 12 => 12페이지 생성됨
		int realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
		
		// 생성자로 페이지네이션 셋팅 완료
	}
}
