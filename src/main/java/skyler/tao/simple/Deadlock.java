package skyler.tao.simple;

public class Deadlock {

	static class Friend {
		private final String name;
		public Friend(String name) {
			this.name = name;
		}
		public String getName() {
			return this.name;
		}
		public synchronized void bow(Friend bower) {
			System.out.format("%s: %s has bowed to me.%n", this.name, bower.getName());
			bower.bowBack(this);
		}
		
		public synchronized void bowBack(Friend bower) {
			System.out.format("%s: %s has bowed bacck to me.%n", this.name, bower.getName());
		}
	}
	
	public static void main(String[] args) {
		final Friend lucy = new Friend("lucy");
		final Friend lili = new Friend("lili");
		
		new Thread(new Runnable() {
			public void run() {
				lucy.bow(lili);
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				lili.bow(lucy);
			}
		}).start();
	}
}
