package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	// implements BoardService 작성하는 것을 잊지 말고 반드시 작성해야 BoardService 인터페이스와 연동됨
	
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;

	// file upload로 인해 수정하였음
	@Override
	public int register(BoardDTO bdto) {
		log.info("register check 2");
		// 기존 보드 내용을 DB에 저장
		int isOK = bdao.register(bdto.getBvo());
		// FileHandler에서는 bno가 생성되지 않으므로 설정할 수 없었음
		// 하지만 지금 이 시점에서 insert를 하면서 bno가 자동 생성됨
		
		// flist를 DB에 저장
		if(bdto.getFlist() == null) {
			// 유저가 파일을 업로드 하지 않을 수 있음
			// => flist가 null이 됨
			// Error가 발생하지 않도록 처리해야 함
			isOK *= 1; // 성공한 것으로 반환
		}else {
			// 파일 저장
			if(isOK > 0 && bdto.getFlist().size() > 0) {
				// bdto.getFlist().size() > 0 : 실질적으로 파일이 존재하는지 확인
				// fvo는 bno가 아직 설정되지 않았음
				// 현재 bdto 시점에서는 아직 bno가 생성되지 않음
				// register를 통해 자동으로 bno가 생성되었음 => 최신 bno를 불러오면 그 bno에 파일들이 저장되는 것임 
				int bno = bdao.selectBno();
				
				for(FileVO fvo : bdto.getFlist()) {
					// fvo 완성 => flist는 board에 종속되므로 file은 동일한 bno를 가져야됨
					fvo.setBno(bno);
					// 파일을 DB에 저장
					isOK *= fdao.insertFile(fvo);
					// DB에 파일이 저장되면 boardVO의 fileCount를 1 증가
					if(isOK == 1) {
						bdao.boardVOFileCountUP(bno);
					}
				}
			}
		}
		return isOK;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("list check 2");
		// 선생님의 commentCount update
		// board 테이블 전체에 CommentCount를 update 한 후에 list.jsp에 뿌리기
		int isOK = bdao.updateCommentCount();
		if(isOK == 0) {
			log.info("updateCommentCount Error");
		}
		return bdao.getList(pgvo);
	}

	// file upload로 인해 수정하였음
	@Override
	public BoardDTO getDetail(int bno) {
		log.info("detail check 2");
		
		bdao.read_countUP(bno);
		// commit을 spring이 자동으로 해줌.? 맞나? 검색 필요
		
		// 파일 업로드 관련 내용 추가
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBvo(bdao.getDetail(bno)); // 기존 게시글 내용을 DTO에 채우기
		boardDTO.setFlist(fdao.getFileList(bno)); // bno에 해당하는 모든 파일 리스트 검색
		
		return boardDTO;
	}

	@Override
	public int edit(BoardDTO bdto) {
		log.info("edit check 2");
		int isOK = bdao.edit(bdto.getBvo()); // 보드 내용 수정
		if(bdto.getFlist() == null) {
			isOK *= 1; // 이미 처리된 것과 같은 효과
		}else {
			if(isOK > 0 && bdto.getFlist().size() > 0) {
				int bno = bdto.getBvo().getBno();
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOK *= fdao.insertFile(fvo);
					// DB에 파일이 저장되면 boardVO의 fileCount를 1 증가
					if(isOK == 1) {
						bdao.boardVOFileCountUP(bno);
					}
				}
			}
		}
		return isOK;
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

	@Override
	public int removeFile(FileVO fvo) {
		log.info("removeFile check 2");
		bdao.boardVOFileCountDown(fvo);
		return fdao.removeFile(fvo);
	}

	@Override
	public int removeFile2(String uuid) {
		return fdao.fileremove2(uuid);
	}
}
