package gridedit.view.params;

import gridmove.model.TargetSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ParamTargetSetList extends JPanel implements ParamComponent, ActionListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean editable;
	private JDialog myParent;
	private TargetSetList list = new TargetSetList(new ListSelectionListener[] { this });
	private JButton add;
	private JButton modify;
	private JButton delete;

	public ParamTargetSetList(TargetSet[] targetSets, boolean isEditable, JDialog parent) {
		super();

		editable = isEditable;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		myParent = parent;

		add(list);

		if(editable) {
			JPanel buttonPanel = new JPanel();

			add = new JButton("Add");
			modify = new JButton("Modify");
			delete = new JButton("Delete");

			modify.setEnabled(false);
			delete.setEnabled(false);

			add.addActionListener(this);
			modify.addActionListener(this);
			delete.addActionListener(this);

			buttonPanel.add(add);
			buttonPanel.add(modify);
			buttonPanel.add(delete);
			
			add(buttonPanel);
		}
	}

	public List<String> getParamValue() {
		Object[] selected = list.getSelectedValues();
		
		List<String> names = new ArrayList<String>();
		
		for (Object targetSet : selected) {
			names.add(((TargetSet)targetSet).getName());
		}
		
		return names;
	}

	public TargetSet[] getAllTargetSets() {
		return list.getAllTargetSets();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == add) {
			TargetSetParamsDialog dialog = new TargetSetParamsDialog();
			dialog.setVisible(true);
			TargetSet ts = dialog.getTargetSet();
			if(ts == null)
				return;
			list.addElement(ts);
		}
		else if(e.getSource() == delete) {
			for (Object o : list.getSelectedValues()) {
				list.removeElement(o);
			}
		}
		else if(e.getSource() == modify) {
			TargetSet initial = (TargetSet)list.getSelectedValue();
			TargetSetParamsDialog dialog = new TargetSetParamsDialog(initial);
			dialog.setVisible(true);

			TargetSet ts = dialog.getTargetSet();
			if(ts == null)
				return;

			list.setElementAt(ts, list.getSelectedIndex());
		}
		myParent.pack();
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false && editable) {
			if (list.getSelectedIndex() == -1) {
				//No selection, disable delete/modify button.
				modify.setEnabled(false);
				delete.setEnabled(false);
			} else {
				modify.setEnabled(true);
				delete.setEnabled(true);
			}
		}
	}
}