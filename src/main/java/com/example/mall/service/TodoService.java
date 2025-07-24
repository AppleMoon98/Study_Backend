package com.example.mall.service;

import com.example.mall.dto.TodoDTO;

public interface TodoService {
	Long register(TodoDTO todoDTO);	// 글 등록
	TodoDTO get(Long tno);	// 글 조회
	void modify(TodoDTO todoDTO);	// 글 수정
	void delete(Long tno); // 글 삭제
}
