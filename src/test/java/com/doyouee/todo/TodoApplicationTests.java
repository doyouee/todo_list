package com.doyouee.todo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doyouee.todo.todoList.entity.TodoInfoEntity;
import com.doyouee.todo.todoList.repository.TodoInfoRepository;
import com.doyouee.todo.todoList.service.TodoInfoService;

@SpringBootTest
class TodoApplicationTests {
	// @Autowired TodoInfoService tService;
	@Autowired TodoInfoRepository tRepo;
	@Test
	void loadTodo() throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date start = formatter.parse("2022-12-26");
		Date end = formatter.parse("2022-12-28");
		List<TodoInfoEntity> list = tRepo.findByEndDtBetweenAndMiSeq(start, end, 1L); // 1L : 회원코드 1의 Long타입
		for(TodoInfoEntity t : list) {
			System.out.println(t);
		}
	}

}
