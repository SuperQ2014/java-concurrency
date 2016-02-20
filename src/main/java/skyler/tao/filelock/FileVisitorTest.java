package skyler.tao.filelock;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.log4j.Logger;

public class FileVisitorTest {

	private static Logger logger = Logger.getLogger("FileVisitorTest.class");
	public static void main(String[] args) throws Exception {
		Files.walkFileTree(Paths.get("src"), new SimpleFileVisitor<Path>() {
			
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				logger.info("正在访问 " + file + "文件");
				if (file.endsWith("FileVisitorTest.java")) {
					logger.info("已经找到了目标文件！");
					return FileVisitResult.TERMINATE;
				}
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				logger.info("正在访问目录 " + dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
