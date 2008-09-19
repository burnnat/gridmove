package gridedit.view.params;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ParamRadioGroup extends JPanel implements ParamComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ButtonGroup myGroup;
	List<ButtonModel> buttons;
	
	public ParamRadioGroup(String[] labels) {
		this(labels, 0);
	}
	
	public ParamRadioGroup(String[] labels, int selectedIndex) {
		myGroup = new ButtonGroup();
		buttons = new ArrayList<ButtonModel>();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		for (String label : labels) {
			JRadioButton radioButton = new JRadioButton(label);
			buttons.add(radioButton.getModel());
			myGroup.add(radioButton);
			add(radioButton);
		}
		
		buttons.get(selectedIndex).setSelected(true);
	}
	
	public Integer getParamValue() {
		return buttons.indexOf(myGroup.getSelection());
	}
}
