package pa.iscde.metrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;

/**
 * Class 
 * 
 */

public class MetricAnalyzer {
	
	private HashMap<String, Integer> metrics = new HashMap<String, Integer>();
	private String[] inicialMetrics = new String[] {"Number of Lines", "Number of Methods", "Number of Constructors"
			, "Number of Fields", "Number of Comments", "Number of Characters" , "Number of Packages"};

	/**
	 * 
	 * @param file
	 */
	
	public MetricAnalyzer(File file) {
		putInicialMetrics();
		
		LineNumberReader lnr;
		try {
			lnr = new LineNumberReader(new FileReader(file));
			lnr.skip(Long.MAX_VALUE);
		
			metrics.remove(lnr.getLineNumber());
			metrics.put("Number of Lines", lnr.getLineNumber() + 1);
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void putInicialMetrics() {
		for (String m : inicialMetrics) {
			metrics.put(m, 0);
		}
	}

	
	/**
	 * 
	 * @param string
	 * @return metrics
	 */
	public String getNumbMetric(String string) {
		return metrics.get(string) + "";
	}

	/**
	 * 
	 * @param m
	 */
	
	public void incremetMetric(String m) {
		int v = metrics.get(m) + 1;
		
		metrics.remove(m);
		metrics.put(m, v);
		
	}
	
	/**
	 * 
	 * @return metrics
	 */
	
	public HashMap<String, Integer> getMetrics() {
		return metrics;
	}
	
	/**
	 * 
	 * @return inicialMetrics
	 */
	
	public String[] getInicialMetrics() {
		return inicialMetrics;
	}

}
