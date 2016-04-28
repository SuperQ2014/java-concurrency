package skyler.tao.concurrency.test;

public class BusyWaiting {

	public static void main(String[] args) throws InterruptedException {
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				//循环等待，直到某个阈值
				long millisToStop = System.currentTimeMillis() + 10000;
				long currentTimeMillis = System.currentTimeMillis();
				while (millisToStop > currentTimeMillis) {
					System.out.println("Running ...");
					currentTimeMillis = System.currentTimeMillis();
				}
			}
		});
		thread.start();
		Thread.sleep(3000);
	}
}
