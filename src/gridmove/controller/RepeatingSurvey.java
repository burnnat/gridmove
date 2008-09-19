package gridmove.controller;

import gridmove.view.GridFrame;

public class RepeatingSurvey extends Survey {
	//before repeating, the thread will wait for this number of milliseconds
	public static long WAIT_TIME = 100;

	private Animator myAnimator;
	private boolean continuing = true;
	
	public RepeatingSurvey(Direction d, Animator a, GridFrame f) {
		super(d, f);
		myAnimator = a;
	}
	
	public synchronized void halt() {
		continuing = false;
		myAnimator.interrupt();
	}
	
	public synchronized boolean isContinuing() {
		return continuing;
	}
	
	public void run() {
		if(!performAction())
			halt();
		
		if(!isContinuing())
			return;
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			return;
		}
		
		while(isContinuing()) {
			if(!performAction())
				halt();
		}
	}
}
