package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Events;
import models.Restaurant;

public class LoginRestaurantFrame extends JFrame{
	
	private static final String TITTLE = "RestaurtantsApp";
	private static final String ACEPT_TAG = "Aceptar";
	private static final String AGENCY_FONT_TYPE = "Agency FB";
	private static final String REGISTER_RESTAURANT = "Iniciar Sesion Restaurante";
	private JLabel lbLoginRestaurant, restaurantName;
	private JTextField txName;
	private JPanel loginRestaurantPanel;
	private JButton btAcept;
	
	public LoginRestaurantFrame(ActionListener actionListener) {
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
		this.lbLoginRestaurant = new JLabel(REGISTER_RESTAURANT);
		this.lbLoginRestaurant.setFont(new Font(AGENCY_FONT_TYPE, Font.PLAIN, 40));
		add(lbLoginRestaurant, BorderLayout.NORTH);
		
		
		createLoginPanel(actionListener);
		add(loginRestaurantPanel,BorderLayout.CENTER);		
	}
	
	private void createLoginPanel(ActionListener actionListener) {
		loginRestaurantPanel = new JPanel();
		loginRestaurantPanel.setLayout(new GridBagLayout());
		loginRestaurantPanel.setPreferredSize(new Dimension(600, 230));
		loginRestaurantPanel.setBackground(Color.WHITE);
		
		restaurantName = new JLabel("Nombre de su Restaurante: ");
		txName = new JTextField(20);
		btAcept = new JButton(ACEPT_TAG);
		btAcept.setPreferredSize(new Dimension(80, 30));
		btAcept.setBorder(null);
		btAcept.setFocusable(false);
		btAcept.setBackground(Color.RED);
		btAcept.setForeground(Color.WHITE);
		btAcept.addActionListener(actionListener);
		btAcept.setActionCommand(Events.LOGIN_RESTAURANT_ACEPT);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets.set(10, 10, 10, 10);
		loginRestaurantPanel.add(restaurantName, c);	
		c.gridwidth = 2;
		c.gridx = 1;
		loginRestaurantPanel.add(txName, c);	
		c.gridy = 1;
		c.gridwidth = 1;
		loginRestaurantPanel.add(btAcept, c);	
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, getWidth(), 5);
	}

	public String getRestaurantLogin() {
		dispose();
		return this.txName.getText();
	}

}
