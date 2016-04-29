package skyler.tao.concurrency.test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) throws IOException {

//		throw new IOException();
		Map testMap = new HashMap(5);
		Collections.unmodifiableMap(testMap);
	}

}
