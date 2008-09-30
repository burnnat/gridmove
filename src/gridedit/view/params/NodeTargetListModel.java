package gridedit.view.params;

import gridmove.model.tiles.NodeTarget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class NodeTargetListModel implements ListModel {

	List<ListDataListener> listeners;
	SortedSet<NodeTarget> targets;

	public NodeTargetListModel() {
		targets = new TreeSet<NodeTarget>(new NodeTarget(null, null));
		listeners = new ArrayList<ListDataListener>();
	}
	
	public void addAll(Collection<? extends NodeTarget> c) {
		targets.addAll(c);
		notifyChange();
	}
	
	public void addElement(NodeTarget target) {
		targets.add(target);
		notifyChange();
	}

	public void removeElement(Object o) {
		targets.remove(o);
		notifyChange();
	}
	
	public boolean contains(Object o) {
		return targets.contains(o);
	}
	
	public List<NodeTarget> getAllElements() {
		return new ArrayList<NodeTarget>(targets);
	}

	public Object getElementAt(int index) {
		if(index < 0 || index >= targets.size())
			return null;

		Iterator<NodeTarget> iterator = targets.iterator();		

		for (int i = 0; i < index; i++) {
			iterator.next();
		}

		return iterator.next();
	}

	public int getSize() {
		return targets.size();
	}
	
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}
	
	private void notifyChange() {
		//because it's sorted, we can't guarantee that anything will remain the same
		//so just do a generic "contents changed" even over the whole thing
		for (ListDataListener listener : listeners) {
			listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, targets.size() - 1));
		}
	}
}
