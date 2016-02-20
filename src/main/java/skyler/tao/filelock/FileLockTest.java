package skyler.tao.filelock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.apache.log4j.Logger;

public class FileLockTest {

	public static final Logger logger = Logger.getLogger("FileLockTest.class");
	public static void main(String[] args) throws Exception {
		try (
			FileChannel channel = new FileOutputStream("log.out").getChannel())
		{
			FileLock lock = channel.tryLock();
			Thread.sleep(20000);
			lock.release();
			
		} catch (FileNotFoundException e) {
			logger.info("File not found Exception!");
			e.printStackTrace();
		} 
		
	}
}
