package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import models.Product;
import models.Restaurant;

public class FileManager {

	private ArrayList<Restaurant> restaurants;

	public FileManager() {
		this.restaurants = new ArrayList<Restaurant>();
	}
	
	public void registerRestaurant(Restaurant restaurant) {
		restaurants.add(restaurant);
	}
	
	public void registerProductToRestaurant(String restaurantName, Product product) {
		for (Restaurant restaurant : restaurants) {
			if (restaurant.getName().equals(restaurantName)) {
				restaurant.addProduct(product);
			}
		}
	}

	public void readRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public Restaurant createRestaurant(String name, byte[] imageData) throws IOException {
		File ImageFile = new File("src/resources/images/" + name + ".png");
		Files.write(ImageFile.toPath(), imageData);
		return new Restaurant(ImageFile, name);
	}
}
