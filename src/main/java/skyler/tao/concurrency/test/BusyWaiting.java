package skyler.tao.concurrency.test;

public class BusyWaiting {

	public static void main(String[] args) throws InterruptedException {
		
		//从CPU的使用率来看，Busy Waiting不是很好的一种策略，除非等待的阈值很小。更佳的方法是，使用sleep或become inactive，知道接收到开始工作的signal
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
