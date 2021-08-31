package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationsPanel extends JPanel {
	
	private static final Font MY_FONT_LBL = new Font("Monospaced", Font.BOLD, 20);
	private JLabel header;
	private NotificationListPanel notifications;

	public NotificationsPanel() {
		setLayout(new BorderLayout());
		header = new JLabel(" Notificaciones ");
		header.setFont(MY_FONT_LBL);
		notifications = new NotificationListPanel();
		
		add(header, BorderLayout.NORTH);
		add(notifications, BorderLayout.CENTER);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 5, getHeight());
	}

	public void addNotification(String notification) {
		notifications.addNotification(notification);
	}
}
