package skyler.tao.filelock;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.apache.log4j.Logger;

public class WatchServiceTest {

	private static Logger logger = Logger.getLogger("WatchServiceTest.class");
	
	public static void main(String[] args) throws Exception {
		WatchService watchService = FileSystems.getDefault().newWatchService();
		Paths.get("target").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
		
		while(true) {
			WatchKey key = watchService.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				logger.info(event.context() + "文件发生了" + event.kind() + "事件！");
			}
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}
}
