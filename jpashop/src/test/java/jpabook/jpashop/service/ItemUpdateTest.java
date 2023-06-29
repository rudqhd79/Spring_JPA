package jpabook.jpashop.service;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemUpdateTest {

	@Autowired
	EntityManager em;
	
	@Test
	public void updateTest() throws Exception {
		Book book = em.find(Book.class, 1L);
		
		book.setName("asdffgdfda");
		
		// 변경감지?
		
	}
}
