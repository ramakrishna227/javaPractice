package filesPractice;

import java.io.File;

public class PrintFilesFromFolder {
public static void main(String[] args) {
	printFileNames("C:\\Users\\rkurisetti\\Desktop\\Work");
	
}

private static void printFileNames(String folder) {
	
	System.out.println("Printing files from folder "+folder);
	File parentFolder =new File(folder);
	File[] listFiles = parentFolder.listFiles();
	
	for(File file:listFiles) {
		if(file.isDirectory()) {
			printFileNames(file.getAbsolutePath());
		}
		System.out.println(file.getName());
	}
}
}
