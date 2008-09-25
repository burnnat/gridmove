package gridmove.view;

import gridmove.model.Level;
import gridmove.model.Model;
import gridmove.model.Skin;
import gridmove.model.tiles.Tile;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JComponent;

/**
 * @author Nat
 *
 * Version: 0.1
 *
 */
public class Screen extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean repaintable = true;
	private SortedMap<Integer, List<JComponent>> layers = Collections.synchronizedSortedMap(new TreeMap<Integer, List<JComponent>>());

	public Screen() {
		setLayout(new TileLayout());
		setDoubleBuffered(true);
		addContainerListener(new ContainerAdapter() {
			public void componentAdded(ContainerEvent e) {
				if(repaintable)
					repaint();
			}

			public void componentRemoved(ContainerEvent e) {
				synchronized(layers) {
					for (List<JComponent> layer : layers.values()) {
						if(layer.contains(e.getComponent()))
							layer.remove(e.getComponent());
					}
				}
				if(repaintable)
					repaint();
			}
		});
		updateScreen();
	}

	public void add(JComponent tile, Integer layer) {
		add(tile);

		if(layers.get(layer) == null)
			layers.put(layer, Collections.synchronizedList(new ArrayList<JComponent>()));

		layers.get(layer).add(tile);
	}

	protected void paintChildren(Graphics g) {
		synchronized(layers) {
			for (Integer i : layers.keySet()) {
				List<JComponent> components = layers.get(i);
				synchronized(components) {
					for (JComponent jc : components) {
						Rectangle r = jc.getBounds();
						jc.paint(g.create(r.x, r.y, r.width, r.height));
					}
				}
			}
		}
	}

	public void removeAll() {
		super.removeAll();
		layers.clear();
	}

	/**
	 * Clears all tiles off the screen and adds current ones from the current room.
	 * Should be called when moving from room to room.
	 */
	public void updateScreen() {
		Skin currentSkin = Model.getActiveModel().getCurrentSkin();
		Level currentLevel = Model.getActiveModel().getCurrentLevel();
		
		repaintable = false; //set the flag, so we don't get flickering

		removeAll(); //clears away all old tiles

		//add the new tiles
		for (int i = 0; i < currentLevel.ROOM_HEIGHT; i++) {
			for (int j = 0; j < currentLevel.ROOM_WIDTH; j++) {
				Tile t = Model.getActiveModel().getCurrentLevel().getCurrentRoom().getTileAt(j, i);
				if(t != null) {
					addTile(t);
					t.loadFromSkin(currentSkin);
				}
			}
		}

		//and the Avatar
		addTile(currentLevel.getAvatar());
		currentLevel.getAvatar().setImageOffset(0, 0);
		
		revalidate();

		repaintable = true; //reset the flag
		
		Dimension size = new Dimension(currentSkin.getTileSize() * currentLevel.ROOM_WIDTH,
									currentSkin.getTileSize() * currentLevel.ROOM_HEIGHT);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);

		//then redraw all components
		repaint();
	}

	public void addTile(Tile t) {
		add(t, t.getTileLayer());
	}
}
