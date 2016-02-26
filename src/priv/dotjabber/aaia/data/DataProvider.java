package priv.dotjabber.aaia.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataProvider {
	public static List<double[]> read(String fileName) throws FileNotFoundException {
		List<double[]> data = new ArrayList<double[]>();
		
		Scanner scanner = new Scanner(new File(fileName));
		while(scanner.hasNextLine()) {
			String[] lineCells = scanner.nextLine().split(",");
			double[] lineValues = new double[lineCells.length];
			
			for(int i = 0; i < lineCells.length; i++) {
				lineValues[i] = Double.parseDouble(lineCells[i]);
			}
			
			data.add(lineValues);
		}
		
		scanner.close();
		return data;
	}
	
	public static void write(String fileName, List<double[]> data) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		
		for(double[] record : data) {
			String line = Arrays.toString(record).replace(" ", "").replace("[", "").replace("]", "");
			pw.println(line);
		}
		
		pw.close();
	}
}
