package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import models.Events;
import models.Restaurant;

public class ServicesPanel extends JPanel {

	public static final int WIDTH = 240;
	private RegisterRestaurant register;
	private LoginRestaurant login;

	public ServicesPanel(ActionListener actionListener) {
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(WIDTH, View.WINDOW_HEIGHT - 70));
		setBackground(Color.WHITE);
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.register = new RegisterRestaurant("Registrar Restaurante: ", actionListener);
		this.register.setActionCommand(Events.REGISTER_RESTAURANT);
		this.login = new LoginRestaurant("Iniciar Sesion :            ", actionListener);
		this.login.setActionCommand(Events.LOGIN_RESTAURANT);

		add(register);
		add(login);
	}

	public Restaurant getNewRestaurant() {
		return register.getNewRestaurant();
	}

	public void openRegister() {
		register.openRegister();
	}

	public void openLogin() {
		login.openLogin();
	}

	public String getRestaurantLogin() {
		return login.getRestaurantLogin();
	}

}
