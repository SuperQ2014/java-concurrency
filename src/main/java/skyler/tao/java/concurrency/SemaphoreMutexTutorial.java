package skyler.tao.java.concurrency;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

/**
 * @anthor tao-chqang@163.com
 * @version 0.0.1
 * @see http://crunchify.com/what-is-java-semaphore-and-mutex-java-concurrency-
 *      multithread-explained-with-example/
 *
 */
public class SemaphoreMutexTutorial {

	static Logger logger = Logger.getLogger(SemaphoreMutexTutorial.class);
	static Object lock = new Object();
	static LinkedList<String> laptop = new LinkedList<String>();

	//Semaphore maintains a set of permits;
	//each acquire blocks if necessary until a permit is available, and then takes it.
	//Each release adds a permit, potentially releasing a blocking acquirer.
	static Semaphore semaphore = new Semaphore(0);
	static Semaphore mutex = new Semaphore(1);

	static class Shopper extends Thread {
		public void run() {
			int counter = 1;
			try {
				while (true) {
					String threadName = Thread.currentThread().getName()
							+ counter++;

					mutex.acquire();
					laptop.add(threadName);
					logger.info("Producer is producing new value: "
							+ threadName);
					mutex.release();

					semaphore.release();
					Thread.sleep(10);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	static class Consumer extends Thread {
		String consumerName;

		public Consumer(String name) {
			this.consumerName = name;
		}

		public void run() {
			try {
				while (true) {
					mutex.acquire();
					String result = "";
					for (String value : laptop) {
						result = value + ",";
					}
					logger.info(consumerName + " consumes value: " + result + "myList.size(): " + laptop.size()
	);
					mutex.release();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		
		new Shopper().start();
		new Consumer("skyler").start();
		new Consumer("tao").start();
		new Consumer("weibo").start();
	}
}
