package skyler.tao.concurrency.test;

public class BadReaderThread {

	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {
		
		public void run() {
			System.out.println(ready);
			while(!ready) {
				Thread.yield();
			}
			System.out.println(ready);
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(ready);
		new ReaderThread().start();
		Thread.sleep(2000);
		number = 22;
		ready = true;
	}
}
