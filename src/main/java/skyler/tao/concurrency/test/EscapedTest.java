package skyler.tao.concurrency.test;

public class EscapedTest {

	private String[] stats = new String[] {"a", "b"};
	
	public String[] getStats() {
		return stats;
	}
	
	public static void main(String[] args) {
		EscapedTest myTest = new EscapedTest();
		String[] stats = myTest.getStats();
		
		System.out.println(stats[1]);
		stats[1] = "c";
		String[] stat2 = myTest.getStats();
		System.out.println(stat2[1]);		//c  escaped
	}
}
