package businessLogic;

import java.util.Random;

public class GenerateValidationCode {

	private int validationNumber;
	private final int min = 1000;
	private final int max = 9999;

	public GenerateValidationCode() {
		Random random = new Random();
		validationNumber = random.nextInt(max - min + 1) + min;
	}

	public int getValidationNumber() {
		return validationNumber;
	}

}
