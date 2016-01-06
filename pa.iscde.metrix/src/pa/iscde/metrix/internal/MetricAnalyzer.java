package pa.iscde.metrix.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Class 
 * 
 */

class MetricAnalyzer {
	
	private String className; 
	private File file; 
	private HashMap<String, Integer> metrics = new HashMap<String, Integer>();
	private String[] inicialMetrics = new String[] {"Number of Lines", "Number of Methods", "Number of Constructors"
			, "Number of Fields", "Number of Comments", "Number of Characters" , "Number of Packages"};
	private MetrixView view;

	/**
	 * 
	 * @param file
	 */
	
	protected MetricAnalyzer(File file, MetrixView view) {
		this.file = file;
		this.className = file.getName();
		this.view = view;
		metrics = view.getMetricsList();
		normalizeMetrics();

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
	
	private void normalizeMetrics() {
		 Set set = metrics.entrySet();
         Iterator iterator = set.iterator();
         while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            //metrics.replace((String) mentry.getKey(), 0);
         }
	}

	public MetricAnalyzer() {}

	private HashMap<String, Integer> putInicialMetrics() {
		for (String m : inicialMetrics) {
			metrics.put(m, 0);
		}
		return metrics;
	}

	
	/**
	 * 
	 * @param string
	 * @return metrics
	 */
	protected int getNumbMetric(String metricName) {
		return metrics.get(metricName);
	}

	/**
	 * 
	 * @param m
	 */
	
	protected void incremetMetric(String m) {
		int v = metrics.get(m) + 1;
		
		metrics.remove(m);
		metrics.put(m, v);
		
	}
	
	/**
	 * 
	 * @return metrics
	 */
	
	protected HashMap<String, Integer> getMetrics() {
		return metrics;
	}
	
	/**
	 * 
	 * @return inicialMetrics
	 */
	
	protected String[] getInicialMetrics() {
		return inicialMetrics;
	}

	protected void addNewMetric(String name, int value) {
		view.addMetric(name,value);
	}

	protected HashMap<String, Integer> initializeMap() {
		return putInicialMetrics();
	}
	
	public File getFile() {
		return file;
	}
	
	public String getClassName() {
		return className;
	}

	public String printMetrics() {
		String result= "";
		Set set = metrics.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
           Map.Entry mentry = (Map.Entry)iterator.next();
           result += "Metrica is: "+ mentry.getKey() + " & Value is: " + mentry.getValue() + "\n";
        }
		return result;
	}

	
	

}
