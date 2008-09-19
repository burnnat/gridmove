package gridedit.view.params;

import javax.swing.JTextField;

public class ParamTextField extends JTextField implements ParamComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParamTextField() {
		super();
        setColumns(15);
	}
	
	public ParamTextField(String initialText) {
		this();
		if(initialText != null)
			setText(initialText);
	}
	
	public String getParamValue(){
		return super.getText();
	}
}
