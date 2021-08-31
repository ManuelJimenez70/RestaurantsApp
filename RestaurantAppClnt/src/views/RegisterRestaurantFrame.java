package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import models.Restaurant;

public class RegisterRestaurantFrame extends JFrame {

	private static final String TITTLE = "RestaurtantsApp";
	private static final String AGENCY_FONT_TYPE = "Agency FB";
	private static final String REGISTER_RESTAURANT = "Registrar Restaurante";
	private JLabel lbRegisterRestaurant;
	private RegisterRestaurantPanel registerRestaurantPanel;


	public RegisterRestaurantFrame(ActionListener actionListener) {
		super();
		this.setTitle(TITTLE);
		this.setSize(new Dimension(600, 230));
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		initComponents(actionListener);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	private void initComponents(ActionListener actionListener) {
		this.lbRegisterRestaurant = new JLabel(REGISTER_RESTAURANT);
		this.lbRegisterRestaurant.setFont(new Font(AGENCY_FONT_TYPE, Font.PLAIN, 40));
		add(lbRegisterRestaurant, BorderLayout.NORTH);
		
		this.registerRestaurantPanel = new RegisterRestaurantPanel(actionListener);
		add(registerRestaurantPanel,BorderLayout.CENTER);
	}
	
	public void selectImage() {
		registerRestaurantPanel.selectImage();
	}

	public Restaurant getNewRestaurant() {
		dispose();
		return registerRestaurantPanel.getNewRestaurant();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, getWidth(), 5);
	}
}
