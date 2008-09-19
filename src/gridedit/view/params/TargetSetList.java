package gridedit.view.params;

import gridmove.model.Model;
import gridmove.model.TargetSet;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

public class TargetSetList extends JList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SortedSet<TargetSet> targets;
	private DefaultListModel listModel = new DefaultListModel();
	
	public TargetSetList(ListSelectionListener[] listeners) {
		targets = new TreeSet<TargetSet>(new TargetSet(null, null));

		TargetSet[] targetSets = Model.getActiveModel().getCurrentLevel().getAllTargetSets();
		
		if(targetSets != null) {
			for (TargetSet target : targetSets) {
				targets.add(target);
				listModel.addElement(target);
			}
		}

		setModel(listModel);
		setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		for (ListSelectionListener lsl : listeners) {
			addListSelectionListener(lsl);
		}
	}
	
	public TargetSet[] getAllTargetSets() {
		return targets.toArray(new TargetSet[0]);
	}
	
	public void addElement(TargetSet ts) {
		targets.add(ts);
		listModel.addElement(ts);
	}
	
	public void removeElement(Object o) {
		targets.remove(o);
		listModel.removeElement(o);
	}

	public void setElementAt(TargetSet ts, int selectedIndex) {
		listModel.setElementAt(ts, selectedIndex);
	}
}
