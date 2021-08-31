package views;

import java.awt.event.ActionListener;

import models.Events;
import models.Restaurant;

public class LoginRestaurant extends MyServiceButton{
	
	private LoginRestaurantFrame login;


	public LoginRestaurant(String text, ActionListener actionListener) {
		super(text, actionListener);
		setActionCommand(Events.REGISTER_RESTAURANT_ACEPT_EVENT);
		initComponents(actionListener);

	}

	private void initComponents(ActionListener actionListener) {
		this.login = new LoginRestaurantFrame(actionListener);
	}

	public void openLogin() {
		login.setVisible(true);
	}

	public String getRestaurantLogin() {
		return login.getRestaurantLogin();
	}

}
