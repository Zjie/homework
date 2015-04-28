package labsheet1;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private List<Integer> scores;
	public Student(String name) {
		this.name = name;
		scores = new ArrayList<Integer>();
	}
	@SuppressWarnings("unused")
	private Student() {}
	public String getName() {
		return name;
	}
	public void addQuizScore(int score) {
		scores.add(score);
	}
	public int getTotalScore() {
		int sum = 0;
		for (Integer sc : scores) {
			sum += sc;
		}
		return sum;
	}
	public double getAverageScore() {
		return 1.0 * getTotalScore() / scores.size();
	}
	@Override
	public String toString() {
		return "Student name: " + name + "\nTotal score: " + getTotalScore() + "\nAverage score: " + getAverageScore() + "\nNumber of quiz taken: " + scores.size();
	}
	public static void main(String[] args) {
		Student stu = new Student("xuetong");
		stu.addQuizScore(77);
		stu.addQuizScore(97);
		stu.addQuizScore(88);
		System.out.println(stu);
	}
}
