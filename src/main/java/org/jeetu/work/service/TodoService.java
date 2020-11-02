package org.jeetu.work.service;

import java.util.List;
import java.util.Optional;

import org.jeetu.work.bean.Todo;
import org.jeetu.work.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

	@Autowired
	private TodoRepository todoRepository;

	public List<Todo> getAllTodoList() {
		return todoRepository.findAll();
	}

	public Todo getTodoById(String id) {
		Optional<Todo> todoData = todoRepository.findById(id);
		if (todoData.isPresent()) {
			return todoData.get();
		} else {
			logger.error("No record found");
			throw new RuntimeException("No record found");
		}
	}

	public void saveTodo(Todo todo) {
		todoRepository.save(todo);
	}

	public void deleteTodo(String id) {
		Optional<Todo> todoData = todoRepository.findById(id);
		if (todoData.isPresent()) {
			todoRepository.deleteById(id);
		} else {
			logger.error("No record found");
			throw new RuntimeException("No record found");
		}
		
	}
}
