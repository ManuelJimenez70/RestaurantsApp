package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileManager {

	private static final String RESTAURANTS_PATH = "src/resources/persistance/restaurants.txt";
	private static final URL JSON_PATH = FileManager.class.getResource("/resources/persistance/restaurants.txt");

	private ArrayList<Restaurant> restaurants;

	public FileManager() throws IOException {
		this.restaurants = new ArrayList<Restaurant>();
		readJson();
		System.out.println(restaurants.size());
	}

	private void readJson() throws IOException {
		String text = String.join("", Files.readAllLines(Paths.get(JSON_PATH.getPath().substring(1))));
		JSONObject json = new JSONObject(text);
		JSONArray restaurantList = json.getJSONArray("restaurantList");
		for (int i = 0; i < restaurantList.length(); i++) {
			Restaurant restaurant = new Restaurant(
					new File(((JSONObject) (restaurantList.get(i))).get("image").toString()),
					((JSONObject) (restaurantList.get(i))).get("name").toString());
			JSONArray productList = ((JSONObject) (restaurantList.get(i))).getJSONArray("productList");
			for (int j = 0; j < productList.length(); j++) {
				restaurant.addProduct(new Product(((JSONObject) (productList.get(j))).get("nameProduct").toString(),
						((JSONObject) (productList.get(j))).get("prize").toString()));
			}
			this.restaurants.add(restaurant);
		}
	}

	public ArrayList<Restaurant> getRestaurants() {
		return this.restaurants;
	}

	public String getRestaurantInfo(Restaurant restaurant) {
		return null;
	}

	public void addRestaurant(String name, byte[] image) throws IOException {
		File ImageFile = new File("src/resources/images/" + name + ".png");
		Files.write(ImageFile.toPath(), image);
		this.restaurants.add(new Restaurant(ImageFile, name));
		writeJson();
		readJson();
	}

	public void addProductToRestaurant(String restaurantName, Product product) throws IOException {
		for (Restaurant restaurant : restaurants) {
			if (restaurant.getName().equals(restaurantName)) {
				restaurant.addProduct(product);
				writeJson();
				readJson();
			}
		}
	}

	public void sortList() {
		Collections.sort(this.restaurants, Comparator.comparing(Restaurant::getName));
	}

	public void writeJson() throws IOException {
		sortList();
		JSONObject json = new JSONObject();
		JSONArray restaurantList = new JSONArray();
		for (Restaurant restaurant : restaurants) {
			JSONObject jsonRestaurant = new JSONObject();
			jsonRestaurant.put("name", restaurant.getName());
			jsonRestaurant.put("image", restaurant.getImage().getPath());
			JSONArray productList = new JSONArray();
			for (int i = 0; i < restaurant.getProducts().size(); i++) {
				JSONObject jsonProduct = new JSONObject();
				jsonProduct.put("nameProduct", restaurant.getProducts().get(i).getName());
				jsonProduct.put("prize", restaurant.getProducts().get(i).getPrize());
				productList.put(jsonProduct);

			}
			jsonRestaurant.put("productList", productList);
			restaurantList.put(jsonRestaurant);
		}
		json.put("restaurantList", restaurantList);
		FileWriter file = new FileWriter(RESTAURANTS_PATH);
		file.write(json.toString(4));
		file.flush();
	}
}
