package gridedit.controller;

import gridmove.controller.Direction;
import gridmove.controller.KeyRelay;
import gridmove.model.Model;

import java.awt.event.KeyEvent;

public class EditorKeyRelay extends KeyRelay {

	private Editor theMan;

	public EditorKeyRelay(Editor c) {
		super(c);
		theMan = c;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyLocation() == KeyEvent.KEY_LOCATION_NUMPAD) {
			int index = -1;
			switch (e.getKeyCode()) {
			case KeyEvent.VK_NUMPAD7:
				index = 0;
				break;
			case KeyEvent.VK_NUMPAD8:
				index = 1;
				break;
			case KeyEvent.VK_NUMPAD9:
				index = 2;
				break;
			case KeyEvent.VK_NUMPAD4:
				index = 3;
				break;
			case KeyEvent.VK_NUMPAD5:
				index = 4;
				break;
			case KeyEvent.VK_NUMPAD6:
				index = 5;
				break;
			case KeyEvent.VK_NUMPAD1:
				index = 6;
				break;
			case KeyEvent.VK_NUMPAD2:
				index = 7;
				break;
			case KeyEvent.VK_NUMPAD3:
				index = 8;
				break;
			default:
				return;
			}
			theMan.doClick(index);
		}
		else if(e.getKeyCode() >= KeyEvent.VK_1 && e.getKeyCode() <= KeyEvent.VK_9) {
			theMan.selectTab(e.getKeyCode() - KeyEvent.VK_1);
		}
		else if(e.getKeyCode() >= KeyEvent.VK_F1 && e.getKeyCode() <= KeyEvent.VK_F4) {
			theMan.selectMasterTab(e.getKeyCode() - KeyEvent.VK_F1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			theMan.placeTile(null);
		}
		else {

			Direction direction = Direction.NOWHERE;
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				direction = Direction.LEFT;
				break;
			case KeyEvent.VK_A:
				direction = Direction.LEFT;
				break;
			case KeyEvent.VK_UP:
				direction = Direction.UP;
				break;
			case KeyEvent.VK_W:
				direction = Direction.UP;
				break;
			case KeyEvent.VK_COMMA:
				direction = Direction.UP;
				break;
			case KeyEvent.VK_RIGHT:
				direction = Direction.RIGHT;
				break;
			case KeyEvent.VK_D:
				direction = Direction.RIGHT;
				break;
			case KeyEvent.VK_E:
				direction = Direction.RIGHT;
				break;
			case KeyEvent.VK_DOWN:
				direction = Direction.DOWN;
				break;
			case KeyEvent.VK_S:
				direction = Direction.DOWN;
				break;
			case KeyEvent.VK_O:
				direction = Direction.DOWN;
				break;
			}

			if ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) == KeyEvent.CTRL_DOWN_MASK)
				direction = new Direction(direction.dx() * Model.getActiveModel().getCurrentLevel().ROOM_WIDTH,
						direction.dy() * Model.getActiveModel().getCurrentLevel().ROOM_HEIGHT);

			theMan.move(direction);
		}
	}
}
