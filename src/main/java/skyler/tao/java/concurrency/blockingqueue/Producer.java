package skyler.tao.java.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private BlockingQueue<Integer> queue;

	public Producer(BlockingQueue<Integer> queue) {

		this.queue = queue;
	}

	public void run() {

		try {

			for (int i = 0; i < 4; i++) {
				queue.put(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
