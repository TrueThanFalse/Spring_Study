package com.ezen.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.www.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Component // 핸들러를 bean 객체로 인식하라는 어노테이션
@Slf4j
@AllArgsConstructor
public class FileHandler {
	// 파일 객체를 파라미터로 받아서 flist로 리턴
	// => FileHandler는 파일을 저장하고 fvo를 생성하는 역할
	
	// 실제 파일이 저장되는 경로(기본 경로)
	// FileVO의 save_dir은 기본 경로 뒤의 값임
	private final String UP_DIR = "D:\\HMS\\myProject\\java\\fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		
		// multipartFile을 받아서 FileVO 형태로 저장한 후 list를 리턴
		// 오늘 날짜 폴더를 생성해서 그 장소에 파일들을 저장(가변 형태로 저장)
		List<FileVO> flist = new ArrayList<FileVO>();
		
		// 오늘 날짜 경로 생성
		LocalDate date = LocalDate.now();
		String today = date.toString();
		log.info("date >>>>> " + today);
		
		// 오늘 날짜(today) 폴더 생성
		today = today.replace("-", File.separator);
		File folders = new File(UP_DIR, today);
		if(!folders.exists()) { // exists : 폴더가 이미 존재하는지 체크
			folders.mkdirs(); // 폴더 생성 명령
			// mkdir : 폴더 1개 생성
			// mkdirs : 하위 폴더까지 모두 생성
		}
		
		// 리스트 설정
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			// UP_DIR(기본 공통 경로) 제외한 하위 오늘 날짜 경로만 set
			fvo.setSave_dir(today);
			// file.getSize()는 long 반환 => FileVO의 file_size는 반드시 long 타입으로 선언해야 함
			fvo.setFile_size(file.getSize());
			
			log.info("getName >>>>> " + file.getName());
			log.info("getOriginalName >>>>>" + file.getOriginalFilename());
			
			// 파일 이름(originalName())은 파일 경로를 포함하고 있을 수도 있음
			String originalFileName = file.getOriginalFilename(); // 실제 파일명 추출
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFile_name(onlyFileName);
			
			// UUID 생성 => 파일명 중복 방지를 위해 활용
			UUID uuid = UUID.randomUUID();
			log.info("UUID >>>>> " + uuid.toString());
			String uuidStr = uuid.toString();
			fvo.setUuid(uuidStr);
			
			// <----- 여기까지 fvo의 setting 완료 ----->
			
			// 디스크에 저장할 파일 객체를 생성하고 저장하기
			// uuid_fileName & uuid_th_fileName
			String fullFileName = uuidStr + "_" + onlyFileName;
			File storeFile = new File(folders, fullFileName);
			
			// 저장 : 폴더가 없거나, 저장 파일이 없다면 io Exception 발생함
			try {
				file.transferTo(storeFile); // 저장 메서드
				
				// 파일 타입을 결정 => 이미지 파일일때만 썸네일 생성
				if(isImageFile(storeFile)) { // tika를 활용하여 만든 메서드 활용
					fvo.setFile_type(1);
					// 썸네일 생성
					File thumbNail = new File(folders, uuidStr + "_th_" + onlyFileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info(">>>>> file 저장 Error");
				e.printStackTrace();
			}
			
			flist.add(fvo); // 완성된 fvo를 flist에 추가
		}
		
		return flist;
	}
	
	// tika를 활용하여 파일의 형식을 체크 => 이미지 파일이 맞는지 확인
	public boolean isImageFile(File storeFile) throws IOException {
		String multimedeaType = new Tika().detect(storeFile); // image/png | image/jpg
		return multimedeaType.startsWith("image") ? true : false;
	}
}
