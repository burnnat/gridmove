package gridedit.view.params;

import gridmove.model.GridCoord;
import gridmove.model.Level;
import gridmove.model.TileData;
import gridmove.model.tiles.MovableBlock;
import gridmove.model.tiles.NodeTarget;
import gridmove.model.tiles.Tile;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NodeTargetParamsDialog extends ParamsDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean actionCanceled = false;
	private boolean isComplete = false;
	private ParamPanel[] params;
	
	public NodeTargetParamsDialog() {
		super("New Node Target", "Please enter the following node target settings:");
		
		Tile[] tileSet = new Tile[Tile.ALL_TILES.length + 1];
		
		for (int i = 0; i < Tile.ALL_TILES.length; i++) {
			tileSet[i] = Tile.ALL_TILES[i];
		}
		
		tileSet[tileSet.length - 1] = null;
		
		params = new ParamPanel[] {
				new ParamPanel("Target Room:", new ParamIntegerField()),
				new ParamPanel("Target Y:", new ParamIntegerField()),
				new ParamPanel("Target X:", new ParamIntegerField()),
				new ParamPanel("Replacement Tile:", new ParamTileSelector(tileSet, this))};
		
		super.addParamPanels(params);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				actionCanceled = true;
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
	
	public NodeTarget getNodeTarget() {
		while(!isComplete){}
		
		if(actionCanceled)
			return null;
		
		GridCoord bullseye = new GridCoord(((Integer)params[0].getValue()).intValue(), ((Integer)params[1].getValue()).intValue(), ((Integer)params[2].getValue()).intValue());
		
		Tile t = (Tile)params[3].getValue();
		if(t instanceof MovableBlock)
			((MovableBlock)t).setStartLocation(bullseye.x, bullseye.y);
		
		TileData targetData = null;
		
		try {
			if(t != null)
				targetData = Level.getTileDataFor((Tile)params[3].getValue());
			else
				targetData = null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return new NodeTarget(bullseye, targetData);
	}
}
