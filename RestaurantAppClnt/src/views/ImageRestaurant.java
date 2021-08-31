package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageRestaurant extends JPanel{
	
	private static final int INITIAL_X_POS = 10;

	private Image image;
	
	public ImageRestaurant(File image) {
		this.image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(image.getPath())).getImage();
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(154, 100));
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics r) {
		super.paint(r);
		Graphics2D g2 = (Graphics2D) r;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.drawImage(this.image, 20, INITIAL_X_POS, getPreferredSize().width - INITIAL_X_POS,
				getPreferredSize().height - INITIAL_X_POS, this);
	}
}
