package priv.dotjabber.aaia;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	private static final int EPOCH_SIZE = 20;
	private static final int EPOCH_COUNT = 300;

	public static final double RANGE = 3;
	public static final int MAX_POW = 11;
	public static final double MUTATE_PROBAB = 0.1;
	
	public static final String OUTPUT_LOG_PATH = "data/log-pow" + MAX_POW + ".csv";
	
	public static final String OUTPUT_NORMALS_PATH = "data/train/norm/normalized-normals-restult.csv";
	public static final String OUTPUT_WARNINGS_PATH = "data/train/norm/normalized-warnings-result.csv";
	public static final String OUTPUT_TESTING_PATH = "data/result-pow" + MAX_POW + ".csv";

	public static final String TRAIN_NORM_PATH = "data/train/norm/normalized-normals.csv";
	public static final String TRAIN_WARN_PATH = "data/train/norm/normalized-warnings.csv";
	public static final String TEST_PATH = "data/test/norm/normalized-testing-pow" + MAX_POW + ".csv";

	public static void main(String[] args) throws Exception {
		Data data = new Data(TRAIN_NORM_PATH, TRAIN_WARN_PATH);
		PrintWriter log = new PrintWriter(new File(OUTPUT_LOG_PATH));
		
		// training
		List<Individual> currentEpoch = new ArrayList<Individual>();
		System.out.println("Randomly creating individuals for epoch 0...");
		
		for(int i = 0; i < EPOCH_SIZE / 2; i++) {
			currentEpoch.add(Utils.create(data.getVectorSize()));
		}
		
		for(int i = 0; i < EPOCH_COUNT; i++) {
			System.out.println("Epoch " + i);
			System.out.println("Epoch " + i + ": adding individuals...");
			
			for(int j = 0; j < EPOCH_SIZE / 2; j++) {
				currentEpoch.add(Utils.create(data.getVectorSize()));
			}
			
			List<Individual> nextEpoch = new ArrayList<Individual>();
			
			System.out.println("Epoch " + i + ": crossing individuals...");
			for(Individual ind1 : currentEpoch) {
				for(Individual ind2 : currentEpoch) {
					if(ind1 != ind2) {
						Individual ind3 = Utils.cross(ind1, ind2);
						Utils.mutate(ind3);
						
						nextEpoch.add(ind3);
					}
				}
			}
			
			System.out.println("Epoch " + i + ": assessing individuals...");
			nextEpoch.addAll(currentEpoch);
			currentEpoch = new ArrayList<Individual>();
			
			data.pickEqualSample();
			Utils.assess(data, nextEpoch);
			
			System.out.println("Epoch " + i + ": getting rid of the weak ones...");
			Collections.sort(nextEpoch);
			currentEpoch.addAll(nextEpoch.subList(0, EPOCH_SIZE / 2));
			
			currentEpoch.get(0).incTopOccurance();
			System.out.println("best from epoch " + i + ": " + currentEpoch.get(0).getScore());
			System.out.println("best from epoch " + i + " occured times: " + currentEpoch.get(0).getTopOccurance());
			
			log.println(String.valueOf(currentEpoch.get(0).getScore()).replace(".", ","));
			log.flush();
		}
		
		log.close();

		PrintWriter result = new PrintWriter(new File(OUTPUT_TESTING_PATH));
		
		TestData testData = new TestData(TEST_PATH);
		for(double[] testRecord : testData.getTestData()) {
			double score = Function.getScore(testRecord, currentEpoch.get(0));
			result.println(String.valueOf(score));
			result.flush();
		}
		result.close();
		
		result = new PrintWriter(new File(OUTPUT_NORMALS_PATH));
		testData = new TestData(TRAIN_NORM_PATH);
		for(double[] testRecord : testData.getTestData()) {
			double score = Function.getScore(testRecord, currentEpoch.get(0));
			result.println(String.valueOf(score));
			result.flush();
		}
		result.close();
		
		result = new PrintWriter(new File(OUTPUT_WARNINGS_PATH));
		testData = new TestData(TRAIN_WARN_PATH);
		for(double[] testRecord : testData.getTestData()) {
			double score = Function.getScore(testRecord, currentEpoch.get(0));
			result.println(String.valueOf(score));
			result.flush();
		}
		result.close();
	}
}
