package com.ezen.www.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService{
	
	@Inject
	private CommentDAO cdao;
	
}
