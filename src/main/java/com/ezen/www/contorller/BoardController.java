package com.ezen.www.contorller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log 관련 어노테이션
@RequestMapping("/board/*")
@Controller
public class BoardController {

	@Inject
	private BoardService bsv;
	
	// file upload를 위해서 추가
	@Inject
	private FileHandler fhd;
	
	// case가 없으므로 각각 맵핑을 적용해야 함
	// 들어오는 데이터가 get인지 post인지 어노테이션으로 인식 시켜줘야함
	
	// 경로와 리턴의 값이 같을 경우
	// 들어오는 경로 : /board/register & 나가는 경로 : /board/register
	// 이런 경우에는 void가 가능
	@GetMapping("/register")
	public void register() {
		
	}
	
	// @PostMapping은 객체로 들어옴 따라서 BoardVO를 파라미터로 넣어줘야 함
	// @RequestParam("name") String name : 파라미터로 받을 때
	@PostMapping("/register")
	public String register(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		/*
		 * file upload를 위해서 @RequestParam으로 files를 불러오고
		 * required : 필수 여부 / false로 설정하면 파라미터가 존재하지 않아도 Error가 발생하지 않음
		 * MultipartFile 클래스를 활용하여 files를 배열로 불러오기
		 */
		log.info("register check 1");
		log.info("bvo >>>>> " + bvo);
		log.info("files >>>>> " + files.toString());
		
		// int isOK = bsv.register(bvo); => file upload 처리를 위해 주석 처리
		
		// FileHandler 처리
		List<FileVO> flist = null;
		
		// 파일이 있을 경우만 fhd를 호출
		if(files[0].getSize() > 0) {
			// 0번지의 size가 0보다 크다는 것은 무언가 파일이 존재한다는 것
			// files.length로 비교하면 Error가 발생할 수도 있음
			// 배열보다 정확하게 자료 자체의 크기를 비교하면 오류 발생을 예방할 수 있음
			
			flist = fhd.uploadFiles(files);
			log.info("flst >>>>> " + flist);
		}
				
		// 목적지 경로
		// BoardController의 list로 보내기
		return "redirect:/board/list";
		// redirect: => Controller로 전송하는 키워드? 내부 로직을 한번 실행해주는 키워드?, 검색해서 정확하게 찾아보기
	}
	
	// 들어 오는 경로 : /board/list & 보내는 경로 /board/list => 사실상 void 처리해도 문제 없음
	@GetMapping("list")
	public String list(Model m, PagingVO pgvo) {
		log.info("list check 1");
		// 리턴 타입은 목적지 경로에 전송할 때 보내는 데이터 형식
		log.info("pgvo >>>>> "+pgvo);
		// pgvo 같은 객체를 활용하면 어떤 값이든 하나만 들어오면 NullPointerException을 예방할 수 있음
		
		// Model 객체 => setAttribute 역할을 할 수 있는 객체
		// addAttribute => request 객체의 setAttributes 같은 역할
		// addAttribute : 파라미터로 데이터를 전송
		m.addAttribute("list", bsv.getList(pgvo));
		
		// ph 객체 생성 (pgvo, totalCount)
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("ph", ph);
		
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"}) // 파라미터가 여러개면 {} 중괄호 필요 
	public void detail(Model m, @RequestParam("bno") int bno) {
		log.info("detail check 1");
		log.info("bno >>>{} " + bno);
		m.addAttribute("bvo", bsv.getDetail(bno));
		// modify로 들어올 때 read_count가 1개 올라가는 모순이 발생함
	}

	@PostMapping("/edit")
	public String edit(BoardVO bvo, Model m) {
		// Model 객체는 Spring이 생성해서 edit 메서드에 넣어주는 것임
		log.info("edit check 1");
		log.info("bvo >>>{} " + bvo);
		
		// update
		int isOK = bsv.edit(bvo);
		log.info("edit >>>{} "+(isOK > 0 ? "Success" : "Fail"));
		
		m.addAttribute("bno", bvo.getBno());
		
		return "redirect:/board/detail";
		// bno가 필요 => Model 객체를 활용하거나 쿼리스트링으로 보낼 수 있음
		// return "redirect:/board/detail?bno="bvo.getBno();
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") int bno, RedirectAttributes re) {
		log.info("delete check 1");
		log.info("bno >>>{} "+bno);
		
		// 실질적으로 BoardVO 객체를 삭제 하는 것이 아닌 isDel을 Y로 update해야 함
		int isOK = bsv.delete(bno);
		
		// isOK는 페이지가 새로고침 될 때 유지될 필요가 없는 데이터임
		// redirect될 때 데이터를 보내는 객체 : RedirectAttributes
		// Model 객체는 Data가 계속 남아 있음, 따라서 현 상황에서는 Model은 사용하면 안 됨
		// Flash가 붙어 있는 addFlashAttribute : 한번만 일회성으로 data를 보낼 때 사용함
		re.addFlashAttribute("isDel", isOK);
		
		// 위 isDel을 보내면 addFlashAttribute를 받는 곳은 board/list가 아니라 list.jsp가 받음
		return "redirect:/board/list";
	}
	
	
}
