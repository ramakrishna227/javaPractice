package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {
public static void main(String[] args) throws IOException {
	File file=new File("C:\\Users\\RamaKrishna\\Desktop\\hackerrankTemp\\input.txt");
	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	String str=null;
	while((str =br.readLine())!=null) {
String[] split = str.split(" ");
System.out.println(split.length);
	}
	
}
}
