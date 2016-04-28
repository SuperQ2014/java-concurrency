package skyler.tao.concurrency.test;

public class MyStopThread extends Thread {

	private volatile Thread stopIndicator;
	
	public void start() {
		stopIndicator = new Thread(this);
		stopIndicator.start();
	}
	
	public void stopThread() {
		stopIndicator = null;
	}
	
	public void run() {
		Thread thisThread = Thread.currentThread();
		while (thisThread == stopIndicator) {
			try {
				System.out.println("Running ...");
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
		}
	}
	
	public static void main(String[] args) throws Exception {
		MyStopThread stopThread = new MyStopThread();
		stopThread.start();
		Thread.sleep(8000);
		stopThread.stopThread();
	}
}
