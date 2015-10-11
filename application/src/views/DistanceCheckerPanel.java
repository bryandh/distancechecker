package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.DistanceCheckerController;

@SuppressWarnings("serial")
public class DistanceCheckerPanel extends JPanel {
	private DistanceCheckerController controller;
	private GroupLayout layout;
	
	public DistanceCheckerPanel() {
		controller = new DistanceCheckerController();
		initLayout();
		initComponents();
	}

	public void initLayout(){
		layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		setLayout(layout);
	}
	
	public void initComponents() {
		// Initialize all the components.
		JLabel originLabel = new JLabel("Origin");
		JLabel destinationLabel = new JLabel("Destination");
		
		JTextField originTextField = new JTextField();
		originTextField.setToolTipText("Enter the origin here");
		originTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, originTextField.getPreferredSize().height));
		JTextField destinationTextField = new JTextField();
		destinationTextField.setToolTipText("Enter the destination here");
		destinationTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, destinationTextField.getPreferredSize().height));
		
		JButton submitButton = new JButton("Submit");
		JLabel outputLabel = new JLabel();
				
		// Add all the components to the layout.
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(originLabel)
						.addComponent(originTextField)
						.addComponent(destinationLabel)
						.addComponent(destinationTextField)
						.addComponent(submitButton))
				.addComponent(outputLabel, GroupLayout.Alignment.LEADING)
				);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(originLabel)
						.addComponent(originTextField)
						.addComponent(destinationLabel)
						.addComponent(destinationTextField)
						.addComponent(submitButton))
				.addComponent(outputLabel)
				);
		
		// Add an ActionListener to the submit button.
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String origin = originTextField.getText();
				String destination = destinationTextField.getText();
				
				if(origin.isEmpty() && destination.isEmpty()){
					outputLabel.setText("Please enter an origin and a destination");
					return;
				}
				if(origin.isEmpty()){
					outputLabel.setText("Please enter an origin");
					return;
				}
				if(destination.isEmpty()){
					outputLabel.setText("Please enter a destination");
					return;
				}
				
				outputLabel.setText(controller.getDistance(origin, destination));
			}
		});

		FocusListener selectAllOnFocus = new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			
			@Override
			public void focusGained(FocusEvent e) {
				((JTextField)e.getSource()).selectAll();
			}
		};
		
		originTextField.addFocusListener(selectAllOnFocus);
		destinationTextField.addFocusListener(selectAllOnFocus);
	
		KeyListener submitOnEnter = new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					submitButton.doClick();
				}
			}
		};
		
		originTextField.addKeyListener(submitOnEnter);
		destinationTextField.addKeyListener(submitOnEnter);
	}
}
