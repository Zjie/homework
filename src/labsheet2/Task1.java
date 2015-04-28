package labsheet2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Stack;

public class Task1 {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("you must type in input_path and output_path");
			System.exit(1);
		}
		String input = args[0];
		String output = args[1];
		//check input and output
		if (input == null || input.trim().isEmpty()) {
			System.out.println("input file path can not be null");
			System.exit(1);
		}
		if (output == null || output.trim().isEmpty()) {
			System.out.println("output file path can not be null");
			System.exit(1);
		}
		File fileInput = new File(input);
		if (fileInput.isDirectory() || !fileInput.exists() || !fileInput.canRead()) {
			System.out.println("input file can not be readed");
			System.exit(1);
		}
		File fileOutput = new File(output);
		if (fileOutput.exists()) {
			System.out.println("output file exietes!");
			System.exit(1);
		}
		
		//input
		Stack<String> lines = new Stack<String>();
		try {
			FileInputStream fis = new FileInputStream(fileInput);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				lines.push(line);
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
		//output
		try {
			FileOutputStream fos = new FileOutputStream(fileOutput);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			while (!lines.empty()) {
				String line = lines.pop();
				bw.write(line + "\n");
			}
			bw.flush();
			bw.close();
			osw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
