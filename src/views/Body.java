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

public class Body extends JPanel{
	
	private JLabel bodyText;
	private JButton bodyBtn;

	public Body(ActionListener actionListener) {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(500,400));
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.bodyText = new JLabel("Body");
		this.bodyBtn = new JButton("Body");
		this.bodyBtn.addActionListener(actionListener);
		this.bodyBtn.setFocusable(false);
		addComponents();
	}
	
	private void addComponents() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0; 
		c.gridwidth = 1;
		c.gridheight = 1; 
		c.ipady=10;
		c.ipadx=5;
		c.insets.set(5, 5, 5, 5);
		this.add(this.bodyText,c);
		c.gridx = 1;
		c.gridy = 0; 
		c.gridwidth = 1;
		c.gridheight = 1; 
		c.ipady=10;
		c.ipadx=5;
		this.add(this.bodyBtn,c);
	}

	
}
