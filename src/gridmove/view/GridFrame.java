package gridmove.view;

import gridmove.model.Model;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public abstract class GridFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GridFrame(String title) {
		super(title);

		Model.register(this);
		Model.setActiveModel(Model.getModelFor(this));
		
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				Model.setActiveModel(Model.getModelFor(GridFrame.this));
			}
		});
	}

	public abstract Screen getScreen();
	public abstract void centerOnScreen();
	public abstract void showErrorDialog(Exception e);
	public abstract void showFatalErrorDialog(Exception e);
	public abstract void updateGemCount();
	public abstract void updateKeyCount();
}
