package skyler.tao.java.concurrency.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueMain {

	public static void main(String[] args) {

		BlockingQueue<Integer> blockQueue = new ArrayBlockingQueue<Integer>(100);

		Producer producer = new Producer(blockQueue);
		Consumer consumer = new Consumer(blockQueue);

		new Thread(producer).start();
		new Thread(consumer).start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
