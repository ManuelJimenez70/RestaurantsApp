package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationMessage extends JPanel {

	private JLabel clientName;
	private boolean isDisappear;

	public NotificationMessage(String clientName) {
		this.clientName = new JLabel(clientName);
		setPreferredSize(new Dimension(100, 50));
		setBackground(Color.WHITE);
		add(this.clientName);
		showNotification();
	}

	public void showNotification() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				int time = 0;
				int fade = 200;
				while (!isDisappear) {
					try {
						if (fade>=0) {
							clientName.setForeground(new Color(0,0,0,fade));
							Thread.sleep(150);
							fade-=5;
							time++;
							if (time == 50) {
								isDisappear = true;
							}
						}
	
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
				}
			}
		}).start();
	}
	
	public boolean isDisappear() {
		return isDisappear;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(new Dimension(100, 80));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		NotificationMessage nm = new NotificationMessage("Manuel Jimenez");
		f.add(nm);
		f.setVisible(true);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (f.isVisible()) {
					if (nm.isDisappear) {
						f.remove(nm);
						f.revalidate();
					}
				}
			}
		}).start();
	}

}
