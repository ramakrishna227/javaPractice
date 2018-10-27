package exceptionPractice;

public class CustomException extends RuntimeException {
	public CustomException(String message) {
		super(message);
	}
	public static void main(String[] args) {
		throw new CustomException("hello from custom exception");
	}
}
