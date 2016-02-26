package priv.dotjabber.aaia;

import java.util.List;
import java.util.Random;

public class Utils {
	private final static Random RAND = new Random();
	
	public static Individual cross(Individual first, Individual second) {
		int size = first.getCoeficients().length;
		
		Individual third = new Individual(size);
		
		int coefCrossPoint = RAND.nextInt(size);
		for(int i = 0; i < size; i++) {
			if(i < coefCrossPoint) {
				third.getCoeficients()[i] = first.getCoeficients()[i];
						
			} else {
				third.getCoeficients()[i] = second.getCoeficients()[i]; 
			}
		}
		
		int expoCrossPoint = RAND.nextInt(size);
		for(int i = 0; i < size; i++) {
			if(i < expoCrossPoint) {
				third.getExponents()[i] = first.getExponents()[i];
						
			} else {
				third.getExponents()[i] = second.getExponents()[i]; 
			}
		}

		int reguCrossPoints = RAND.nextInt(size);
		for(int i = 0; i < size; i++) {
			if(i < reguCrossPoints) {
				third.getRegulators()[i] = first.getRegulators()[i];
						
			} else {
				third.getRegulators()[i] = second.getRegulators()[i]; 
			}
		}
		
		int addictorCrossPoint = RAND.nextInt(size);
		for(int i = 0; i < size; i++) {
			if(i < addictorCrossPoint) {
				third.getAddictors()[i] = first.getAddictors()[i];
						
			} else {
				third.getAddictors()[i] = second.getAddictors()[i]; 
			}
		}
		
		int totalitCrossPoint = RAND.nextInt(size);
		for(int i = 0; i < size; i++) {
			if(i < totalitCrossPoint) {
				third.getTotalitarians()[i] = first.getTotalitarians()[i];
						
			} else {
				third.getTotalitarians()[i] = second.getTotalitarians()[i]; 
			}
		}
		
		return third;
	}
	
	public static void mutate(Individual ind) {
		int size = ind.getCoeficients().length;
		
		for(int i = 0; i < size; i++) {
			if(RAND.nextDouble() > Main.MUTATE_PROBAB) {
				ind.getCoeficients()[i] = RAND.nextDouble() * Main.RANGE - Main.RANGE / 2;
			}
		
			if(RAND.nextDouble() > Main.MUTATE_PROBAB) {
				ind.getExponents()[i] = RAND.nextInt(Main.MAX_POW + 1);
			}
			
			if(RAND.nextDouble() > Main.MUTATE_PROBAB) {
				ind.getRegulators()[i] = RAND.nextDouble();
			}
			
			if(RAND.nextDouble() > Main.MUTATE_PROBAB) {
				ind.getAddictors()[i] = RAND.nextDouble() * Main.RANGE - Main.RANGE / 2;
			}
			
			if(RAND.nextDouble() > Main.MUTATE_PROBAB) {
				ind.getTotalitarians()[i] = RAND.nextDouble() * Main.RANGE - Main.RANGE / 2;
			}
		}	
	}
	
	public static Individual create(int length) {
		return new Individual(length);
	}
	
	public static void assess(Data data, List<Individual> individuals) {
		
		for(Individual ind : individuals) {
			try {
				double score = 0;
				for(int i = 0; i < data.getSampleData().size(); i++) {
					score += Function.getScore(data.getSampleData().get(i), data.getSampleValues().get(i), ind); 
				}
				ind.setScore(score);
			} catch(Exception ex) {
				ind.setScore(Double.MAX_VALUE);
			}
		}
	}
}
