package priv.dotjabber.aaia;

import java.io.FileNotFoundException;
import java.util.List;

import priv.dotjabber.aaia.data.DataProvider;

public class TestData {
	private List<double[]> testData;

	public TestData(String testDataPath) throws FileNotFoundException {
		testData = DataProvider.read(testDataPath);
	}
	
	public List<double[]> getTestData() {
		return testData;
	}

	public void setTestData(List<double[]> testData) {
		this.testData = testData;
	}
}
