package com.ezen.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO {

	private int pageNo; // 현재 페이지 번호
	private int qty; // 한 페이지에 보여줄 게시글 수량 (10개)
	
	// 검색에 사용되는 2개의 멤버변수 추가
	private String type;
	private String keyword;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public int getPageStart() {
		return (this.pageNo-1) * this.qty;
	}
	
	// 여러가지의 타입을 같이 검색하기 위해 type을 배열로 구분화하는 메서드
	// getter로 만들기 위해서 get을 붙이고 그 다음 문자는 반드시 대문자
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
}
