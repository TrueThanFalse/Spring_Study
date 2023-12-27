package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(int bno);

	int removeFile(FileVO fvo);
	
}
