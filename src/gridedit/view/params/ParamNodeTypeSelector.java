package gridedit.view.params;

import gridmove.model.tiles.NodeType;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ParamNodeTypeSelector extends JPanel implements ParamComponent, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComboBox comboBox = new JComboBox(new String[] {
			NodeType.STANDARD_NODE,
			NodeType.BLOCK_RECEPTOR
	});
	private JCheckBox checkBox = new JCheckBox();
	private JLabel checkBoxLabel = new JLabel("Single Use");
	
	public ParamNodeTypeSelector() {
		super();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		comboBox.addItemListener(this);
		
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.X_AXIS));
		
		checkBoxPanel.add(checkBox);
		checkBoxPanel.add(checkBoxLabel);

		add(comboBox);
		add(checkBoxPanel);
	}

	public void itemStateChanged(ItemEvent e) {
		if(comboBox.getSelectedItem().equals(NodeType.BLOCK_RECEPTOR)) {
			checkBox.setEnabled(false);
			checkBoxLabel.setEnabled(false);
		} else { 
			checkBox.setEnabled(true);
			checkBoxLabel.setEnabled(true);
		}
	}
	
	public NodeType getParamValue() {
		return new NodeType((String)comboBox.getSelectedItem(), checkBox.isSelected());
	}
}
