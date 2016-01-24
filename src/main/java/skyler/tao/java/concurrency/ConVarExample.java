package skyler.tao.java.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class ConVarExample {

	Logger logger = Logger.getLogger(ConVarExample.class);
	private int contents;
	private boolean available = false;
	private Lock aLock = new ReentrantLock();
	private Condition condVar = aLock.newCondition();
	
	@SuppressWarnings("finally")
	public int get(int who) {
		aLock.lock();
		try {
			while (available == false) {
				try {
					condVar.await();
				} catch (InterruptedException e) {}
			}
			available = false;
			logger.info("Consumer " + who + " got: " + contents);
			condVar.signalAll();
		} catch (Exception e){
			logger.info(e.getMessage());
		} finally {
			aLock.unlock();
			return contents;
		}
	}
	
	public void put(int who, int value) {
		aLock.lock();
		try {
			while (available == true) {
				try {
					condVar.await();
				} catch (InterruptedException e) {}
			}
			contents = value;
			logger.info("Producer " + who + " put: " + contents);
			condVar.signalAll();
		} finally {
			aLock.unlock();
		}
	}
}
