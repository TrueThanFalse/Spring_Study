package com.ezen.www.contorller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

// 비동기 controller는 Rest 자료를 받을 수 있도록 RestController
@RestController
@RequestMapping("/comment/*")
@Slf4j
public class CommentController {
	
	@Inject
	private CommentService csv;
	
	// ResponseEntity 객체 활용 : body 내용 + httpStatus 상태, 2가지를 함께 내보내야 함
	// ResponseEntity<String> : String인 이유는 String으로 리턴되어 나갈 것이기 때문
	// ResponseEntity에 ctrl+spacebar해서 설명서를 한번 읽어보면 구성을 참고할 수 있음
	@PostMapping(value="/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
		// @RequestBody : body 값 추출
		// consumes : 가져오는 데이터의 타입
		// produces : 내보내는 데이터의 타입(나가는 데이터 타입) => MediaType(import는 tomcat이 아닌 http)
		// 데이터 형식은 보통 json과 text
		// json => application/json & text => text_plain
		// consumes, produces에 작성되는 것은 Content-Type을 작성하는 것임
		log.info("cvo >>>>> "+cvo);
		
		int isOK = csv.post(cvo);
		
		return (isOK > 0) ? new ResponseEntity<String>("1", HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		// HttpStatus.OK : 200 상태를 의미 함
		// HttpStatus.INTERNAL_SERVER_ERROR : 500 Error 상태를 의미 함
		/* < Http 상태 코드 >
		1xx (정보): 요청을 받았으며 프로세스를 계속한다
		2xx (성공): 요청을 성공적으로 받았으며 인식했고 수용하였다
		3xx (리다이렉션): 요청 완료를 위해 추가 작업 조치가 필요하다
		4xx (클라이언트 오류): 요청의 문법이 잘못되었거나 요청을 처리할 수 없다
		5xx (서버 오류): 서버가 명백히 유효한 요청에 대해 충족을 실패했다
		*/
	}
	
	// Path 경로를 활용하여 데이터 가져오기
	// 변수를 여러개 받는다면 "/{}/{}/{}..." 이렇게 받을 수 있음
	@GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVO>> list(@PathVariable("bno") int bno){
		// @PathVariable : 경로 값 추출
		log.info("bno >>>>> "+bno);
		
		List<CommentVO> list = csv.getList(bno);
		// pom.xml에 등록한 jackson-databind가 알아서 json 처리를 진행함
		
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);
	}
	
	// 삭제
	// {cnoVal}는 실질적으로는 숫자로 들어옴, 따라서 {}내부 명칭은 자유롭게 정할 수 있음
	// JS에서 method를 delete로 전송했으므로 @DeleteMapping로 받아야 함
	@DeleteMapping(value = "/{cnoVal}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> commentDelete(@PathVariable("cnoVal") int cno){
		// 통신간 기본 타입은 String이므로 사실상 produces = MediaType.APPLICATION_JSON_VALUE은 생략해도 된다.
		// 하지만 간혹 한글을 인식 못하는 encoding 문제가 발생 할 수 있어서
		// produces = MediaType.APPLICATION_JSON_VALUE를 작성하면 확실하게 인식 시켜줄 수 있는 장점이 있다.
		log.info("bno >>>>> "+cno);
		
		int isOK = csv.commentDelete(cno);
		log.info("commentDelete >>>>> "+(isOK > 0 ? "Success":"Fail"));
		
		return (isOK > 0) ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// 위 코드를 @ResponseBody를 활용하여 만들 수도 있다.
	/*
	@ResponseBody
	@DeleteMapping(value = "/{cnoVal}")
	public String commentDelete(@PathVariable("cnoVal") int cno){
		log.info("bno >>>>> "+cno);
		
		int isOK = csv.commentDelete(cno);
		log.info("commentDelete >>>>> "+(isOK > 0 ? "Success":"Fail"));
		
		return isOK > 0 ? "1":"0";
	}
	 */
	
	// @ResponseBody를 활용한 수정 로직 처리
	@ResponseBody
	@PutMapping("/modify")
	public String modify(@RequestBody CommentVO cvo) {
		log.info("cvo >>>>> "+cvo);
		
		int isOK = csv.modify(cvo);
		log.info("modify >>>>> "+(isOK > 0 ? "Success":"Fail"));
		
		return isOK > 0 ? "1":"0";
	}
//	@PutMapping("/modify")
//	public ResponseEntity<String> modify(@RequestBody CommentVO cvo) {
//		int isOK csv.modify(cvo);
//		return (isOK > 0) ? new ResponseEntity<String>("1", HttpStatus.OK)
//				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
}
