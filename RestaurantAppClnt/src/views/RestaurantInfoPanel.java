package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Restaurant;

public class RestaurantInfoPanel extends JPanel{
	
	
	private static final Font MY_FONT_LBL = new Font("Monospaced", Font.BOLD, 40);

	private JLabel image, name;
	
	
	public RestaurantInfoPanel(Restaurant restaurant) {
		this.setLayout(new BorderLayout());
		this.image = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(restaurant.getImage().getPath())));
		this.name = new JLabel(restaurant.getName());
		this.image.setHorizontalAlignment(SwingConstants.CENTER);
		this.name.setHorizontalAlignment(SwingConstants.CENTER);
		this.name.setFont(MY_FONT_LBL);
		
		add(name, BorderLayout.NORTH);
		add(image, BorderLayout.CENTER);
	}


	public String getRestaurantName() {
		return name.getName();
	}
	
	

}
