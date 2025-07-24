package com.example.mall.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mall.domain.Todo;
import com.example.mall.dto.TodoDTO;
import com.example.mall.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
	private final ModelMapper modelMapper;
	private final TodoRepository todoRepository;

	@Override
	public Long register(TodoDTO todoDTO) {
		log.info("------------------------------");
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		Todo savedTodo = todoRepository.save(todo);
		return savedTodo.getId();
	}

	@Override
	public TodoDTO get(Long tno) {
		Optional<Todo> result = todoRepository.findById(tno);
		Todo todo = result.orElseThrow();
		TodoDTO todoDTO = modelMapper.map(todo, TodoDTO.class);
		return todoDTO;
	}

	@Override
	public void modify(TodoDTO todoDTO) {
		Optional<Todo> result = todoRepository.findById(todoDTO.getTno());
		Todo todo = result.orElseThrow();
		todo.setTitle(todoDTO.getTitle());
		todo.setWriter(todoDTO.getWriter());
		todo.setDueDate(todoDTO.getDueDate());
		
		todoRepository.save(todo);
	}

	@Override
	public void delete(Long tno) {
		todoRepository.deleteById(tno);
	}
}
