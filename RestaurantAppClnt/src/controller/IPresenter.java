package controller;

import java.io.IOException;
import java.util.ArrayList;

import models.Product;
import models.Restaurant;

public interface IPresenter {

	public void showAdvertisement(String advertisement);

	public void getRestaurants(ArrayList<Restaurant> restaurants);

	public Restaurant createRestaurant(String name, byte[] imageData) throws IOException;

	public void addRestaurantToList(Restaurant restaurant);

	public void addProductToRestaurant(String restaurantName, Product product);

	public void notificatoToRestaurant(String restaurant, String notification);

}
