package com.mallppang.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mallppang.freeboard.FreeComment;
import com.mallppang.freeboard.FreeCommentService;

@SpringBootTest
class MallppangApplicationTests {
	@Autowired FreeCommentService commentService;

	@Test
	void contextLoads() {
//		List<FreeComment> a = commentService.getList(1L);
//		for(int i = 0; i < a.size(); i++)
//			System.out.println(a.get(i));
	}
}
