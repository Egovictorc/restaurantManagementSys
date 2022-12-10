package com.michael.restaurantmanagementsystem.service;

import javafx.scene.Node;

public interface RestaurantService<T> {
    public Node createView(T t);
}
