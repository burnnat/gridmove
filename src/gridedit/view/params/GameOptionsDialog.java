package gridedit.view.params;

import gridmove.controller.Motion;
import gridmove.controller.RepeatingSurvey;
import gridmove.model.Model;
import gridmove.view.GridFrame;
import gridmove.view.ItemCounter;

import java.awt.event.ActionEvent;
import java.util.Hashtable;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class GameOptionsDialog extends ParamsDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int MAX_INTERRUPT_LENGTH = 40;
	private static final int MAX_REPEAT_DELAY = 600;
	private static final int MAX_COUNTER_SIZE = 50;
	
	ParamPanel myMotionQuality;
	ParamPanel myMotionDelay;
	ParamPanel myKeyDelay;
	ParamPanel myCounterSize;
	
	GridFrame myFrame;
	
	public GameOptionsDialog(GridFrame gf) {
		super("Game Options", null);
		
		myFrame = gf;
		
		ParamSlider qualitySlider = new ParamSlider(Model.getActiveModel().getCurrentSkin().getTileSize() / 2, Model.getActiveModel().getCurrentSkin().getTileSize() - 1);
		qualitySlider.setValue(Model.getActiveModel().getCurrentSkin().getTileSize() - Motion.PIXEL_SKIP);
		qualitySlider.setMinorTickSpacing(Model.getActiveModel().getCurrentSkin().getTileSize() / 20);
		qualitySlider.setMajorTickSpacing(qualitySlider.getMinorTickSpacing() * 2);
		Hashtable<Integer, JComponent> labelTable = new Hashtable<Integer, JComponent>();
		labelTable.put(new Integer(qualitySlider.getMinimum()), new JLabel("Low"));
		labelTable.put(new Integer(qualitySlider.getMaximum()), new JLabel("High"));
		qualitySlider.setLabelTable(labelTable);
		qualitySlider.setPaintTicks(true);
		qualitySlider.setPaintLabels(true);
		qualitySlider.setSnapToTicks(true);
		
		ParamSlider motionDelaySlider = new ParamSlider(0, MAX_INTERRUPT_LENGTH);
		motionDelaySlider.setValue((int)Motion.WAIT_TIME);
		motionDelaySlider.setMinorTickSpacing(MAX_INTERRUPT_LENGTH / 10);
		motionDelaySlider.setMajorTickSpacing(motionDelaySlider.getMinorTickSpacing() * 2);
		Hashtable<Integer, JComponent> labelTable2 = new Hashtable<Integer, JComponent>();
		labelTable2.put(new Integer(motionDelaySlider.getMinimum()), new JLabel("Short"));
		labelTable2.put(new Integer(motionDelaySlider.getMaximum()), new JLabel("Long"));
		motionDelaySlider.setLabelTable(labelTable2);
		motionDelaySlider.setPaintTicks(true);
		motionDelaySlider.setPaintLabels(true);
		motionDelaySlider.setSnapToTicks(true);
		
		ParamSlider keyDelaySlider = new ParamSlider(0, MAX_REPEAT_DELAY);
		keyDelaySlider.setValue((int)RepeatingSurvey.WAIT_TIME);
		keyDelaySlider.setMajorTickSpacing(150);
		keyDelaySlider.setMinorTickSpacing(25);
		keyDelaySlider.setPaintTicks(true);
		keyDelaySlider.setPaintLabels(true);
		keyDelaySlider.setSnapToTicks(false);
		
		ParamSlider myCounterSlider = new ParamSlider(10, MAX_COUNTER_SIZE);
		myCounterSlider.setValue(ItemCounter.SPRITE_SIZE);
		myCounterSlider.setMajorTickSpacing(20);
		myCounterSlider.setMinorTickSpacing(5);
		myCounterSlider.setPaintTicks(true);
		myCounterSlider.setPaintLabels(true);
		myCounterSlider.setSnapToTicks(false);
		
		myMotionQuality = new ParamPanel("Motion Quality:", qualitySlider);
		myMotionDelay = new ParamPanel("Motion Refresh Delay:", motionDelaySlider);
		myKeyDelay = new ParamPanel("Key Repeat Delay (milliseconds):", keyDelaySlider);
		myCounterSize = new ParamPanel("Item Counter Icon Size:", myCounterSlider);
		
		ParamPanel[] panels = { myMotionQuality, myMotionDelay, myKeyDelay, myCounterSize };
		
		super.addParamPanels(panels);
	}
	
	public void actionPerformed(ActionEvent e) {
		Motion.PIXEL_SKIP = Model.getActiveModel().getCurrentSkin().getTileSize() - ((Integer)myMotionQuality.getValue()).intValue();
		Motion.WAIT_TIME = ((Integer)myMotionDelay.getValue()).longValue();
		RepeatingSurvey.WAIT_TIME = ((Integer)myKeyDelay.getValue()).longValue();
		ItemCounter.SPRITE_SIZE = (Integer)myCounterSize.getValue();
		myFrame.updateKeyCount();
		myFrame.updateGemCount();
		myFrame.pack();
		setVisible(false);
	}
}