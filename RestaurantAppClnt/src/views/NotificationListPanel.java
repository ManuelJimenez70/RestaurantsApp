package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class NotificationListPanel extends JPanel{
	
	public NotificationListPanel() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(100, 300));
	}
	
	

	public void addNotification(String notification) {
		add(new NotificationMessage("Nuevo Pedido"));
		revalidate();
		repaint();
	}
}
