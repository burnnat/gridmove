package gridedit.view.params;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ParamTextArea extends JPanel implements ParamComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextArea myTextArea;
	
	public ParamTextArea() {
		super();
		myTextArea = new JTextArea(5, 25);
		initialize();
	}
	
	public ParamTextArea(int rows, int columns) {
		super();
		myTextArea = new JTextArea(rows, columns);
		initialize();
	}
	
	private void initialize() {
        myTextArea.setLineWrap(true);
        myTextArea.setWrapStyleWord(true);
        
		JScrollPane scrollPane = new JScrollPane(myTextArea);
		add(scrollPane);
	}
	
	public String getParamValue(){
		return myTextArea.getText();
	}
}
