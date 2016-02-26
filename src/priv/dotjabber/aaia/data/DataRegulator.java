package priv.dotjabber.aaia.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.math3.util.FastMath;

public class DataRegulator {
	public static Map<Integer, double[]> metaData;
	static {
		
		// meta-data, this is given, it is recoded though (by hand).
		metaData = new HashMap<>();
		metaData.put(146,  new double[] {2.0, 1, 0, 0});
		metaData.put(149,  new double[] {2.2, 0, 1, 0});
		metaData.put(155,  new double[] {2.7, 0, 1, 0});
		metaData.put(171,  new double[] {2.0, 1, 0, 0});
		metaData.put(264,  new double[] {3.5, 0, 1, 0});
		metaData.put(373,  new double[] {1.6, 0, 1, 0});
		metaData.put(437,  new double[] {3.0, 0, 1, 0});
		metaData.put(470,  new double[] {3.8, 0, 0, 1});
		metaData.put(479,  new double[] {4.0, 1, 0, 0});
		metaData.put(490,  new double[] {1.9, 1, 0, 0});
		metaData.put(508,  new double[] {2.8, 1, 0, 0});
		metaData.put(541,  new double[] {4.4, 0, 1, 0});
		metaData.put(575,  new double[] {3.0, 0, 1, 0});
		metaData.put(583,  new double[] {4.0, 0, 1, 0});
		metaData.put(599,  new double[] {3.0, 1, 0, 0});
		metaData.put(607,  new double[] {3.8, 0, 1, 0});
		metaData.put(641,  new double[] {3.8, 1, 0, 0});
		metaData.put(689,  new double[] {3.0, 0, 1, 0});
		metaData.put(703,  new double[] {3.0, 1, 0, 0});
		metaData.put(725,  new double[] {2.2, 0, 1, 0});
		metaData.put(765,  new double[] {1.4, 1, 0, 0});
		metaData.put(777,  new double[] {3.4, 0, 1, 0});
		metaData.put(793,  new double[] {3.4, 0, 1, 0});
		metaData.put(799,  new double[] {3.2, 1, 0, 0});
	}
	
	public static void addToVector(List<Double> vector, double[] data) {
		for(double elem : data) vector.add(elem);
	}
	
	public static double[] getVectorLine(String dataLine) {
		String[] dataCells = dataLine.split(",");
		
		List<Double> rawVector = new ArrayList<Double>();
		
		// based on id, give it first few values (based on metadata)
		addToVector(rawVector, metaData.get(Integer.valueOf(dataCells[0])));
		
		for(int i = 1; i < dataCells.length; i++) {
			
			// decode abcd
			if(dataCells[i].equals("a")) {
				addToVector(rawVector, new double[] { 1, 0, 0, 0 });
				
			} else if(dataCells[i].equals("b")) {
				addToVector(rawVector, new double[] { 0, 1, 0, 0 });
				
			} else if(dataCells[i].equals("c")) {
				addToVector(rawVector, new double[] { 0, 0, 1, 0 });

			} else if(dataCells[i].equals("d")) {
				addToVector(rawVector, new double[] { 0, 0, 0, 1 });
				
			} else {
				rawVector.add(Double.parseDouble(dataCells[i]));
			}
		}
		
//		List<Double> processedVector = new ArrayList<Double>();
//		addToVector(processedVector, Arrays.copyOfRange(rawLine, 0, 41));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 41, 65)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 65, 89)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 89, 113)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 113, 137)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 137, 161)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 161, 185)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 185, 209)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 209, 233)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 233, 257)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 257, 281)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 281, 305)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 305, 329)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 329, 353)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 353, 377)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 377, 401)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 401, 425)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 425, 449)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 449, 473)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 473, 497)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 497, 521)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 521, 545)));
//		addToVector(processedVector, Stats.getTimeSeriesStats(Arrays.copyOfRange(rawLine, 545, 569)));
//		rawVector.addAll(processedVector);
		
		return rawVector.stream().mapToDouble(Double::doubleValue).toArray();
	}
	
	public static void regulateData(String dataPath, String labelPath, String testPath) throws FileNotFoundException {
		List<double[]> normals = new ArrayList<double[]>();
		List<double[]> warnings = new ArrayList<double[]>();
		List<double[]> tests = new ArrayList<double[]>();

		int r = 0;
		for(int i = 0; i < 5; i++) {
			Scanner dataScanner = new Scanner(new File(dataPath + String.valueOf(i) + ".csv"));
			Scanner labelScanner = new Scanner(new File(labelPath + String.valueOf(i) + ".csv"));
		
			while(dataScanner.hasNextLine() && labelScanner.hasNextLine()) {
				String dataLine = dataScanner.nextLine();
				String labelLine = labelScanner.nextLine();
				
				if(labelLine.equals("warning")) {
					warnings.add(getVectorLine(dataLine));
					
				} else if (labelLine.equals("normal")) {
					normals.add(getVectorLine(dataLine));
				}
				
				System.out.println(MessageFormat.format("Normalizing data, record {0}... ", String.valueOf(++r)));
			}
			
			dataScanner.close();
			labelScanner.close();
		}
		
		Scanner testScanner = new Scanner(new File(testPath));
		r = 0;
		
		while(testScanner.hasNextLine()) {
			String testLine = testScanner.nextLine();
			tests.add(getVectorLine(testLine));
			
			System.out.println(MessageFormat.format("Normalizing tests, record {0}... ", String.valueOf(++r)));
		}

		testScanner.close();

		// normalize - search for max value
		double[] maxes = new double[normals.get(0).length];
		for(double[] record : warnings) {
			for(int i = 0; i < record.length; i++) {
				if(maxes[i] < FastMath.abs(record[i])) maxes[i] = FastMath.abs(record[i]);
			}
		}
		
		for(double[] record : normals) {
			for(int i = 0; i < record.length; i++) {
				if(maxes[i] < FastMath.abs(record[i])) maxes[i] = FastMath.abs(record[i]);
			}
		}
		
		for(double[] record : tests) {
			for(int i = 0; i < record.length; i++) {
				if(maxes[i] < FastMath.abs(record[i])) maxes[i] = FastMath.abs(record[i]);
			}
		}
		
		// normalize - divide by max value
		for(double[] record : warnings) {
			for(int i = 0; i < record.length; i++) {
				if(maxes[i] != 0) {
					record[i] = record[i] / maxes[i];
				}
			}
		}
		
		for(double[] record : normals) {
			for(int i = 0; i < record.length; i++) {
				if(maxes[i] != 0) {
					record[i] = record[i] / maxes[i];
				}
			}
		}
		
		for(double[] record : tests) {
			for(int i = 0; i < record.length; i++) {
				if(maxes[i] != 0) {
					record[i] = record[i] / maxes[i];
				}
			}
		}
		
		System.out.println("Saving...");
		DataProvider.write("data/train/norm/normalized-normals.csv", normals);
		DataProvider.write("data/train/norm/normalized-warnings.csv", warnings);
		DataProvider.write("data/test/norm/normalized-testing.csv", tests);
		System.out.println("Done!");
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		regulateData("data/train/raw/additional_training_data_", "data/train/raw/additional_training_labels_", "data/test/raw/testData.csv");
	}
}
