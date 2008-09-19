package gridedit.view.params;

import gridmove.model.tiles.NodeTarget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ParamTargetTileList extends JPanel implements ParamComponent, ActionListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SortedSet<NodeTarget> targets;
	JDialog myParent;
	JList list = new JList();
	DefaultListModel listModel = new DefaultListModel();
	JButton add = new JButton("Add");
	JButton delete = new JButton("Delete");

	public ParamTargetTileList(List<NodeTarget> nodeTargets, JDialog parent) {
		super();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		myParent = parent;
		
		if(nodeTargets == null)
			nodeTargets = new ArrayList<NodeTarget>();
		
		targets = new TreeSet<NodeTarget>(new NodeTarget(null, null));
		targets.addAll(nodeTargets);

		if(nodeTargets != null) {
			for (NodeTarget target : nodeTargets) {
				listModel.addElement(target);
			}
		}

		list.setModel(listModel);
		list.addListSelectionListener(this);

		JPanel buttonPanel = new JPanel();

		delete.setEnabled(false);

		add.addActionListener(this);
		delete.addActionListener(this);
		buttonPanel.add(add);
		buttonPanel.add(delete);

		add(list);
		add(buttonPanel);
	}

	public List<NodeTarget> getParamValue() {
		return new ArrayList<NodeTarget>(targets);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == add) {
			NodeTargetParamsDialog dialog = new NodeTargetParamsDialog();
			dialog.setVisible(true);
			NodeTarget nt = dialog.getNodeTarget();
			
			if(nt == null)
				return;

			//try to add the target to the set, and to the model if it was successful
			if(targets.add(nt))
				listModel.addElement(nt);
		}
		else if(e.getSource() == delete) {
			for (Object o : list.getSelectedValues()) {
				targets.remove(o);
				listModel.removeElement(o);
			}
		}
		myParent.pack();
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			if (list.getSelectedIndex() == -1) {
				//No selection, disable delete button.
				delete.setEnabled(false);
			} else {
				delete.setEnabled(true);
			}
		}
	}
}