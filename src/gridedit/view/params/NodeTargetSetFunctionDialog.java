package gridedit.view.params;

import gridmove.model.tiles.NodeFunctor;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeTargetSetFunctionDialog extends ParamsDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isComplete = false;
	private List<ParamPanel> params = new ArrayList<ParamPanel>();
	private List<String> setNames;

	public NodeTargetSetFunctionDialog(List<String> targetSetNames) {
		super("Node Target Sets", "Please enter a functionality for the following target sets:");

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setNames = targetSetNames;
		
		for (String name : setNames) {
			params.add(new ParamPanel(name + ":", 
					new ParamComboBox(new NodeFunctor[] {
							NodeFunctor.SINGLE_DIRECTION_ON,
							NodeFunctor.SINGLE_DIRECTION_OFF,
							NodeFunctor.TOGGLE_NODE
					})));
		}
		
		super.addParamPanels(params.toArray(new ParamPanel[0]));
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				isComplete = true;
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		isComplete = true;
		setVisible(false);
	}

	public boolean isComplete() {
		return isComplete;
	}

	public Map<String, NodeFunctor> getTargetSetMap() {
		while(!isComplete){}
		
		Map<String, NodeFunctor> map = new HashMap<String, NodeFunctor>(); 
		
		for (int i = 0; i < setNames.size(); i++) {
			map.put(setNames.get(i), (NodeFunctor)params.get(i).getValue());
		}
		
		return map;
	}
}
