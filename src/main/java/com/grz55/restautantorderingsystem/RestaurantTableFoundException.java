package com.grz55.restautantorderingsystem;

public class RestaurantTableFoundException extends RuntimeException {

    public RestaurantTableFoundException(int number) {
        super("Restaurant table number " + number + " already exists. Please choose another number");
    }
}
