package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Events;
import models.Product;

public class AddproductInfoPanel extends JPanel{
	
	private static final String ACEPT_TAG = "Aceptar";
	private static final Font MY_FONT_LBL = new Font("Monospaced", Font.BOLD, 20);

	private JLabel lblName, lblPrize;
	private JTextField txName, txPrize;
	private JButton btnAccept;

	public AddproductInfoPanel(ActionListener actionListener) {
		
		setLayout(new GridBagLayout());
		initComponents(actionListener);

		
	}

	private void initComponents(ActionListener actionListener) {
		createComponents(actionListener);
		addComponents(actionListener);
	}

	private void createComponents(ActionListener actionListener) {
		lblName = new JLabel("Nombre del Producto: ");
		lblName.setFont(MY_FONT_LBL);
		txName = new JTextField(20);
		lblPrize = new JLabel("Precio del Producto: ");
		lblPrize.setFont(MY_FONT_LBL);
		txPrize = new JTextField(20);
		btnAccept = new JButton("Acept");
		btnAccept = new JButton(ACEPT_TAG);
		btnAccept.setFont(MY_FONT_LBL);
		btnAccept.setPreferredSize(new Dimension(80, 30));
		btnAccept.setBorder(null);
		btnAccept.setFocusable(false);
		btnAccept.setBackground(Color.RED);
		btnAccept.setForeground(Color.WHITE);
		btnAccept.addActionListener(actionListener);
		btnAccept.setActionCommand(Events.ADD_PRODUCT);
	}

	private void addComponents(ActionListener actionListener) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets.set(10, 10, 10, 10);
		add(lblName, c);
		c.gridx = 1;
		add(txName, c);
		c.gridx = 0;
		c.gridy = 1;
		add(lblPrize, c);
		c.gridx = 1;
		add(txPrize, c);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		add(btnAccept, c);
	}
	
	public Product getNewProduct() {
		return new Product(this.txName.getText(), "$" + this.txPrize.getText());
	}
	
}
