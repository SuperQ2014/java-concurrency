package skyler.tao.java.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * 使用{@code Condition}代替{@code Object}的{@link Object#wait()}/{@link Object#notifyAll()}, 
 * 进行producer-consumer之间的状态同步
 * @author chaoqiang
 *
 */
public class ConVarExample {

	Logger logger = Logger.getLogger(ConVarExample.class);
	private int contents;
	private boolean available = false;
	private Lock aLock = new ReentrantLock();
	private Condition condVar = aLock.newCondition();

	@SuppressWarnings("finally")
	public int get(String who) {
		aLock.lock();
		try {
			while (available == false) {
				try {
					condVar.await();
				} catch (InterruptedException e) {
				}
			}
			available = false;
			logger.info("Consumer " + who + " got: " + contents);
			condVar.signalAll();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		} finally {
			aLock.unlock();
			return contents;
		}
	}

	public void put(String who, int value) {
		aLock.lock();
		try {
			while (available == true) {
				try {
					condVar.await();
				} catch (InterruptedException e) {
				}
			}
			contents = value;
			logger.info("Producer " + who + " put: " + contents);
			available = true;
			condVar.signalAll();
		} finally {
			aLock.unlock();
		}
	}

	public static void main(String[] args) {

		final ConVarExample testedObject = new ConVarExample();

		// producer
		new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 0;
				while (true) {
					testedObject.put("skyler tao", i);
					i++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();

		// consumer
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					testedObject.get("skyler tao");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
