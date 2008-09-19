package gridedit.view.params;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ParamPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ParamComponent valueComp;
	
	public ParamPanel(String paramName, ParamComponent pc) {
        valueComp = pc;

        if(paramName != null) {
        	JLabel name = new JLabel(paramName);
        	add(name);
        }
        
        add((JComponent)valueComp);
	}
	
	public Object getValue() {
		return valueComp.getParamValue();
	}
	
	public ParamComponent getParamComponent() {
		return valueComp;
	}
}
