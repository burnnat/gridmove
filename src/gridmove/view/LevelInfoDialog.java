package gridmove.view;

import gridmove.model.Level;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LevelInfoDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Level level;

	public LevelInfoDialog(Level level, JFrame parent) {
		super(parent);
		this.level = level;
		
		if(level.getLevelNumber() != null)
			setTitle("Level " + level.getLevelNumber());
		else
			setTitle("Level");
		
		setModal(true);
		
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(this);

		panel.add(Box.createVerticalGlue());
		addAndCenter(new JLabel("Level Title: " + level.getLevelTitle(), JLabel.CENTER), panel);
		panel.add(Box.createVerticalStrut(10));
		addAndCenter(new JLabel("Level Author: " + level.getLevelAuthor(), JLabel.CENTER), panel);
		panel.add(Box.createVerticalStrut(10));
		addAndCenter(new JLabel("Total Gems: " + level.getTotalGems(), JLabel.CENTER), panel);
		panel.add(Box.createVerticalStrut(10));
		addAndCenter(okButton, panel);
		panel.add(Box.createVerticalGlue());
		
		add(panel);		
		pack();
		setLocation(getParent().getLocation().x + (int)((getParent().getWidth() - getWidth()) / 2),
				getParent().getLocation().y + (int)((getParent().getHeight() - getHeight()) / 2));
	}
	
	private void addAndCenter(JComponent component, Container container) {
		component.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		container.add(component);
	}

	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		dispose();
	}
}
