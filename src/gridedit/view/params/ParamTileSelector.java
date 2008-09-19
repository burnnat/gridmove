package gridedit.view.params;

import gridedit.view.TileButton;
import gridedit.view.TileSwatch;
import gridmove.model.GridCoord;
import gridmove.model.Model;
import gridmove.model.Skin;
import gridmove.model.tiles.CaveWarp;
import gridmove.model.tiles.GemBlock;
import gridmove.model.tiles.Node;
import gridmove.model.tiles.Placard;
import gridmove.model.tiles.RedBlueNode;
import gridmove.model.tiles.ScreenWarp;
import gridmove.model.tiles.Tile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class ParamTileSelector extends JButton implements ParamComponent, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String BUTTON_TEXT = "Select Tile...";
	
	private boolean isComplete = false;
	private JDialog myParent;
	private Skin epidermis;
	private JDialog myDialog;
	private Tile myValue;
	
	public ParamTileSelector(Tile[] tiles, JDialog parent) {
		super(BUTTON_TEXT);
				
		epidermis = Model.getActiveModel().getCurrentSkin();
		myParent = parent;
				
		myDialog = new JDialog(myParent, BUTTON_TEXT) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void setVisible(boolean isVisible) {
				if(isVisible)
					myDialog.setLocation((int)((myParent.getWidth() - myDialog.getWidth()) / 2) + myParent.getX(),
							(int)((myParent.getHeight() - myDialog.getHeight()) / 2) + myParent.getY());
				super.setVisible(isVisible);
			}
		};
		
		myDialog.setModal(true);
		myDialog.add(new TileSwatch(tiles, this));
		myDialog.pack();
		myDialog.setSize(myDialog.getMinimumSize());
		myDialog.setResizable(false);
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDialog.setVisible(true);
			}
		});
		myDialog.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				myValue = null;
				updateIcon();
				myDialog.setVisible(false);
				isComplete = true;
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		Tile t = ((TileButton)e.getSource()).getTile();
		if(t instanceof CaveWarp ||
				t instanceof GemBlock ||
				(t instanceof Node && !(t instanceof RedBlueNode)) ||
				t instanceof ScreenWarp ||
				t instanceof Placard) {
			//force the params dialog to accept a screenwarp
			if(t instanceof ScreenWarp)
				t.setGridLocation(new GridCoord(0, 0, 0));
			myValue = TileParamsDialog.getParamsFor(t);
		} else {
			myValue = t;
		}
		updateIcon();
		myDialog.setVisible(false);
		isComplete = true;
	}
	
	public boolean isComplete() {
		return isComplete;
	}
	
	private void updateIcon() {
		if(myValue == null) {
			setIcon(null);
			setText(BUTTON_TEXT);
		} else {
			setIcon(new ImageIcon(epidermis.getImageFor(myValue)));
			setText("");
		}
		myParent.pack();
	}

	public Object getParamValue() {
		while(!isComplete && myParent.isVisible()) {}
		return myValue;
	}
}
