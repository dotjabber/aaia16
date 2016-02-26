package priv.dotjabber.aaia;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import priv.dotjabber.aaia.data.DataProvider;

public class Data {
	private final static Random RAND = new Random();
	
	private List<double[]> normals;
	private List<double[]> warnings;
	
	private List<double[]> sampleData;
	private List<Double> sampleValues;
	
	private int vectorSize;

	public Data(String normalsPath, String warningsPath) throws FileNotFoundException {
		normals = DataProvider.read(normalsPath);
		warnings = DataProvider.read(warningsPath);
		
		vectorSize = normals.get(0).length;
		pickEqualSample();
	}
	
	public void pickEqualSample() {
		Collections.shuffle(normals);
		Collections.shuffle(warnings);
		
		sampleData = new ArrayList<double[]>();
		sampleValues = new ArrayList<Double>();
		
		for(int i = 0; i < warnings.size() / 4; i++) {
			if(RAND.nextDouble() > 0.5) {
				sampleData.add(warnings.get(i));
				sampleValues.add(1.0);
				
			} else {
				sampleData.add(normals.get(i));
				sampleValues.add(0.0);
			}
		}
	}

	public List<double[]> getSampleData() {
		return sampleData;
	}

	public List<Double> getSampleValues() {
		return sampleValues;
	}

	public int getVectorSize() {
		return vectorSize;
	}

	public List<double[]> getNormals() {
		return normals;
	}

	public void setNormals(List<double[]> normals) {
		this.normals = normals;
	}

	public List<double[]> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<double[]> warnings) {
		this.warnings = warnings;
	}
}
