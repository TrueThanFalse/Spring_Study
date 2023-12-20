package com.ezen.www.contorller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

// 비동기 controller는 Rest 자료를 받을 수 있도록 RestController
@RestController
@RequestMapping("/comment/*")
@Slf4j
public class CommentController {
	
	@Inject
	private CommentService csv;
	
	
}
