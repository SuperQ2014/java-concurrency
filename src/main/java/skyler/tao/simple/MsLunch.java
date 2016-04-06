package skyler.tao.simple;

public class MsLunch {

	//two instance fields, c1 and c2
	//they are never used together.
	private long c1 = 0;
	private long c2 = 0;
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	/*
	 * simple example about synchronized statement
	 */
	public void inc1() {
		synchronized(lock1) {
			c1++;
		}
	}
	
	public void inc2() {
		synchronized(lock2) {
			c2++;
		}
	}
	
	public long getc1() {
		synchronized(lock1) {
			return c1;
		}
	}
	
	public long getc2() {
		synchronized(lock2) {
			return c2;
		}
	}
}
