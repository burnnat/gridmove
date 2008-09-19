/**
 * 
 */
package gridedit.view;

import gridedit.view.params.TileParamsDialog;
import gridmove.controller.KeyRelay;
import gridmove.model.Model;
import gridmove.model.tiles.CaveWarp;
import gridmove.model.tiles.GemBlock;
import gridmove.model.tiles.Node;
import gridmove.model.tiles.Placard;
import gridmove.model.tiles.RedBlueNode;
import gridmove.model.tiles.ScreenWarp;
import gridmove.model.tiles.Tile;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;

/**
 * @author Nat
 *
 * Version: 0.1
 *
 */
public class Palette extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabMaster;
	Gridedit parent;

	public Palette(Gridedit parentFrame, KeyRelay relay) {		
		super(parentFrame);

		parent = parentFrame;

		addKeyListener(relay);

		tabMaster = new JTabbedPane();
		tabMaster.setFocusable(false);
		tabMaster.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tile t = ((TileButton)e.getSource()).getTile();
				Tile newTile = t;

				if(t instanceof CaveWarp ||
						t instanceof GemBlock ||
						(t instanceof Node && !(t instanceof RedBlueNode)) ||
						t instanceof ScreenWarp ||
						t instanceof Placard) {
					newTile = TileParamsDialog.getParamsFor(t);
				}
				else if(newTile != null) {
					newTile = t.copy();
				}

				if(newTile != null)
					parent.getEditor().placeTile(newTile);
			}
		};

		for (Tile[][] tileGroup : Tile.TILE_GROUPS) {
			JTabbedPane tabs = new JTabbedPane();
			tabs.setFocusable(false);

			for (Tile[] tiles : tileGroup) {
				TileSwatch swatch = new TileSwatch(tiles, al);
				swatch.addKeyListener(relay);

				int i = 0;
				while(tiles[i] == null) {
					i++;
				}
				try {
					tabs.addTab(null, swatch);
				} catch(Exception e) {
					System.out.println(tiles[i].getType());
					e.printStackTrace();
				}
			}

			tabMaster.addTab(null, tabs);

			reloadImages();
		}

//		for (Tile[] tiles : Tile.TILE_GROUPS) {
//		TileSwatch swatch = new TileSwatch(tiles, al);
//		swatch.addKeyListener(relay);

//		int i = 0;
//		while(tiles[i] == null) {
//		i++;
//		}

//		tabMaster.addTab(null, new ImageIcon(skin.getImageFor(tiles[i])), swatch);
//		}

		add(tabMaster);

		pack();
		setSize(getWidth() + 2, getHeight());

//		setAlwaysOnTop(true);
//		setResizable(false);

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}

	public void reloadImages() {
		for (int i = 0; i < tabMaster.getComponentCount(); i++) {
			if(tabMaster.getComponent(i) instanceof JTabbedPane) {
				JTabbedPane tabs = (JTabbedPane)tabMaster.getComponent(i);
				for (int j = 0; j < tabs.getTabCount(); j++) {
					if(!(tabs.getComponent(j) instanceof TileSwatch))
						continue;
					TileSwatch swatch = (TileSwatch)tabs.getComponent(j);
					int k = 0;
					while(swatch.getTileAt(k) == null) {
						k++;
					}

					ImageIcon icon;
					if(Model.getActiveModel().getCurrentSkin().getImageFor(swatch.getTileAt(k)) != null)
						icon = new ImageIcon(Model.getActiveModel().getCurrentSkin().getImageFor(swatch.getTileAt(k)).getScaledInstance(24, 24, Image.SCALE_SMOOTH));
					else
						icon = null;

					tabs.setIconAt(tabs.indexOfComponent(swatch), icon);
					tabs.repaint();

					swatch.reloadImages();
				}
				tabMaster.setIconAt(tabMaster.indexOfComponent(tabs), tabs.getIconAt(0));
			}
		}
		tabMaster.repaint();
	}

	public void selectTab(int tab) {
		if(tab < ((JTabbedPane)tabMaster.getSelectedComponent()).getTabCount())
			((JTabbedPane)tabMaster.getSelectedComponent()).setSelectedIndex(tab);
	}

	public void selectMasterTab(int tab) {
		if(tab < tabMaster.getTabCount())
			tabMaster.setSelectedIndex(tab);
	}

	public void doClick(int i) {
		((TileSwatch)((JTabbedPane)tabMaster.getSelectedComponent()).getSelectedComponent()).doClick(i);
	}
}
