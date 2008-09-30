package gridedit.view.params;

import gridmove.model.tiles.NodeTarget;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.TransferHandler;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ParamTargetTileList extends JPanel implements ParamComponent, ActionListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JDialog myParent;
	JList list = new JList();
	NodeTargetListModel listModel = new NodeTargetListModel();
	JButton add = new JButton("Add");
	JButton delete = new JButton("Delete");

	public ParamTargetTileList(List<NodeTarget> nodeTargets, JDialog parent) {
		super();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		myParent = parent;

		if(nodeTargets == null)
			nodeTargets = new ArrayList<NodeTarget>();

		listModel.addAll(nodeTargets);
		listModel.addListDataListener(new ListDataListener() {
			public void contentsChanged(ListDataEvent e) {
				myParent.pack();
			}

			public void intervalAdded(ListDataEvent e) {
				myParent.pack();
			}

			public void intervalRemoved(ListDataEvent e) {
				myParent.pack();				
			}
		});

		list.setModel(listModel);
		list.addListSelectionListener(this);
		list.addMouseListener(new PopupListener());
		list.setTransferHandler(new NodeTargetTransferHandler());
		list.setDropMode(DropMode.INSERT);
		list.setDragEnabled(true);

		ActionMap map = list.getActionMap();
		map.put(TransferHandler.getCopyAction().getValue(Action.NAME),
				TransferHandler.getCopyAction());
		map.put(TransferHandler.getPasteAction().getValue(Action.NAME),
				TransferHandler.getPasteAction());

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
		return listModel.getAllElements();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == add) {
			NodeTargetParamsDialog dialog = new NodeTargetParamsDialog();
			dialog.setVisible(true);
			NodeTarget nt = dialog.getNodeTarget();

			if(nt == null)
				return;
			
			listModel.addElement(nt);
		}
		else if(e.getSource() == delete) {
			for (Object o : list.getSelectedValues()) {
				listModel.removeElement(o);
			}
		}
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

	class PopupListener extends MouseAdapter {
		private JPopupMenu popup;
		private JMenuItem copyItem;
		private JMenuItem pasteItem;

		public PopupListener() {
			popup = new JPopupMenu();

			copyItem = new JMenuItem("Copy");
			copyItem.setActionCommand((String)TransferHandler.getCopyAction().getValue(Action.NAME));
			copyItem.addActionListener(new TransferActionListener());
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					copyItem.setEnabled(list.getSelectedIndices().length > 0);
				}
			});

			pasteItem = new JMenuItem("Paste");
			pasteItem.setActionCommand((String)TransferHandler.getPasteAction().getValue(Action.NAME));
			pasteItem.addActionListener(new TransferActionListener());
			
			FlavorListener flavorListener = new FlavorListener() {
				public void flavorsChanged(FlavorEvent e) {
					boolean isAvailable = ((Clipboard) e.getSource()).isDataFlavorAvailable(NodeTargetTransferHandler.NODE_TARGET_FLAVOR);
					pasteItem.setEnabled(isAvailable);
				}
			};
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.addFlavorListener(flavorListener);


			popup.add(copyItem);
			popup.add(pasteItem);
		}

		public void mousePressed(MouseEvent e) {
			considerPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			considerPopup(e);
		}

		private void considerPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				list.requestFocusInWindow();
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
}