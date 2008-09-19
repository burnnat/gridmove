package gridedit.view.params;

import gridmove.model.GridCoord;
import gridmove.model.Level;
import gridmove.model.Model;
import gridmove.model.TargetSet;
import gridmove.model.tiles.CaveWarp;
import gridmove.model.tiles.GemBlock;
import gridmove.model.tiles.Node;
import gridmove.model.tiles.NodeFunctor;
import gridmove.model.tiles.NodeType;
import gridmove.model.tiles.Placard;
import gridmove.model.tiles.ScreenWarp;
import gridmove.model.tiles.Tile;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * This dialog window prompts the user for all parameters necessary for the creation of a new tile, depending on the tile type.
 * 
 * @author Nat
 */
public class TileParamsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isComplete = false;
	private Tile mySample;
	private ParamPanel[] params;
	private TargetSet[] updatedSet = null;

	private TileParamsDialog(Tile tile) {
		super();
		setModal(true);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				mySample = null;
				isComplete = true;
			}
		});

		mySample = tile;

		setTitle("New " + mySample.getName());

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel backing = new JPanel();
		backing.setLayout(new BoxLayout(backing, BoxLayout.Y_AXIS));

		JButton ok = new JButton("OK");
		ok.setAlignmentX(Box.CENTER_ALIGNMENT);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(mySample instanceof Node) {
					//check that the node has at least one target set
					if(!((NodeType)params[0].getValue()).getType().equals(NodeType.BLOCK_RECEPTOR) && ((List<?>)params[1].getValue()).size() == 0) {
						JOptionPane.showMessageDialog(TileParamsDialog.this, "Each node must have a target set.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				isComplete = true;
				setVisible(false);
			}
		});

		JLabel instruct = new JLabel("Please enter the following parameters:");
		instruct.setAlignmentX(Box.CENTER_ALIGNMENT);

		backing.add(instruct);

		if(mySample instanceof CaveWarp) {
			params = new ParamPanel[3];
			params[0] = new ParamPanel("Target Room:", new ParamIntegerField());
			params[1] = new ParamPanel("Target Y:", new ParamIntegerField());
			params[2] = new ParamPanel("Target X:", new ParamIntegerField());
		}
		else if(mySample instanceof GemBlock) {
			params = new ParamPanel[1];
			params[0] = new ParamPanel("Number of Gems:", new ParamIntegerField());
		}
		else if(mySample instanceof Node) {
			params = new ParamPanel[2];
			params[0] = new ParamPanel("Node Type:", new ParamNodeTypeSelector());
			params[1] = new ParamPanel("Target Set:", new ParamTargetSetList(Model.getActiveModel().getCurrentLevel().getAllTargetSets(), false, this));
		}
		else if(mySample instanceof ScreenWarp) {
			params = new ParamPanel[1];
			params[0] = new ParamPanel("Target Screen:", new ParamIntegerField());
		}
		else if(mySample instanceof Placard) {
			params = new ParamPanel[2];
			params[0] = new ParamPanel("Title:", new ParamTextField());
			params[1] = new ParamPanel("Message:", new ParamTextArea());
		}

		for (ParamPanel panel : params) {
			backing.add(panel);
		}

		add(backing);
		add(Box.createRigidArea(new Dimension(0,7)));
		add(ok);
		
		getRootPane().setDefaultButton(ok);
		
		((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		pack();
		setResizable(false);
		Dimension scrSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)((scrSize.getWidth() - getWidth()) / 2), (int)((scrSize.getHeight() - getHeight()) / 2));
	}

	public Tile getNewTile() {
		while(!isComplete){}

		Tile replacement;

		if(mySample instanceof CaveWarp) {
			replacement = new CaveWarp(((Integer)params[0].getValue()).intValue(), ((Integer)params[1].getValue()).intValue(), ((Integer)params[2].getValue()).intValue());
		}
		else if(mySample instanceof GemBlock) {
			replacement = new GemBlock(((Integer)params[0].getValue()).intValue());
		}
		else if(mySample instanceof Node) {
			Map<String, NodeFunctor> map;
			
			List<String> targetSets = ((ParamTargetSetList)params[1].getParamComponent()).getParamValue();
			
			if(targetSets.size() > 0) {
				NodeTargetSetFunctionDialog dialog = new NodeTargetSetFunctionDialog(targetSets);
				dialog.setVisible(true);
				map = dialog.getTargetSetMap();
			} else {
				map = new HashMap<String, NodeFunctor>();
			}
			replacement = new Node((NodeType)params[0].getValue(), map);
		}
		else if(mySample instanceof ScreenWarp) {
			replacement = new ScreenWarp(((Integer)params[0].getValue()).intValue());
		}
		else if(mySample instanceof Placard) {
			replacement = new Placard((String)params[0].getValue(), (String)params[1].getValue());
		}
		else { //mySample is probably null - we canceled out
			replacement = null;
		}
		return replacement;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public static Tile getParamsFor(Tile t) {
		Level currentLevel = Model.getActiveModel().getCurrentLevel();

		GridCoord coords = currentLevel.getAvatar().getGridLocation();
		int room = coords.room;
		int gridX = coords.x;
		int gridY = coords.y;

		if(t instanceof ScreenWarp &&
				(gridX != 0 && gridX != currentLevel.ROOM_WIDTH - 1) &&
				(gridY != 0 && gridY != currentLevel.ROOM_HEIGHT - 1))
			return 	currentLevel.getRoomNumber(room).getTileAt(gridX, gridY);
		TileParamsDialog tpd = new TileParamsDialog(t);
		tpd.setVisible(true);
		Tile updatedTile = tpd.getNewTile();

		if(tpd.updatedSet != null)
			currentLevel.setTargetSets(tpd.updatedSet);

		return updatedTile;
	}
}
