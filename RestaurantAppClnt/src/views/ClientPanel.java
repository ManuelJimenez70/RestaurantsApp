package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.Restaurant;

public class ClientPanel extends JPanel {

	private ServicesPanel services;
	private InfoRestaurantsPanel restaurants;

	public ClientPanel(ActionListener actionListener, ArrayList<Restaurant> restaurants) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(View.WINDOW_WIDTH, View.WINDOW_HEIGHT - 70));
		this.setPreferredSize(new Dimension(View.WINDOW_WIDTH, View.WINDOW_HEIGHT - 70));
		this.setLayout(new BorderLayout());

		this.restaurants = new InfoRestaurantsPanel(restaurants, actionListener);
		add(this.restaurants, BorderLayout.CENTER);
		this.services = new ServicesPanel(actionListener);
		add(this.services, BorderLayout.WEST);
	}

	public void setRestaurantList(ArrayList<Restaurant> restaurantList, ActionListener actionListener) {
		this.restaurants.setRestaurantList(restaurantList, actionListener);
	}

	public Restaurant getNewRestaurant() {
		return services.getNewRestaurant();
	}

	public void openRegister() {
		services.openRegister();
	}

	public void openLogin() {
		services.openLogin();
	}

	public String getRestaurantLogin() {
		return services.getRestaurantLogin();
	}
}
