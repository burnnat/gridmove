package gridmove.controller;


public class Notification extends Action {
	
	private String myTitle;
	private String myText;
	
	public Notification(String title, String message) {
		super(null);
		myTitle = title;
		myText = message;
	}

	public void run() {
		Notifier.notifyMessage(myTitle, myText);
	}
}
