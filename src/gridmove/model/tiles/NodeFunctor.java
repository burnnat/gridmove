package gridmove.model.tiles;

import gridmove.model.ToggleSet;

import java.io.Serializable;

//TODO : Is this a problem?
@SuppressWarnings("serial")
public abstract class NodeFunctor implements Serializable {

	public static NodeFunctor TOGGLE_NODE = new NodeFunctor() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void doNodeAction(ToggleSet ts) {
			ts.toggle();
		}
		
		public String toString() {
			return "Toggle";
		}
	};
	
	public static NodeFunctor SINGLE_DIRECTION_ON = new NodeFunctor() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void doNodeAction(ToggleSet ts) {
			ts.setActivated(true);
		}
		
		public String toString() {
			return "SDN - On";
		}
	};
	
	public static NodeFunctor SINGLE_DIRECTION_OFF = new NodeFunctor() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void doNodeAction(ToggleSet ts) {
			ts.setActivated(false);
		}
		
		public String toString() {
			return "SDN - Off";
		}
	};
	
	public abstract void doNodeAction(ToggleSet set);
	
	public abstract String toString();
}