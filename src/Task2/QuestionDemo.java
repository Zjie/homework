package Task2;

import java.util.Scanner;

public class QuestionDemo {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//2.2
		Question numQuestion = new NumericQuestion(1.41f, "What	is	the	value	of	square	root	of	2?");
		numQuestion.display();
		System.out.println("your answer:");
		numQuestion.checkAnswer(scanner.next());
		//2.3
		Question q2_3 = new Question();
	}
}
