package skyler.tao.concurrency.test;

public class MyDaemonThread extends Thread {

	//设置守护线程，setDaemon方法必须在该守护线程创建前调用，也即在构造方法中调用
	public MyDaemonThread() {
		setDaemon(true);
	}
	
	public void run() {
		while (true) {
//			System.out.println("Running ...");  	//测试不适用try-catch块的情况，要么打印一至多条，要么不打印
			try {
				System.out.println("Running ...");	//如果没有user thread，要么打印一条，要么被main thread终止
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
	
	//增加user thread后，只要user thread没有终止，daemon thread就会一直运行。
	class UserThread implements Runnable {
		
		@Override
		public void run() {
			while (true) {
				try {
					System.out.println("User thread running ...");
					Thread.sleep(3000);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread deamonThread = new MyDaemonThread();
//		new Thread(new MyDaemonThread().new UserThread()).start();
		deamonThread.start();
	}
}
