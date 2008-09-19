package gridedit.view.params;

import javax.swing.JComboBox;

public class ParamComboBox extends JComboBox implements ParamComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParamComboBox(Object[] choices) {
		super(choices);
	}

	public Object getParamValue() {
		return super.getSelectedItem();
	}
}
