package com.grz55.restautantorderingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantTableService {

    private RestaurantTableRepository restaurantTableRepository;
    private RestaurantTableFactory restaurantTableFactory;

    @Autowired
    public RestaurantTableService(RestaurantTableRepository restaurantTableRepository, RestaurantTableFactory restaurantTableFactory) {
        this.restaurantTableRepository = restaurantTableRepository;
        this.restaurantTableFactory = restaurantTableFactory;
    }

    public List<RestaurantTable> findAll() {
        return restaurantTableRepository.findAll();
    }

    public RestaurantTable save(int number) {
        RestaurantTable restaurantTable = restaurantTableFactory.createTable(number);
        return restaurantTableRepository.save(restaurantTable);
    }

    public void deleteById(Long id) {
        restaurantTableRepository.deleteById(id);
    }

    public Optional<RestaurantTable> findById(Long id) {
        return restaurantTableRepository.findById(id);
    }
}
