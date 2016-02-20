package skyler.tao.filelock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class FilesTest {

	public static final Logger logger = Logger.getLogger("FilesTest.class");
	public static void main(String[] args) {
		try {
			Files.copy(Paths.get("pom.xml"), new FileOutputStream("a.txt"));
		} catch (FileNotFoundException e) {
			logger.info("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			logger.info("FilesTest.java 是否为隐藏文件：" + Files.isHidden(Paths.get("pom.xml")));
		} catch (IOException e) {
			logger.info("IOException occurs!");
			e.printStackTrace();
		}
	}
}
