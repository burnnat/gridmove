package gridmove.controller;

import gridmove.model.Level;
import gridmove.model.Model;
import gridmove.model.tiles.Avatar;
import gridmove.model.tiles.CaveWarp;
import gridmove.model.tiles.Door;
import gridmove.model.tiles.Floor;
import gridmove.model.tiles.Gem;
import gridmove.model.tiles.GemBlock;
import gridmove.model.tiles.Key;
import gridmove.model.tiles.MovableBlock;
import gridmove.model.tiles.Node;
import gridmove.model.tiles.NodeType;
import gridmove.model.tiles.Placard;
import gridmove.model.tiles.ScreenWarp;
import gridmove.model.tiles.Tile;
import gridmove.model.tiles.Wall;
import gridmove.model.tiles.Warp;
import gridmove.view.GridFrame;
import gridmove.view.Screen;

public class Survey extends Action {

	protected Direction direction;
	protected Screen screen;
	protected GridFrame frame;
	
	public Survey(Direction d, GridFrame f) {
		super(Model.getActiveModel().getCurrentLevel().getAvatar());
		direction = d;
		frame = f;
		screen = frame.getScreen();
	}

	public void run() {
		performAction();
	}
	
	/**
	 * Examines the next tile and takes appropriate game actions.
	 * 
	 * @return <code>true</code> if the action is repeatable, <code>false</code> if not.
	 */
	public boolean performAction() {
		Level currentLevel = Model.getActiveModel().getCurrentLevel();
		Avatar avatar = currentLevel.getAvatar();
		
		//we've got to look before we leap
		//(i.e. check to see if there's something in the way)
		int xAhead = avatar.getGridX() + direction.dx();
		int yAhead = avatar.getGridY() + direction.dy();
				
		Tile locus = currentLevel.getCurrentRoom().getTileAt(xAhead, yAhead);
		Tile source = currentLevel.getCurrentRoom().getTileAt(avatar.getGridX(), avatar.getGridY());
		
		if(locus instanceof Wall) {
			return false;
		}
		else if(locus instanceof Gem) {
			currentLevel.collectGem();
			new Motion(avatar, direction).run();
			currentLevel.getCurrentRoom().setTileAt(xAhead, yAhead, null);
			new ScreenUpdate(screen).run();
			frame.updateGemCount();
			return true;
		}
		else if(locus instanceof GemBlock) {
			//check for passability
			boolean becomingPassable = false;
			if(currentLevel.getGemsCollected() >= ((GemBlock)locus).getNumberOfGems())
				becomingPassable = true;
			
			if(((GemBlock)locus).isPassable() || becomingPassable)
				new Motion(avatar, direction).run();

			if(becomingPassable)
				((GemBlock)locus).setPassable(true);
			
			return true;
		}
		else if(locus instanceof CaveWarp) {
			if (checkForEndOfLevel((Warp)locus))
				return false;
			
			if (direction != Direction.UP)
				return false;
			
			//queue the animation
			new CaveMotion((CaveWarp)locus, screen).run();
			
			return true;
		}
		else if(locus instanceof ScreenWarp && //if we're moving onto a warp (even if we're sitting on one, we'll be going to one if we're going to a new screen)
				(xAhead >= currentLevel.ROOM_WIDTH || xAhead < 0 || yAhead >= currentLevel.ROOM_HEIGHT || yAhead < 0)) { //and we'll go off the screen
			
			ScreenWarp warp;
			
			//if there's a collision, go with the source
			if (source instanceof ScreenWarp)
				warp = (ScreenWarp) source;
			else
				warp = (ScreenWarp)locus;
				
			if (checkForEndOfLevel(warp))
				return false;
			
			//don't carryover keystrokes into the next screen.
//			myAnimator.clearQueue();
			
			new ScreenMotion(warp.getTargetID(), direction, screen).run();
			
			return true;
		}
		else if(locus instanceof MovableBlock) {
			if(!((MovableBlock)locus).isMovable())
				return false;
			
			int xAhead2 = xAhead + direction.dx();
			int yAhead2 = yAhead + direction.dy();
			Tile t = currentLevel.getCurrentRoom().getTileAt(xAhead2, yAhead2);
			
			boolean flatTile = false;
			
			if(t == null)
				flatTile = true;
			else if(t instanceof Floor)
				flatTile = true;
			else if(t instanceof GemBlock) {
				//check for passability
				if(currentLevel.getGemsCollected() >= ((GemBlock)t).getNumberOfGems())
					((GemBlock)t).setPassable(true);
				
				flatTile = ((GemBlock)t).isPassable();
			}
			else if(t instanceof Door) {
				if(t.getState().equals(Door.OPEN))
					flatTile = true;
			}
			
			if(flatTile) {
				MovableBlock mb = (MovableBlock)locus;
				currentLevel.getCurrentRoom().setTileAt(xAhead, yAhead, mb.getBaseTile());
				mb.setBaseTile(currentLevel.getCurrentRoom().getTileAt(xAhead2, yAhead2));
				new BlockMotion(mb, false, direction).run();
				currentLevel.getCurrentRoom().setTileAt(xAhead2, yAhead2, locus);
			}
			else if(t.getType().equals(NodeType.BLOCK_RECEPTOR)) {

				MovableBlock mb = (MovableBlock)locus;

				currentLevel.getRoomNumber(locus.getParentRoomID()).setTileAt(xAhead, yAhead, mb.getBaseTile());
				new BlockMotion(mb, true, direction).run();
				currentLevel.getRoomNumber(t.getParentRoomID()).setTileAt(t.getGridX(), t.getGridY(), locus);
				
				((Node)t).doNodeAction();
				
				new ScreenUpdate(screen).run();
			}
			
			return true;
		}
		else if(locus instanceof Node) {
			if(locus.getType().equals(NodeType.BLOCK_RECEPTOR))
				return false;
						
			new Motion(avatar, direction).run();

			Node node = (Node)locus;
			node.doNodeAction();
			
			if(node.isSingleUseNode())
				currentLevel.getRoomNumber(node.getParentRoomID()).setTileAt(node.getGridX(), node.getGridY(), null);

			new ScreenUpdate(screen).run();
			
			return true;
		}
		else if(locus instanceof Key) {
			currentLevel.collectKey();
			new Motion(avatar, direction).run();
			currentLevel.getCurrentRoom().setTileAt(xAhead, yAhead, null);
			new ScreenUpdate(screen).run();
			frame.updateKeyCount();
			
			return true;
		}
		else if(locus instanceof Door) {
			if(((Door)locus).isOpen()) {
				new Motion(avatar, direction).run();
			}
			else if(currentLevel.getKeysCollected() > 0) {
				currentLevel.useKey();
				frame.updateKeyCount();
				((Door)locus).setOpen(true);
				locus.loadFromSkin(Model.getActiveModel().getCurrentSkin());
				new Motion(avatar, direction).run();
			}
			
			return true;
		}
		else if(locus instanceof Placard) {
			Placard placard = (Placard) locus;
			new Notification(placard.getTitle(), placard.getText()).run();
			
			return false;
		}
		else { //there's nothing in our way
			//then proceed to update the model
			//and queue the animation
			new Motion(avatar, direction).run();
			
			return true;
		}
	}
	
	private boolean checkForEndOfLevel(Warp warp) {
		if(warp.getTargetID() == -1 && Model.getActiveModel().getCurrentLevel().getGemsCollected() >= Model.getActiveModel().getCurrentLevel().getTotalGems()) {
			new LevelCompletion(frame, direction).run();
			return true;
		} else {
			return false;
		}
	}
}