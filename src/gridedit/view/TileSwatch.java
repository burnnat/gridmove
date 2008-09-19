package gridedit.view;

import gridmove.model.Model;
import gridmove.model.Skin;
import gridmove.model.tiles.CaveWarp;
import gridmove.model.tiles.Gem;
import gridmove.model.tiles.GemBlock;
import gridmove.model.tiles.MovableBlock;
import gridmove.model.tiles.Node;
import gridmove.model.tiles.NodeType;
import gridmove.model.tiles.ScreenWarp;
import gridmove.model.tiles.Tile;
import gridmove.model.tiles.Wall;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TileSwatch extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public static final Tile[] SHRUBBERY = {
//		new Wall(Wall.SHRUBBERY_TOP_LEFT),
//		new Wall(Wall.SHRUBBERY),
//		new Wall(Wall.SHRUBBERY_TOP_RIGHT),
//		new Wall(Wall.SHRUBBERY),
//		new Wall(Wall.SHRUBBERY),
//		new Wall(Wall.SHRUBBERY),
//		new Wall(Wall.SHRUBBERY_BOTTOM_LEFT),
//		new Wall(Wall.SHRUBBERY),
//		new Wall(Wall.SHRUBBERY_BOTTOM_RIGHT),
//	};
//
//	public static final Tile[] OUTSIDE_BRICKS = {
//		new Wall(Wall.OUTSIDE_BRICK_TOP_LEFT),
//		new Wall(Wall.OUTSIDE_BRICK_EDGE_TOP),
//		new Wall(Wall.OUTSIDE_BRICK_TOP_RIGHT),
//		new Wall(Wall.OUTSIDE_BRICK_EDGE_LEFT),
//		new Wall(Wall.SOLID_BLOCK),
//		new Wall(Wall.OUTSIDE_BRICK_EDGE_RIGHT),
//		new Wall(Wall.OUTSIDE_BRICK_BOTTOM_LEFT),
//		new Wall(Wall.OUTSIDE_BRICK_EDGE_BOTTOM),
//		new Wall(Wall.OUTSIDE_BRICK_BOTTOM_RIGHT),
//	};
//
//	public static final Tile[] INSIDE_BRICKS = {
//		new Wall(Wall.INSIDE_BRICK_TOP_LEFT),
//		new Wall(Wall.INSIDE_BRICK_EDGE_TOP),
//		new Wall(Wall.INSIDE_BRICK_TOP_RIGHT),
//		new Wall(Wall.INSIDE_BRICK_EDGE_LEFT),
//		new Wall(Wall.SOLID_BLOCK),
//		new Wall(Wall.INSIDE_BRICK_EDGE_RIGHT),
//		new Wall(Wall.INSIDE_BRICK_BOTTOM_LEFT),
//		new Wall(Wall.INSIDE_BRICK_EDGE_BOTTOM),
//		new Wall(Wall.INSIDE_BRICK_BOTTOM_RIGHT),
//	};
//	
//	public static final Tile[] DECORATIVE_BLOCKS = {
//		null,
//		new Wall(Wall.VOLCANO_BLOCK),
//		null,
//		new Wall(Wall.DARK_BUSH),
//		new Wall(Wall.DOOR),
//		new Wall(Wall.LIGHT_BUSH),
//		null,
//		new Wall(Wall.CRAB_BLOCK),
//		null,
//	};
//
//	public static final Tile[] FUNCTIONAL_TILES = {
//		new Node(Node.DEFAULT, 0, 0, 0, null),
//		new CaveWarp(0, 0, 0),
//		new Gem(),
//		new ScreenWarp(0),
//		new MovableBlock(),
//		new GemBlock(0),
//		null,
//		null,
//		null,
//	};
//	
//	public static final Tile[] WALLS = {
//		new Wall(Wall.SHRUBBERY),
//		new Wall(Wall.SHRUBBERY_BOTTOM_LEFT),
//		new Wall(Wall.SHRUBBERY_BOTTOM_RIGHT),
//		new Wall(Wall.SHRUBBERY_TOP_LEFT),
//		new Wall(Wall.SHRUBBERY_TOP_RIGHT),
//		new Wall(Wall.CRAB_BLOCK),
//		new Wall(Wall.SOLID_BLOCK),
//		new Wall(Wall.VOLCANO_BLOCK),
//		new Wall(Wall.DOOR),
//		new Wall(Wall.DARK_BUSH),
//		new Wall(Wall.LIGHT_BUSH),
//		new Wall(Wall.OUTSIDE_BRICK_TOP_LEFT),
//		new Wall(Wall.OUTSIDE_BRICK_EDGE_TOP),
//		new Wall(Wall.OUTSIDE_BRICK_TOP_RIGHT),
//		new Wall(Wall.OUTSIDE_BRICK_EDGE_LEFT),
//		new Wall(Wall.OUTSIDE_BRICK_EDGE_RIGHT),
//		new Wall(Wall.OUTSIDE_BRICK_BOTTOM_LEFT),
//		new Wall(Wall.OUTSIDE_BRICK_EDGE_BOTTOM),
//		new Wall(Wall.OUTSIDE_BRICK_BOTTOM_RIGHT),
//		new Wall(Wall.INSIDE_BRICK_TOP_LEFT),
//		new Wall(Wall.INSIDE_BRICK_EDGE_TOP),
//		new Wall(Wall.INSIDE_BRICK_TOP_RIGHT),
//		new Wall(Wall.INSIDE_BRICK_EDGE_LEFT),
//		new Wall(Wall.INSIDE_BRICK_EDGE_RIGHT),
//		new Wall(Wall.INSIDE_BRICK_BOTTOM_LEFT),
//		new Wall(Wall.INSIDE_BRICK_EDGE_BOTTOM),
//		new Wall(Wall.INSIDE_BRICK_BOTTOM_RIGHT),
//	};
	
	public static final Tile[] ALL_TILES = {
		new Wall(Wall.SHRUBBERY),
		new Wall(Wall.SHRUBBERY_CORNER_BOTTOM_LEFT),
		new Wall(Wall.SHRUBBERY_CORNER_BOTTOM_RIGHT),
		new Wall(Wall.SHRUBBERY_CORNER_TOP_LEFT),
		new Wall(Wall.SHRUBBERY_CORNER_TOP_RIGHT),
		new Wall(Wall.CRAB_BLOCK),
		new Wall(Wall.SOLID_BLOCK),
		new Wall(Wall.VOLCANO_BLOCK),
		new Wall(Wall.DOOR),
		new Wall(Wall.DARK_BUSH),
		new Wall(Wall.LIGHT_BUSH),
		new Wall(Wall.OUTSIDE_BRICK_TOP_LEFT),
		new Wall(Wall.OUTSIDE_BRICK_EDGE_TOP),
		new Wall(Wall.OUTSIDE_BRICK_TOP_RIGHT),
		new Wall(Wall.OUTSIDE_BRICK_EDGE_LEFT),
		new Wall(Wall.OUTSIDE_BRICK_EDGE_RIGHT),
		new Wall(Wall.OUTSIDE_BRICK_BOTTOM_LEFT),
		new Wall(Wall.OUTSIDE_BRICK_EDGE_BOTTOM),
		new Wall(Wall.OUTSIDE_BRICK_BOTTOM_RIGHT),
		new Wall(Wall.INSIDE_BRICK_TOP_LEFT),
		new Wall(Wall.INSIDE_BRICK_EDGE_TOP),
		new Wall(Wall.INSIDE_BRICK_TOP_RIGHT),
		new Wall(Wall.INSIDE_BRICK_EDGE_LEFT),
		new Wall(Wall.INSIDE_BRICK_EDGE_RIGHT),
		new Wall(Wall.INSIDE_BRICK_BOTTOM_LEFT),
		new Wall(Wall.INSIDE_BRICK_EDGE_BOTTOM),
		new Wall(Wall.INSIDE_BRICK_BOTTOM_RIGHT),
		new Node(new NodeType(NodeType.STANDARD_NODE, false), null),
		new CaveWarp(0, 0, 0),
		new MovableBlock(),
		new ScreenWarp(0),
		new Gem(),
		new GemBlock(0),
		null,
	};
	
	private TileButton[] buttons;
	
	public TileSwatch(Tile[] tiles, ActionListener al) {
		
		Skin skin = Model.getActiveModel().getCurrentSkin();
		buttons = new TileButton[tiles.length];
		
		setLayout(new GridLayout(0,(int)Math.ceil(Math.sqrt(tiles.length))));
		
		for (int i = 0; i < buttons.length; i++) {
			TileButton button;
			if(skin.getImageFor(tiles[i]) != null)
				button = new TileButton(new ImageIcon(skin.getImageFor(tiles[i]).getScaledInstance(24, 24, Image.SCALE_SMOOTH)), tiles[i]);
			else
				button = new TileButton(null, tiles[i]);
			button.addActionListener(al);
			buttons[i] = button;
			add(button);
		}

//		setMinimumSize(new Dimension(108, 90));
//		setPreferredSize(new Dimension(155, 135));
	}
	
	public Tile getTileAt(int index) {
		return buttons[index].getTile();
	}
	
	public void reloadImages() {
		for (TileButton button : buttons) {
			button.reloadImage();
		}
	}
	
	public void doClick(int i) {
		buttons[i].doClick();
	}
}
