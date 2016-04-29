package skyler.tao.concurrency.test;

import java.util.concurrent.TimeUnit;

public class InterruptedExceptionExample {

	class SampleThread extends Thread {
		public SampleThread() {
			super();
			System.out.println("An instance of the " + SampleThread.class + " class was created!");
		}
		public void run() {
			try {
				Thread.sleep(9000);
				TimeUnit.SECONDS.sleep(5000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				//增加try-catch块的目的是验证JVM处理ex.printStackTrace()和sysou打印时的先后顺序，如果不加上，会先打印sysou，然后是StackTrace
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("An InterruptedException was caught!");
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread userThread = new InterruptedExceptionExample().new SampleThread();
		
		userThread.start();
		
		Thread.sleep(3000);
		userThread.interrupt();
		
		userThread.join();
	}
}
