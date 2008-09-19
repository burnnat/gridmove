package gridmove.controller;

import gridmove.model.Model;
import gridmove.model.tiles.CaveWarp;
import gridmove.view.Screen;

public class CaveMotion extends Action {
	
		private ScreenChange screenChange;
		private CaveWarp warp;
		
		public CaveMotion(CaveWarp caveWarp, Screen screen) {
			super(Model.getActiveModel().getCurrentLevel().getAvatar());
			warp = caveWarp;
			screenChange = new ScreenChange(warp.getTargetID(), screen);
		}

		public void run() {
			Motion up = new Motion(myTarget, Direction.UP);
			OffMotion away = new OffMotion(Direction.UP, false);
			Motion down = new Motion(myTarget, Direction.DOWN);
			OffMotion back = new OffMotion(Direction.DOWN, true);

			up.run();
			away.run();

			myTarget.setGridLocation(warp.getTargetX(), warp.getTargetY());
			screenChange.run();

			back.run();			
			down.run();
			
			myTarget.setGridLocation(warp.getTargetX(), warp.getTargetY() + 1);
		}
}
