package gridedit.view.params;

import gridmove.model.GridCoord;
import gridmove.model.Level;

import java.awt.event.ActionEvent;

public class LevelPreviewParamsDialog extends ParamsDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Level myLevel;
	private ParamPanel[] params;
	private GridCoord cursorPostion;

	public LevelPreviewParamsDialog(Level level, GridCoord cursorPosition) {
		super("Preview Settings", null);
		myLevel = level;
		this.cursorPostion = cursorPosition;
		params = new ParamPanel[] {
				new ParamPanel("Start Location:", new ParamRadioGroup(new String[] { "Beginning of level", "Cursor position" })),
				new ParamPanel("Starting Gems:", new ParamIntegerField(0)),
				new ParamPanel("Starting Keys:", new ParamIntegerField(0))};
		super.addParamPanels(params);
	}

	public void actionPerformed(ActionEvent e) {
		if((Integer) params[0].getValue() == 1) {
			myLevel.setCurrentRoomID(cursorPostion.room);
			myLevel.getAvatar().setGridLocation(cursorPostion);
		}
		
		for (int i = 0; i < (Integer) params[1].getValue(); i++) {
			myLevel.collectGem();
		}
		
		for (int i = 0; i < (Integer) params[2].getValue(); i++) {
			myLevel.collectKey();
		}
		
		setVisible(false);
	}
}
