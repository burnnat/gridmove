package gridedit.view.params;

import gridmove.model.TargetSet;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class TargetSetParamsDialog extends ParamsDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean actionCanceled = false;
	private boolean isComplete = false;
	private ParamPanel[] params;
	private TargetSet mySet;

	public TargetSetParamsDialog() {
		this(null);
	}

	public TargetSetParamsDialog(TargetSet initial) {
		super("New Node Target Set", "Please enter the following target set settings:");

		mySet = initial;

		if(initial == null)
			params = new ParamPanel[] {
				new ParamPanel("Set Name:", new ParamTextField()),
				new ParamPanel("Node Targets:", new ParamTargetTileList(null, this))};
		else
			params = new ParamPanel[] {
				new ParamPanel("Set Name:", new ParamTextField(initial.getName())),
				new ParamPanel("Node Targets:", new ParamTargetTileList(initial.getTargets(), this))};

		super.addParamPanels(params);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				actionCanceled = true;
				isComplete = true;
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		//check that the node has at least one target set
		if(((ParamTargetTileList)params[1].getParamComponent()).getParamValue().size() == 0) {
			JOptionPane.showMessageDialog(this, "Every target set must have at least one node target.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		isComplete = true;
		setVisible(false);
	}

	public boolean isComplete() {
		return isComplete;
	}

	public TargetSet getTargetSet() {
		while(!isComplete){}

		if(actionCanceled)
			return null;

		
		
		if(mySet == null) {
			mySet = new TargetSet((String)params[0].getValue(), ((ParamTargetTileList)params[1].getParamComponent()).getParamValue());
		} else {
			mySet.setName((String)params[0].getValue());
			mySet.setTargets(((ParamTargetTileList)params[1].getParamComponent()).getParamValue());
		}

		return mySet;
	}
}
