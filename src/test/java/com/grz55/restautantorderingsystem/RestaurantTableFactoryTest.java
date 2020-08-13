package com.grz55.restautantorderingsystem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantTableFactoryTest {

    @InjectMocks
    RestaurantTableFactory restaurantTableFactory;

    @Mock
    RestaurantTableRepository restaurantTableRepository;

    @Before
    public void init() {
        RestaurantTable restaurantTable = new RestaurantTable(1);
        when(restaurantTableRepository.findAll()).thenReturn(Arrays.asList(restaurantTable));
    }

    @Test(expected = RestaurantTableFoundException.class)
    public void shouldReturnRestaurantTableFoundException() {
        RestaurantTable restaurantTable2 = restaurantTableFactory.createTable(1);
    }

    @Test
    public void shouldReturnRestaurantTable() {
        assertEquals(new RestaurantTable(2).getId(), restaurantTableFactory.createTable(2).getId());
        assertEquals(new RestaurantTable(2).getNumber(), restaurantTableFactory.createTable(2).getNumber());
    }

}