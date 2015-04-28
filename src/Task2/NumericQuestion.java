package Task2;

public class NumericQuestion extends Question {
	private float correctAnswer;
	public NumericQuestion(float ca, String questionText) {
		correctAnswer = ca;
		this.questionText = questionText;
	}
	@Override
	public boolean checkAnswer(String response) {
		if (response == null || response.isEmpty()) {
			System.out.println("false");
			return false;
		}
		float res = Float.parseFloat(response);
		if (Math.abs(res - correctAnswer) < 0.01) {
			System.out.println("true");
			return true;
		}
		System.out.println("false");
		return false;
	}
	
	@Override
	public void display() {
		super.display();
	}
}
