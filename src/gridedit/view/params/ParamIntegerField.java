package gridedit.view.params;

import java.text.ParseException;

import javax.swing.JFormattedTextField;

public class ParamIntegerField extends JFormattedTextField implements ParamComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParamIntegerField() {
		super();
		setValue(new Integer(0));
		setText("");
        setColumns(3);
	}
	
	public ParamIntegerField(int initialValue) {
		this();
		setValue(new Integer(initialValue));
	}
	
	public Integer getParamValue(){
		try {
			commitEdit();
		} catch (ParseException e) {
			System.err.println("Could not parse string: \'" + getText() + "\'");
		}
		return (Integer)super.getValue();
	}
}
