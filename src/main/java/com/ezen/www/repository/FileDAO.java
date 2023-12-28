package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(int bno);

	int removeFile(FileVO fvo);

	void boardVOFileCountDown(FileVO fvo);

	int fileremove(FileVO uuid);

	int fileremove2(String uuid);

	
}
