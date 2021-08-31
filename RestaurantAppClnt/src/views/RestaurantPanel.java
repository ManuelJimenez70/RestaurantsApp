package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import javafx.scene.layout.BorderImage;
import models.Product;
import models.Restaurant;

public class RestaurantPanel extends JPanel{

	private RestaurantInfoPanel restaurantInfo;
	private NotificationsPanel notifications;
	private AddProductPanel adder;
	private String name;
	public RestaurantPanel(ActionListener actionListener) {
		name = "";
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(View.WINDOW_WIDTH, View.WINDOW_HEIGHT - 70));
		this.setPreferredSize(new Dimension(View.WINDOW_WIDTH, View.WINDOW_HEIGHT - 70));
		this.setLayout(new BorderLayout());

		this.notifications = new NotificationsPanel();
		add(notifications, BorderLayout.EAST);
		this.adder = new AddProductPanel(actionListener);
		add(adder, BorderLayout.SOUTH);
	}

	public void setRestaurantInfo(Restaurant rlog) {
		this.name = rlog.getName();
		this.restaurantInfo = new RestaurantInfoPanel(rlog);
		add(restaurantInfo, BorderLayout.CENTER);
		revalidate();
	}

	public Product getNewProduct() {
		return adder.getNewProduct();
	}

	public String getRestaurantName() {
		if (restaurantInfo!=null) {
			return restaurantInfo.getRestaurantName();
		}
		return "";
	}

	public void addNotification(String notification) {
		if (notification.equalsIgnoreCase(name)) {
			notifications.addNotification(notification);
		}
	}

}
