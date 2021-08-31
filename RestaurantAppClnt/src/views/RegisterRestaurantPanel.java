package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Events;
import models.Restaurant;

public class RegisterRestaurantPanel extends JPanel {
	
	private static final String ACEPT_TAG = "Aceptar";
	private JLabel lbName, lbImage;
	private JTextField txName;
	private JFileChooser chooser;
	private JButton btAcept, btCancel, btImage;
	private File image;

	public RegisterRestaurantPanel(ActionListener actionListener) {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(600, 230));
		setBackground(Color.WHITE);
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		lbName = new JLabel("Nombre: ");
		txName = new JTextField(20);
		lbImage = new JLabel("Seleccione una Imagen(.PNG)");
		
		createButtons(actionListener);
		addComponents();
	}

	private void addComponents() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets.set(10, 10, 10, 10);
		add(lbName, c);		
		c.gridx = 2;
		add(txName, c);
		c.gridx = 0;
		c.gridy = 1;
		add(lbImage, c);		
		c.gridx = 2;
		add(btImage, c);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.NONE;
		add(btAcept, c);
	}

	private void createButtons(ActionListener actionListener) {
		btAcept = new JButton(ACEPT_TAG);
		btAcept.setPreferredSize(new Dimension(80, 30));
		btAcept.setBorder(null);
		btAcept.setFocusable(false);
		btAcept.setBackground(Color.RED);
		btAcept.setForeground(Color.WHITE);
		btAcept.addActionListener(actionListener);
		btAcept.setActionCommand(Events.REGISTER_RESTAURANT_ACEPT_EVENT);
		
		btImage = new JButton("Exportar");
		btImage.setPreferredSize(new Dimension(90, 30));
		btImage.setFocusable(false);
		btImage.setBackground(Color.WHITE);
		btImage.addActionListener(actionListener);
		btImage.setActionCommand(Events.EVIDENCY_EVENT);
	}

	public void selectImage() {
		int option = chooser.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			this.image = new File(chooser.getSelectedFile().getPath());
		}
	}

	public Restaurant getNewRestaurant() {
		Restaurant r = new Restaurant(image, this.txName.getText());
		txName.setText("");
		return r;
	}

}
