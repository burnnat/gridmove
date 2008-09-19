package gridmove.controller;

import gridmove.view.Screen;

public class ScreenUpdate extends Action {
	private Screen screen;
	
	public ScreenUpdate(Screen screen) {
		super(null);
		this.screen = screen;
	}
	
	public void run() {
		screen.updateScreen();
	}
}
