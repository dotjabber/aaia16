package priv.dotjabber.aaia;

import org.apache.commons.math3.util.FastMath;

public class Function {
	public static double getScore(double[] data, double value, Individual ind) throws Exception {
		double response = getScore(data, ind);
		return FastMath.pow(value - response, 2);
	}
	
	public static double getScore(double[] data, Individual ind) throws Exception {
		double response = 0;
		
		for(int i = 0; i < ind.getCoeficients().length; i++) {
			if(Double.isFinite(data[i])) {
				
				double regularized = FastMath.pow(data[i] * ind.getRegulators()[i] + ind.getAddictors()[i], ind.getExponents()[i]);
				response += ind.getCoeficients()[i] * regularized + ind.getTotalitarians()[i];
				
				if(!Double.isFinite(response)) {
					throw new Exception();
				}
			}
		}
		
		if(response > 1) response = 1;
		if(response <= 0) response = 0;
		return response;
	}
}
