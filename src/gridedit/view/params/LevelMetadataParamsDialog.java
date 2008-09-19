package gridedit.view.params;

import gridmove.model.Level;

import java.awt.event.ActionEvent;

public class LevelMetadataParamsDialog extends ParamsDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Level myLevel;
	private ParamPanel[] params;

	public LevelMetadataParamsDialog(Level level) {
		super("Level Settings", null);
		myLevel = level;
		params = new ParamPanel[] {
				new ParamPanel("Level Title:", new ParamTextField(myLevel.getLevelTitle())),
				new ParamPanel("Level Author:", new ParamTextField(myLevel.getLevelAuthor())),
				new ParamPanel("Level Number:", new ParamTextField(myLevel.getLevelNumber()))};
		super.addParamPanels(params);
	}

	public void actionPerformed(ActionEvent e) {
		myLevel.setLevelTitle((String)params[0].getValue());
		myLevel.setLevelAuthor((String)params[1].getValue());
		myLevel.setLevelNumber((String)params[2].getValue());
		setVisible(false);
	}
}
