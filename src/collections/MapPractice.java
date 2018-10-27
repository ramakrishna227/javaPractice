package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapPractice {
	Map<String, Object> parentMap = new ConcurrentHashMap();
	Map<String, String> localMap = Collections.synchronizedMap(new HashMap<String, String>());

	public static void main(String[] args) {
		MapPractice clsObj = new MapPractice();
		clsObj.myMethod();
		clsObj.printLocalMapfromParent();
		clsObj.modifyLocalMap();
		clsObj.printLocalMapfromParent();
		deleteValue(clsObj.localMap);
		clsObj.printLocalMapfromParent();
		
		clsObj.modifyLocalMap();
		clsObj.printLocalMapfromParent();
	}

	private static void deleteValue(Map<String, String> map) {
		map.remove("first");
		
	}

	private void printLocalMapfromParent() {
		
		System.out.println("printing...");
		Map<String, String> someMap = (Map<String, String>) parentMap.get("localMap");
		if (!someMap.isEmpty()) {

			for (String value : someMap.values()) {
				System.out.println(value);
			}
		} else {
			System.out.println("Child list empty");
		}
	}

	private void modifyLocalMap() {
		localMap.put("first", "first");
		localMap.put("second", "second");

	}

	private void myMethod() {
		parentMap.put("localMap", localMap);

	}
}
