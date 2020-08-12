package com.grz55.restautantorderingsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@ContextConfiguration(classes = RestautantOrderingSystemApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestautantOrderingSystemApplicationTests {

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    @Test
    void contextLoads() {
    }

    @Test
	void testCreateTable() {
        RestaurantTableFactory restaurantTableFactory = new RestaurantTableFactory(restaurantTableRepository);
        RestaurantTable restaurantTable = restaurantTableFactory.createTable(1);
        RestaurantTable savedRestaurantTable = restaurantTableRepository.save(restaurantTable);
        assertNotNull(savedRestaurantTable);

    }

}
