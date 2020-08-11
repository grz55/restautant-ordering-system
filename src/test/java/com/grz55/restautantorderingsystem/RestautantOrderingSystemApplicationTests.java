package com.grz55.restautantorderingsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestautantOrderingSystemApplicationTests {

	@Autowired
	TableRepository tableRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateTable(){
		Table table = new Table(1);
		tableRepository.save(table);

	}

}
