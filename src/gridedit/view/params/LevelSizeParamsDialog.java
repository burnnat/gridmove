package gridedit.view.params;

import gridmove.model.Level;
import gridmove.model.LevelData;
import gridmove.model.RedBlueSet;
import gridmove.model.TargetSet;
import gridmove.model.tiles.NodeTarget;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LevelSizeParamsDialog extends ParamsDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isComplete = false;
	private ParamPanel[] params = new ParamPanel[] {
			new ParamPanel("Level Height:", new ParamIntegerField()),
			new ParamPanel("Level Width:", new ParamIntegerField()),
			new ParamPanel("Room Height:", new ParamIntegerField()),
			new ParamPanel("Room Width:", new ParamIntegerField())};

	private boolean actionCanceled;

	public LevelSizeParamsDialog(boolean closeable) {
		super("New Level", null);
		if(!closeable)
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		else
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					actionCanceled = true;
					isComplete = true;
				}
			});
		super.addParamPanels(params);
	}

	public void actionPerformed(ActionEvent e) {
		if(((Integer)params[0].getValue()).intValue() < 1 ||
				((Integer)params[1].getValue()).intValue() < 1 ||
				((Integer)params[2].getValue()).intValue() < 1 ||
				((Integer)params[3].getValue()).intValue() < 1) {
			JOptionPane.showMessageDialog(this, "One or more sizes are invalid.", "Error",
				    JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			isComplete = true;
			setVisible(false);
		}
	}

	/**
	 * Generates a new level with user-specified dimensions.
	 * 
	 * @return A new level, or null if canceled.
	 */
	public Level getNewLevel() {
		while(!isComplete){}
		
		if(actionCanceled)
			return null;
		
		return new Level(new LevelData("Untitled", "", "", 0, 0, 0, 0,
				((Integer)params[0].getValue()).intValue(),
				((Integer)params[1].getValue()).intValue(),
				((Integer)params[2].getValue()).intValue(),
				((Integer)params[3].getValue()).intValue(),
				new TargetSet(RedBlueSet.RED_BLUE_SET, new ArrayList<NodeTarget>()),
				null));
	}
}
