package gridedit.view.params;

import java.util.Dictionary;

import javax.swing.JComponent;
import javax.swing.JSlider;

public class ParamSlider extends JSlider implements ParamComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParamSlider(int minimum, int maximum) {
		super();
		setMinimum(minimum);
		setMaximum(maximum);
	}
	
	public ParamSlider(int minimum, int maximum, Dictionary<Integer , JComponent> labelTable) {
		this(minimum, maximum);
		setLabelTable(labelTable);
	}
	
	public ParamSlider() {
		this(0, 100);
	}
	
	public Integer getParamValue(){
		return (Integer)getValue();
	}
}
