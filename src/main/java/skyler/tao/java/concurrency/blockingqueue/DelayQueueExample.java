package skyler.tao.java.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {

	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<Delayed> queue = new DelayQueue<Delayed>();
		Delayed myElement1 = new DelayedElement();
		
		queue.put(myElement1);
		Delayed newElement = queue.take();
		System.out.println(myElement1.compareTo(newElement));
	}
	
	private static class DelayedElement implements Delayed {

		public int compareTo(Delayed o) {
			// TODO Auto-generated method stub
			return 0;
		}

		public long getDelay(TimeUnit unit) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
