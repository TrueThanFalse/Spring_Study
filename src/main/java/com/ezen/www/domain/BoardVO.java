package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 *  create table board(
	bno int auto_increment,
	title varchar(200),
	writer varchar(200),
	content text,
	isDel varchar(10) default 'N',
	reg_date datetime default now(),
	read_count int,
	primary key(bno)
	);
	
	alter table board modify read_count int default 0;
 */

// lombok의 @data는 편리하지만 보안상 사용하지 않는 것이 옳다.

// 어노테이션을 활용하여 lombok import 하기
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // all 생성자
@ToString
@Setter
@Getter
public class BoardVO {
	
	// 특정한 멤버변수만 getter&setter 만들고 싶다면
	// 그 멤버변수의 위에 어노테이션을 달아주면 됨
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String isDel;
	private String reg_date;
	private int read_count;
	
}
