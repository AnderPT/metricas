package pa.iscde.metrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;


public class MetricAnalyzer {
	
	private HashMap<String, Integer> metrics = new HashMap<String, Integer>();
	private String[] inicialMetrics = new String[] {"Number of Lines", "Number of Methods", "Number of Constructors"
			, "Number of Fields", "Number of Comments", "Number of Characters" , "Number of Packages"};

	
	public MetricAnalyzer(File file) {
		putInicialMetrics();
		
		LineNumberReader lnr;
		try {
			lnr = new LineNumberReader(new FileReader(file));
			lnr.skip(Long.MAX_VALUE);
			metrics.replace("Number of Lines", lnr.getLineNumber() + 1); 
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

	public String getNumbMetric(String string) {
		return metrics.get(string) + "";
	}

	public void incremetMetric(String m) {
		int v = metrics.get(m) + 1;
		metrics.replace(m, v);
	}
	
	public HashMap<String, Integer> getMetrics() {
		return metrics;
	}
	
	public String[] getInicialMetrics() {
		return inicialMetrics;
	}

}
