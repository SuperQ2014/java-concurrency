package skyler.tao.java.concurrency;

import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;


public class SemaphoreTutorial {

	Logger logger = Logger.getLogger(SemaphoreTutorial.class);
	private static final int MAX_CONCURRENT_THREADS = 2;
	private final Semaphore adminLock = new Semaphore(MAX_CONCURRENT_THREADS, true); 
	
	public void startTest() {
		for (int i = 0; i < 10; i++) {
			
		}
	}
	
	public class persion extends Thread {
		@Override
		public void run() {
			try {
				adminLock.acquire();
			} catch (InterruptedException e) {
				logger.info("received InterruptedException!");
				return;
			}
			logger.info("Thread " + this.getId() + " start using car -- Acquire()");
			try {
				sleep(1000);
			} catch (Exception e) {
			} finally {
				adminLock.release();
			}
			logger.info("Thread " + this.getId() + " stops using car -- Release()");
		}
	}
	
	public static void main(String[] args) {
		SemaphoreTutorial test = new SemaphoreTutorial();
		test.startTest();
	}
}
