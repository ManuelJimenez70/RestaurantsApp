package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Restaurant;

public class RestaurantCard extends JPanel {
	
	private static final int STROKE = 5;
	private static final int STROKE_SEPARATOR = 8;
	private static final Font MY_FONT_LBL = new Font("Monospaced", Font.BOLD, 20);
	private static final Font MY_FONT_BTN = new Font("Dialog", Font.BOLD, 13);

	private ImageRestaurant image;
	private JLabel nameRts;
	private JButton orderBtn;

	public RestaurantCard(Restaurant restaurant, ActionListener actionListener) {
		super();
		setLayout(new BorderLayout());
		this.setSize(new Dimension(190, 200));
		this.setPreferredSize(new Dimension(190, 200));
		this.setBackground(Color.WHITE);
		initComponents(actionListener, restaurant);
	}

	private void initComponents(ActionListener actionListener, Restaurant restaurant) {
		this.image = new ImageRestaurant(restaurant.getImage());
		this.nameRts = new JLabel(restaurant.getName());
		this.nameRts.setHorizontalAlignment(SwingConstants.CENTER);
		this.nameRts.setFont(MY_FONT_LBL);
		this.orderBtn = new JButton("Pedir");
		this.orderBtn.setBackground(Color.RED);
		this.orderBtn.setForeground(Color.WHITE);
		this.orderBtn.addActionListener(actionListener);
		this.orderBtn.setActionCommand(restaurant.getName());
		this.orderBtn.setFont(MY_FONT_BTN);
		this.orderBtn.setFocusable(false);
		add(image, BorderLayout.NORTH);
		add(nameRts, BorderLayout.CENTER);
		add(orderBtn, BorderLayout.SOUTH);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		getGraphics2D(g2);
			paintBorder(g2);
		

	}

	private void getGraphics2D(Graphics2D g2) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		g2.setRenderingHints(rh);
	}

	private void paintBorder(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillRect(0, 0, getWidth(), STROKE);
		g2.setColor(Color.WHITE);
		g2.fillRect(getWidth()-STROKE_SEPARATOR, 0, STROKE_SEPARATOR, getHeight());
	}
}
