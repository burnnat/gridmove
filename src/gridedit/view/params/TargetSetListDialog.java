package gridedit.view.params;

import gridedit.view.Gridedit;
import gridmove.model.Level;
import gridmove.model.Model;
import gridmove.model.RedBlueSet;
import gridmove.model.Room;
import gridmove.model.TargetSet;
import gridmove.model.tiles.Node;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

public class TargetSetListDialog extends ParamsDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParamTargetSetList list;
	private Gridedit myParent;

	public TargetSetListDialog(Gridedit parent) {
		super("Target Sets", null);

		myParent = parent;
		
		list = new ParamTargetSetList(Model.getActiveModel().getCurrentLevel().getAllTargetSets(), true, this);

		super.addParamPanels(new ParamPanel[] { new ParamPanel(null, list) });
	}

	public void actionPerformed(ActionEvent e) {

		int outOfDate = 0; 
		Level l = Model.getActiveModel().getCurrentLevel();

		for (int z = 0; z < l.LEVEL_HEIGHT * l.LEVEL_WIDTH; z++) {
			Room r = l.getRoomNumber(z);
			for (int i = 0; i < l.ROOM_HEIGHT; i++) {
				for (int j = 0; j < l.ROOM_WIDTH; j++) {
					if (r.getTileAt(j, i) instanceof Node) {
						Node node = (Node) r.getTileAt(j, i);
						for (String setName : node.getTargetSets()) {
							boolean isValid = false;
							for (TargetSet set : list.getAllTargetSets()) {
								if(setName.equals(set.getName()) || setName.equals(RedBlueSet.RED_BLUE_SET)) {
									isValid = true;
									break;
								}
							}
							if(!isValid)
								outOfDate++;
						}
					}
				}
			}
		}

		if(outOfDate > 0) {
			int response = JOptionPane.showConfirmDialog (null,
					outOfDate + "  nodes currently reference a deleted set.  " +
					"These references will be deleted, and if the node does not\n" +
					" reference any other target sets, it will be deleted as well.  " +
					"Are you sure you want to continue?",
					"Confirm Node Updates",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.CANCEL_OPTION) return;
		}
		
		Model.getActiveModel().getCurrentLevel().setTargetSets(list.getAllTargetSets());
		
		myParent.getScreen().updateScreen();
		myParent.updateLabel();
		
		setVisible(false);
	}
}
