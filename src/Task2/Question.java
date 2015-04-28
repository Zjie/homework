package Task2;

public class Question {
	protected String correctResponse;
	protected String questionText;
	protected String response;
	public void setText(String questionText) {
		this.questionText = questionText;
	}
	public void setAnswer(String correctResponse) {
		this.correctResponse = correctResponse;
	}
	public boolean checkAnswer(String response) {
		if (correctResponse == null || response == null) {
			return false;
		}
		//2.2
		if (correctResponse.equals(response)) {
			return true;
		}
		//2.3
		if (correctResponse.equalsIgnoreCase(response)) {
			return true;
		}
		return false;
	}
	public void display() {
		System.out.println("the question is : " + questionText);
	}
}
