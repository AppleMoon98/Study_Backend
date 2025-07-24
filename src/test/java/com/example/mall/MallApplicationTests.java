package com.example.mall;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.mall.domain.Todo;
import com.example.mall.dto.TodoDTO;
import com.example.mall.repository.TodoRepository;
import com.example.mall.service.TodoService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class MallApplicationTests {
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoService todoService;
	
	@Test
	void contextLoads() {
		log.info("----------------------------");
		log.info(todoRepository);
		
		// 생성 - C
//		for(int i = 1; i <= 100; i++) {
//			Todo todo = Todo.builder().title("title - " + i).writer("writer - " + i).dueDate(LocalDate.now()).build();
//			todoRepository.save(todo);
//		}
		
		// 수정 - U
//		Optional<Todo> result = todoRepository.findById(3L);
//		
//		Todo todo = result.orElseThrow();
//		todo.setTitle("수정된 데이터 3");
//		todo.setWriter("관리자");
//		todo.setDueDate(LocalDate.of(2024, 1, 1));
//		todoRepository.save(todo);
		
		// 삭제 - D
		// 작성 답안
//		todoRepository.delete(todoRepository.findById(1L).get());
		// 모범 답안
//		todoRepository.deleteById(1L);
		
		// 출력 - R
//		Page<Todo> result = todoRepository.findAll(PageRequest.of(0, 10, Sort.by("id").descending()));
//		log.info(result.getTotalElements());
//		result.getContent().stream().forEach(todo -> log.info(todo));
		
//		TodoDTO todoDTO = TodoDTO.builder().title("Service test..").writer("writer").dueDate(LocalDate.of(2020, 10, 10)).build();
//		long tno = todoService.register(todoDTO);
//		log.info("줄 번호 : " + tno);
		
		Long tno = 101L;
		TodoDTO todoDTO = todoService.get(tno);
		log.info(todoDTO);
	}

}
