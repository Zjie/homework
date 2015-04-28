package labsheet1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task3 {
	private static final String NAME = "Name: ";
	private static final String SCORE = "Score: ";
	public static void main(String[] args) {
		System.out.println("please input the file path");
		Scanner input = new Scanner(System.in);
		String fileName = input.nextLine();
		if (fileName == null || fileName.trim().isEmpty()) {
			System.out.println("file path can not be null");
			System.exit(1);
		}
		File f = new File(fileName);
		if (f.isDirectory() || !f.exists() || !f.canRead()) {
			System.out.println("file can not be readed");
			System.exit(1);
		}
		
		List<Student> allStu = new ArrayList<Student>();
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				Student stu = null;
				if (!line.startsWith(NAME))
					continue;
				String name = line.substring(NAME.length(), line.length());
				stu = new Student(name);
				if ((line = br.readLine()) == null) {
					break;
				}
				if (!line.startsWith(SCORE))
					continue;
				String scoreStr = line.substring(SCORE.length(), line.length());
				String[] scoresStr = scoreStr.split(" ");
				boolean flag = false;
				for (String sc : scoresStr) {
					try {
						int s = Integer.parseInt(sc);
						if (s < 0) {
							s = 0;
						}
						stu.addQuizScore(s);
					} catch (Exception e) {
						//e.printStackTrace();
						System.out.println(stu.getName() + " has a error score, see");
						System.out.println(line);
						flag = true;
						break;
					}
				}
				
				if (flag)
					continue;
				else
					 allStu.add(stu);
			}
			br.close();
			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("there are " + allStu.size() + " students.");
		for (Student stu : allStu) {
			System.out.println(stu);
		}
	}
}
