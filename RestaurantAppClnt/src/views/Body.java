package views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Events;
import models.Product;
import models.Restaurant;

public class Body extends JPanel {

	private ClientPanel client;
	private RestaurantPanel restaurant;
	private CardLayout cardLayout;

	public Body(ActionListener actionListener, ArrayList<Restaurant> restaurants) {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		this.setPreferredSize(new Dimension(View.WINDOW_WIDTH, View.WINDOW_HEIGHT - 70));
		initComponents(actionListener, restaurants);
	}

	private void initComponents(ActionListener actionListener, ArrayList<Restaurant> restaurants) {
		this.client = new ClientPanel(actionListener, restaurants);
		add(client, Events.CLIENT_PANEL);

		this.restaurant = new RestaurantPanel(actionListener);
		add(restaurant, Events.RESTAURANT_PANEL);
	}

	public void setRestaurants(ArrayList<Restaurant> restaurants, ActionListener actionListener) {
		client.setRestaurantList(restaurants, actionListener);
	}

	public Restaurant getNewRestaurant() {
		return client.getNewRestaurant();
	}

	public void openRegister() {
		client.openRegister();
	}

	public void openLogin() {
		client.openLogin();
	}

	public String getRestaurantLogin() {
		return client.getRestaurantLogin();
	}

	public void loginRestaurant(Restaurant rlog) {
		restaurant.setRestaurantInfo(rlog);
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public Product getNewProduct() {
		return restaurant.getNewProduct();
	}

	public String getRestaurantName() {
		return restaurant.getRestaurantName();
	}

	public void addNotification(String restaurant, String notification) {

		this.restaurant.addNotification(notification);

	}
}
