package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import models.Events;
import models.Product;
import models.Restaurant;
import network.Client;
import views.View;

public class Controller implements ActionListener, IPresenter {

	private FileManager fileManager;
	private Client client;
	private View view;

	public Controller() {
		Timer timer = new Timer();
		ActionListener ac = this;
		fileManager = new FileManager();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				createClient();
				view = new View(ac, fileManager.getRestaurants());
			}
		}, 500);

	}

	public void createClient() {
		try {
			client = new Client(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showAdvertisement(String message) {
		view.addText(message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case Events.REGISTER_RESTAURANT:
			view.openRegister();
			break;
		case Events.LOGIN_RESTAURANT:
			view.openLogin();
			break;
		case Events.ADD_PRODUCT:
			Product newProduct = view.getNewProduct();
			try {
				client.registerProduct(view.getRestaurantName(), newProduct);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case Events.LOGIN_RESTAURANT_ACEPT:
			Restaurant rlog = searchRestaurant(view.getRestaurantLogin());
			view.loginRestaurant(rlog);
			view.getCardLayout().show(view.getBodyPanel(), Events.RESTAURANT_PANEL);
			break;
		case Events.REGISTER_RESTAURANT_ACEPT_EVENT:
			try {
				Restaurant r = view.getNewRestaurant();
				if (r != null) {
					client.registerRestaurant(view.getNewRestaurant());
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
			
		default:
			break;
		}
		for (int i = 0; i < fileManager.getRestaurants().size(); i++) {
			if (fileManager.getRestaurants().get(i).getName().equals(command)) {
				try {
					System.out.println(fileManager.getRestaurants().get(i).getName());
					client.sendOrder(fileManager.getRestaurants().get(i).getName());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private Restaurant searchRestaurant(String restaurantName) {
		for (int i = 0; i < fileManager.getRestaurants().size(); i++) {
			if (fileManager.getRestaurants().get(i).getName().toLowerCase().equals(restaurantName.toLowerCase())) {
				return fileManager.getRestaurants().get(i);
			}
		}
		return null;
	}

	private void setRestaurants() {
		this.view.setRestaurants(fileManager.getRestaurants(), this);
	}

	@Override
	public void getRestaurants(ArrayList<Restaurant> restaurants) {
		fileManager.readRestaurants(restaurants);
	}

	@Override
	public Restaurant createRestaurant(String name, byte[] imageData) throws IOException {
		return fileManager.createRestaurant(name, imageData);
	}

	@Override
	public void addRestaurantToList(Restaurant restaurant) {
		fileManager.registerRestaurant(restaurant);
		setRestaurants();
	}

	@Override
	public void addProductToRestaurant(String restaurantName, Product product) {
		fileManager.registerProductToRestaurant(restaurantName, product);
		setRestaurants();
	}

	@Override
	public void notificatoToRestaurant(String restaurant, String notification) {
		view.addNotification(restaurant,notification);
	}
}
