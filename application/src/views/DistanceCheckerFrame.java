package views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DistanceCheckerFrame extends JFrame {
		
	public DistanceCheckerFrame() {
		// Make the application exit on clicking the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 120);
		setTitle("Distance checker");
		centerFrame();
	}

	/**
	 * Centers the frame on the display.
	 */
	public void centerFrame() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
	}
}
