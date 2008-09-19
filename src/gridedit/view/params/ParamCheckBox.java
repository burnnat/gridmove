package gridedit.view.params;

import javax.swing.JCheckBox;

public class ParamCheckBox extends JCheckBox implements ParamComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Boolean getParamValue() {
		return new Boolean(isSelected());
	}
}
