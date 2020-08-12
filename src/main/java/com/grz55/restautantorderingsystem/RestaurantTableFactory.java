package com.grz55.restautantorderingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantTableFactory {

    private RestaurantTableRepository restaurantTableRepository;

    @Autowired
    public RestaurantTableFactory(RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
    }

    public RestaurantTable createTable(int number) {
        List<RestaurantTable> allRestaurantTables = restaurantTableRepository.findAll();

        if (allRestaurantTables.isEmpty())
            return new RestaurantTable(number);

        for (int i = 0; i < allRestaurantTables.size(); i++) {
            if (allRestaurantTables.get(i).getNumber() == number)
                throw new RestaurantTableFoundException(number);
        }
        return new RestaurantTable(number);
    }
}
