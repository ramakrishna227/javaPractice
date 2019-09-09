package sundayCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KLargestElements {

	public static void DataReadFromFile() throws IOException {
		File f = new File("C:\\Users\\RamaKrishna\\Desktop\\TestData.txt");
		if (f.canRead()) {
			// FileInputStream fis = new FileInputStream(f);
			int num;
			FileReader fr = new FileReader(f); 
			BufferedReader br = new BufferedReader(fr);
			while(br.ready()) {
				String readLine = br.readLine();
				System.out.println(readLine);
printHigestKnum(3, readLine);
			}
			/*while ((num = fr.read()) != -1) {
				System.out.println(num);
			}*/
		}
	}
/**
 * @param k
 * @param str
 */
public static void printHigestKnum(int k,String str) {
	String[] split = str.split(",");
	List<Integer> intList = new ArrayList<Integer>();
	for (String string : split) {
		
	}
	
}
	public static void main(String[] args) {
		try {
			DataReadFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
