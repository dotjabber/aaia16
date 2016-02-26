package priv.dotjabber.aaia;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.FastMath;

public class Stats {
	public static double[] getTimeSeriesStats(double[] data) {

		SimpleRegression regression = new SimpleRegression();
		for(int i = 0; i < data.length; i++) {
			regression.addData(i, data[i]);
		}

		return new double[] {
				StatUtils.mean(data),
				FastMath.sqrt(StatUtils.variance(data)),
				StatUtils.percentile(data, 50),
				StatUtils.geometricMean(data),
				StatUtils.min(data),
				StatUtils.max(data),
				StatUtils.variance(data),
				StatUtils.product(data),
				StatUtils.sumSq(data),
				StatUtils.sumLog(data),
				regression.getSlope(),
				regression.getIntercept(),
				regression.getR(),
				regression.getMeanSquareError(),
				regression.getSignificance(),
				regression.getSumOfCrossProducts()
		};
	}
}
