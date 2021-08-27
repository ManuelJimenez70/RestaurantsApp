package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel{
	
	private static final int STROKE = 3;
	private static final URL HEADER_ICON = View.class.getResource("/resources/images/headerIcon.png");
	private JLabel txtheader;

	public Header(ActionListener actionListener) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(View.WINDOW_WIDTH,70));
		initComponents(actionListener);
	}

	private void initComponents(ActionListener actionListener) {
		this.txtheader = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(HEADER_ICON)));
		this.add(txtheader, BorderLayout.WEST);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, getHeight() - STROKE, getWidth(), STROKE);
	}

}
