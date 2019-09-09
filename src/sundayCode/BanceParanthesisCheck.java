package sundayCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class BanceParanthesisCheck {
	static String filePath = "C:\\Users\\RamaKrishna\\Desktop\\paranthesisProgram.txt";
	static Stack<Character> paranthesisStack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		String line = br.readLine();
		boolean flag=true;
		for (int i = 0; i < line.length(); i++) {
			char myChar = line.charAt(i);
			if (myChar == '{') {
				paranthesisStack.push(myChar);
			} else if (myChar == '}') {
				char topElement = paranthesisStack.peek();
				if (topElement == '}') {
					paranthesisStack.pop();
				} else {
					flag=false;
					break ;
				}
			}
		}
		if (flag && paranthesisStack.isEmpty()) {
			System.out.println("proper expression");
		} else {
			System.out.println("Not proper expression");
		}
		br.close();
	}
}
