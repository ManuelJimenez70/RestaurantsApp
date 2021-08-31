package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;

import models.Product;
import models.Restaurant;

public class View extends JFrame{

	public static final int WINDOW_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 1000;
	private static final String TITTLE = "Titulo";
	private static final String ICON_IMAGE = "/resources/images/titleIcon.png";
	private static final Font MY_FONT_LBL = new Font("SansSerif", Font.PLAIN, 17);
	private static final Font MY_FONT_BTN = new Font("SansSerif", Font.PLAIN, 13);
	private static final String LABEL_FONT_PROP = "Label.font";
	private Header header;
	private Body body;
	
	
	public View(ActionListener actionListener, ArrayList<Restaurant> restaurants) {
		super();
		UIManager.put(LABEL_FONT_PROP, MY_FONT_LBL);
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.foreground", Color.RED);
		UIManager.put("Button.font", MY_FONT_BTN);
		UIManager.put("Panel.background", Color.WHITE);
		this.setTitle(TITTLE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource(ICON_IMAGE)));
		this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		initComponents(actionListener, restaurants);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents(ActionListener actionListener, ArrayList<Restaurant> restaurants) {
		this.header = new Header(actionListener);
		this.body = new Body(actionListener, restaurants);

		this.add(this.header,BorderLayout.NORTH);
		this.add(this.body,BorderLayout.CENTER);
	}

	public void addText(String message) {

		System.out.println(message);
	}

	public void setRestaurants(ArrayList<Restaurant> restaurants, ActionListener actionListener) {
		body.setRestaurants(restaurants, actionListener);
	}

	public Restaurant getNewRestaurant() {
		return body.getNewRestaurant();
	}

	public String getRestaurantName() {
		return body.getRestaurantName();
	}

	public Product getNewProduct() {
		return body.getNewProduct();
	}

	public void openRegister() {
		body.openRegister();
	}

	public void openLogin() {
		body.openLogin();

	}

	public String getRestaurantLogin() {
		return body.getRestaurantLogin();
	}

	public void loginRestaurant(Restaurant rlog) {
		body.loginRestaurant(rlog);
	}

	public CardLayout getCardLayout() {
		return body.getCardLayout();
	}

	public Body getBodyPanel() {
		return body;
	}

	public void addNotification(String restaurant, String notification) {
		body.addNotification(restaurant,notification);
	}
	
}
