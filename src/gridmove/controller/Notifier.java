package gridmove.controller;

import gridmove.view.GridFrame;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;

public class Notifier extends Thread {
	private static Notifier singleton;

	private GridFrame myParent;
	Queue<Runnable> toDo = new LinkedBlockingQueue<Runnable>();

	private Notifier(GridFrame parent) {
		super("Notifier");
		myParent = parent;
	}

	public void run() {
		while(!isInterrupted()) {
			while(!toDo.isEmpty()) {
				toDo.peek().run();
				toDo.remove();
			}

			synchronized(toDo) {
				try {
					toDo.wait();
				} catch (InterruptedException e) {}
			}
		}
	}

	public void showMessage(String title, String message) {
		showNotification(new MessageNotification(title, message));
	}

	public void showError(String title, String message) {
		showNotification(new ErrorNotification(title, message));
	}

	private void showNotification(MessageNotification notification) {
		synchronized(toDo) {
			if(!toDo.contains(notification)) {
				toDo.add(notification);
				toDo.notifyAll();
			}
		}
	}

	public static void configureForFrame(GridFrame frame) {
		if(singleton != null)
			singleton.interrupt();

		singleton = new Notifier(frame);
		singleton.start();
	}

	public static void notifyMessage(String title, String message) {
		if(singleton == null)
			return;

		singleton.showMessage(title, message);
	}

	public static void notifyError(Throwable t) {
		if(singleton == null)
			return;

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		
		singleton.showError("Error", "An error has occured:\n\n" + sw.toString());
	}

	private class MessageNotification implements Runnable {
		private String myTitle;
		private String myMessage;

		public MessageNotification(String title, String message) {
			myTitle = title;
			myMessage = message;
		}

		public String getTitle() {
			return myTitle;
		}

		public String getMessage() {
			return myMessage;
		}

		public void run() {
			JOptionPane.showMessageDialog(myParent, myMessage, myTitle, JOptionPane.PLAIN_MESSAGE);
		}

		public boolean equals(Object o) {
			if(o instanceof MessageNotification) {
				MessageNotification other = (MessageNotification) o;
				if(myTitle.equals(other.getTitle()) && myMessage.equals(other.getMessage()))
					return true;
			}

			return false;
		}
	}

	private class ErrorNotification extends MessageNotification {
		public ErrorNotification(String title, String message) {
			super(title, message);
		}

		public void run() {
			JOptionPane.showMessageDialog(myParent, getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
