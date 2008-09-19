package gridmove.controller;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Nat
 *
 * Version: 0.1
 * 
 * The Animator class handles the animation of any action, such as Avatar motion, or item events. 
 *
 */
public class Animator extends Thread {
	Queue<Action> toDo = new LinkedBlockingQueue<Action>();

	private boolean dying = false;

	public Animator() {
		super("Animator");
	}

	public void run() {
		while(!isDying()) {
			while(!toDo.isEmpty()) {
				try {
					toDo.remove().run();
				} catch(Throwable t) {
					t.printStackTrace();
					Notifier.notifyError(t);
				}
			}

			synchronized(this) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
		}
	}

	/**
	 * Queues an animation for execution
	 * @param a - the Action to be queued
	 */
	public synchronized void queueAction(Action a) {
		toDo.add(a);
		notifyAll();
	}

	/**
	 * Clears all animations from the queue
	 */	
	public synchronized void clearQueue() {
		toDo.clear();
		notifyAll();
	}

	/**
	 * Kills the animation thread - presumably, the game is shutting down.  If not, what did the animator ever do to you?
	 */
	public synchronized void kill() {
		dying = true;
		notifyAll();
	}

	public synchronized boolean isDying() {
		return dying;
	}
}
