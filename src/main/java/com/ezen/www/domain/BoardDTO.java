package com.ezen.www.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	
	// 하나의 보드에 여러개의 파일이 있을 수 있다는 뜻
	private BoardVO bvo;
	private List<FileVO> flist;
	
	// <주의사항> 두번째 글자를 대문자로 쓰면 JSP가 인지를 못함 : JSP 버그로 추측
	
}
