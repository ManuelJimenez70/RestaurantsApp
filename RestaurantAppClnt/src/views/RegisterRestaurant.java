package views;

import java.awt.event.ActionListener;

import models.Events;
import models.Restaurant;

public class RegisterRestaurant extends MyServiceButton{
	
	private RegisterRestaurantFrame register;

	public RegisterRestaurant(String text, ActionListener actionListener) {
		super(text, actionListener);
		setActionCommand(Events.REGISTER_RESTAURANT);
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.register = new RegisterRestaurantFrame(actionListener);
	}

	public Restaurant getNewRestaurant() {
		return register.getNewRestaurant();
	}

	public void openRegister() {
		register.setVisible(true);
	}
}
