package com.ezen.www.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

/*
project의 properties -> java build path -> libraries -> classpath 클릭
-> add library... -> JUnit -> version 4 -> apply
왜 JUnit5가 아닌 JUnit4를 사용하는가? => pom.xml을 보면 JUnit Test version이 4.7이다.
JUnit5는 SpringBoot에서 사용하자
 */

// Test 클래스명 우클릭 -> Run as -> JUnit Test 실행 -> JUnit 콘손창에 초록색이 나오면 정상 test 완료

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// DB와 연결하기 위해서 root-context.xml까지 연결시켜줘야 함
@RunWith(SpringJUnit4ClassRunner.class)
// @RunWith : JUnit이 test 실행할 수 있도록 해주는 필수적인 어노테이션
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
