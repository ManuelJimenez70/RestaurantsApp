package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Product;

public class AddProductPanel extends JPanel{
	
	private static final Font MY_FONT_LBL = new Font("Monospaced", Font.BOLD, 30);
	private JLabel headerlbl;
	private AddproductInfoPanel adder;

	public AddProductPanel(ActionListener actionListener) {
		setLayout(new BorderLayout());
		this.headerlbl = new JLabel(" Añadir un producto");
		this.headerlbl.setFont(MY_FONT_LBL);
		this.headerlbl.setHorizontalAlignment(SwingConstants.CENTER);
		this.adder = new AddproductInfoPanel(actionListener);
		
		add(headerlbl, BorderLayout.NORTH);
		add(adder, BorderLayout.CENTER);
	}

	public Product getNewProduct() {
		return adder.getNewProduct();
	}

}
