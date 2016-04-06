package skyler.tao.simple;

public class SynchronizedCounter {

	private int count;
	
	/*
	 * Synchronizing constructor doesn't make sense, because only the thread that creates an object
	 * should hava access to it while it is being constructed.
	 */
	public SynchronizedCounter(int count) {
		this.count = count;
	}
	public synchronized void increment() {
		count++;
	}
	public synchronized void decrement() {
		count--;
	}
	public synchronized int getCount() {
		return count;
	}
}
