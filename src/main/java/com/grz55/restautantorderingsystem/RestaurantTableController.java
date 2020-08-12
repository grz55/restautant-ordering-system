package com.grz55.restautantorderingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantTableController {

    private RestaurantTableService restaurantTableService;

    @Autowired
    public RestaurantTableController(RestaurantTableService restaurantTableService) {
        this.restaurantTableService = restaurantTableService;
    }

    @GetMapping("/tables")
    public List<RestaurantTable> getAllRestaurantTables() {
        return restaurantTableService.findAll();
    }

    @GetMapping("/tables/{id}")
    public RestaurantTable getRestaurantTableById(@PathVariable Long id) {
        return restaurantTableService.findById(id).orElseThrow(() -> new RestaurantTableNotFoundException(id));
    }

    @PostMapping("/tables")
    public RestaurantTable addRestaurantTable(@RequestBody RestaurantTable restaurantTable) {
        return restaurantTableService.save(restaurantTable.getNumber());
    }

    @DeleteMapping("/tables/{id}")
    public void deleteRestaurantTableById(@PathVariable Long id) {
        if (restaurantTableService.findById(id).isPresent()) {
            restaurantTableService.deleteById(id);
        } else {
            throw new RestaurantTableNotFoundException(id);
        }
    }
}
