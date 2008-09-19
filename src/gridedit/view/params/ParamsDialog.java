package gridedit.view.params;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ParamsDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel backing = new JPanel();
	
	public ParamsDialog(String title, String instructions) {
		super();
		setModal(true);
		setTitle(title);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		backing.setLayout(new BoxLayout(backing, BoxLayout.Y_AXIS));
		
		JButton ok = new JButton("OK");
		ok.setAlignmentX(Box.CENTER_ALIGNMENT);
		ok.addActionListener(this);

		if(instructions != null) {
			JLabel instruct = new JLabel(instructions);
			instruct.setAlignmentX(Box.CENTER_ALIGNMENT);

			backing.add(instruct);
		}

		add(backing);
//		add(Box.createRigidArea(new Dimension(0,7)));
		add(ok);
		
		getRootPane().setDefaultButton(ok);
		
		((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}
	
	public void addParamPanels(ParamPanel[] panels) {
		if(backing.getComponentCount() > 1)
			return;
		for (ParamPanel panel : panels) {
			backing.add(panel);
		}

		pack();
		setResizable(false);
		Dimension scrSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)((scrSize.getWidth() - getWidth()) / 2), (int)((scrSize.getHeight() - getHeight()) / 2));
	}
}
