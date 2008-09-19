package gridedit.view.params;

import gridedit.controller.Editor;
import gridmove.model.Skin;

import java.awt.event.ActionEvent;
import java.util.Map;

public class SkinParamsDialog extends ParamsDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Skin mySkin;
	private Editor myEditor;
	private ParamPanel[] params;
	
	public SkinParamsDialog(Skin skin, Editor editor) {
		super("Edit Skin", null);
		
		mySkin = skin;
		myEditor = editor;
		
		params = new ParamPanel[] {
				new ParamPanel("Skin Name:", new ParamTextField(skin.getName())),
				new ParamPanel("Tile Size:", new ParamIntegerField(skin.getTileSize())),
				new ParamPanel(null, new ParamSkinMapper(skin))};
		
		super.addParamPanels(params);
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {		
		Map<String, String> map = (Map<String, String>)params[2].getValue();
		mySkin = new Skin((String)params[0].getValue(), map);
		mySkin.setTileSize(((Integer)params[1].getValue()).intValue());
		myEditor.setCurrentSkin(mySkin);
		setVisible(false);
	}
}
