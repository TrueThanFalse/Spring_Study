package com.ezen.www.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// DB와 연결하기 위해서 root-context.xml까지 연결시켜줘야 함
@RunWith(SpringJUnit4ClassRunner.class)
// @RunWith : JUnit이 테스트할 때 필수로 있어야 하는 어노테이션
@Slf4j
public class BoardTest {
	
	@Inject
	private BoardDAO bdao;
	
	@Test
	public void insertBoard() {
		// 테스트를 위해선 설정 어노테이션 2개 (@ContextConfiguration, @RunWith)
		// @Test 어노테이션 1개가 필수적이다.
		// 화면이 없을 때 DB와 연결이 잘 되었는지 확인할 수 있음
		log.info("Test Insert in >>>>>");
		for(int i=0; i<200; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Test Title"+i);
			bvo.setWriter("Tester");
			bvo.setContent("Test Content"+1);
			
			bdao.register(bvo);
		}
	}
}
