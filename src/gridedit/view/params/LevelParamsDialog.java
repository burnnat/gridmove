package gridedit.view.params;

import gridmove.model.Level;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelParamsDialog extends ParamsDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Level myLevel;
	private ParamPanel[] params;

	public LevelParamsDialog(Level level) {
		super("Level Settings", null);
		myLevel = level;
		params = new ParamPanel[] {
				new ParamPanel("Level Title:", new ParamTextField(myLevel.getLevelTitle())),
				new ParamPanel("Level Author:", new ParamTextField(myLevel.getLevelAuthor())),
				new ParamPanel("Level Number:", new ParamTextField(myLevel.getLevelNumber())),
				new ParamPanel("Total Gems:", new ParamIntegerField(myLevel.getTotalGems())),
				new ParamPanel("Start Room:", new ParamIntegerField(myLevel.getAvatarStartCoord().room)),
				new ParamPanel("Start Y:", new ParamIntegerField(myLevel.getAvatarStartCoord().x)),
				new ParamPanel("Start X:", new ParamIntegerField(myLevel.getAvatarStartCoord().y))};
		super.addParamPanels(params);
	}

	public void actionPerformed(ActionEvent e) {
		myLevel.setLevelTitle((String)params[0].getValue());
		myLevel.setLevelAuthor((String)params[1].getValue());
		myLevel.setLevelNumber((String)params[2].getValue());
		myLevel.setTotalGems(((Integer)params[3].getValue()).intValue());
		myLevel.setAvatarStartRoom(((Integer)params[4].getValue()).intValue());
		myLevel.setAvatarStartY(((Integer)params[5].getValue()).intValue());
		myLevel.setAvatarStartX(((Integer)params[6].getValue()).intValue());
		setVisible(false);
	}
}

