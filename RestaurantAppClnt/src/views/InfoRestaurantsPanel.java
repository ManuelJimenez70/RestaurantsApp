package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.Restaurant;

public class InfoRestaurantsPanel extends JPanel{
	
	
	private static final int CARD_SIZE = 260;
	private ArrayList<RestaurantCard> restaurantList;
	
	public InfoRestaurantsPanel(ArrayList<Restaurant> restaurantList, ActionListener actionListener) {
		this.restaurantList = new ArrayList<>();
		createRestaurantList(restaurantList, actionListener);
		this.setLayout(new FlowLayout());
		setSizePanel();
		setBackground(Color.WHITE);
	}

	private void setSizePanel() {
		double size = this.restaurantList.size();
		double m = Math.ceil(size/3)*CARD_SIZE;
		this.setSize(new Dimension(View.WINDOW_WIDTH-ServicesPanel.WIDTH,(int) m));
	}

	private void createRestaurantList(ArrayList<Restaurant> restaurantList, ActionListener actionListener) {
		for (int i = 0; i < restaurantList.size(); i++) {
			RestaurantCard card = new RestaurantCard(restaurantList.get(i), actionListener);
			this.restaurantList.add(card);
			add(card);
		}
	}

	public void setRestaurantList(ArrayList<Restaurant> restaurantList, ActionListener actionListener) {
		this.restaurantList.clear();
		createRestaurantList(restaurantList, actionListener);
		setSizePanel();
		repaint();
		revalidate();		
	}


}
