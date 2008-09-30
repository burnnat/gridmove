package gridedit.view.params;

import gridmove.model.tiles.NodeTarget;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

public class NodeTargetTransferHandler extends TransferHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final DataFlavor NODE_TARGET_FLAVOR = new DataFlavor(NodeTarget[].class, "Node Target Array");

	/**
	 * Perform the actual data import.
	 */
	public boolean importData(TransferHandler.TransferSupport info) {
		NodeTarget[] data = null;

		//if we can't handle the import, bail now.
		if (!canImport(info)) {
			return false;
		}

		JList list = (JList)info.getComponent();
		NodeTargetListModel model = (NodeTargetListModel) list.getModel();
		//fetch the data - bail if it fails
		try {
			data = (NodeTarget[]) info.getTransferable().getTransferData(NODE_TARGET_FLAVOR);
		} catch (UnsupportedFlavorException ufe) {
			ufe.printStackTrace();
			return false;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}

		if (info.isDrop()) {
			//drop in the specified location
			JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
			int index = dl.getIndex();

			if(!dl.isInsert())
				model.removeElement(model.getElementAt(index));

			for (NodeTarget nodeTarget : data) {
				model.addElement(nodeTarget);	
			}
			
			return true;
			
		} else {
			//append to the end of the list
			for (NodeTarget nodeTarget : data) {
				model.addElement(nodeTarget);
			}
			return true;
		}
	}

	/**
	 * Bundle up the data for export.
	 */
	protected Transferable createTransferable(JComponent c) {
		if(!(c instanceof JList))
			return null;
		
		JList list = (JList) c;
		
		Object[] selected = list.getSelectedValues();
		
		NodeTarget[] value = new NodeTarget[selected.length];
		
		for (int i = 0; i < selected.length; i++) {
			value[i] = (NodeTarget) selected[i];
		}
		
		return new NodeTargetTransferable(value);
	}

	/**
	 * The list handles both copy and move actions.
	 */
	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}

	/** 
	 * When the export is complete, remove the old list entry if the
	 * action was a move.
	 */
	protected void exportDone(JComponent c, Transferable data, int action) {
		if (action != MOVE) {
			return;
		}

		JList list = (JList)c;
		DefaultListModel model = (DefaultListModel)list.getModel();
		int index = list.getSelectedIndex();
		model.remove(index);
	}

	/**
	 * We only support importing NodeTargets.
	 */
	public boolean canImport(TransferHandler.TransferSupport support) {
		// we only import NodeTargets
		if (!support.isDataFlavorSupported(NODE_TARGET_FLAVOR)) {
			return false;
		}

		return true;
	}

	private class NodeTargetTransferable implements Transferable {
		private NodeTarget[] myTarget;

		public NodeTargetTransferable(NodeTarget[] target) {
			myTarget = target;
		}

		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
			if(!isDataFlavorSupported(flavor))
				throw new UnsupportedFlavorException(flavor);
			else
				return myTarget;
		}

		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { NODE_TARGET_FLAVOR };
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return flavor.equals(NODE_TARGET_FLAVOR);
		}
	}
}
