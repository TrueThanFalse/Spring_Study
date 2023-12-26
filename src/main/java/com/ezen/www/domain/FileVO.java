package com.ezen.www.domain;

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
public class FileVO {

//			CREATE TABLE file(
//			uuid varchar(256) NOT NULL,
//			save_dir VARCHAR(256) NOT NULL,
//			file_name VARCHAR(256) NOT NULL,
//			file_type TINYINT(1) DEFAULT 0,
//			bno INT,
//			file_size INT,
//			reg_date DATETIME DEFAULT NOW(),
//			PRIMARY KEY(uuid));
	
	private String uuid;
	private String save_dir;
	private String file_name;
	private int file_type;
	private int bno;
	private long file_size; // file_size는 리턴을 long으로 받아서 long으로 만듬
	private String reg_date;
}
