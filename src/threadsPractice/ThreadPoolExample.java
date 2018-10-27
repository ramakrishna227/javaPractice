package threadsPractice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ThreadPoolExample {
public static void main(String[] args) {
	String sourcePath="C:/Users/rkurisetti/Desktop/Work";
	File file=new File(sourcePath);
List<File> fileList=new ArrayList<>();
	if(file.isDirectory()) {
	for(File f:file.listFiles()) {
		if(!f.isDirectory()) {
			fileList.add(f);
		}
	}
}
}
}
