package pa.iscde.metrix.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
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
	private String extensionPath;
	private HashMap<String, Integer> metrics = new HashMap<String, Integer>();
	private MetrixControl metrixController;

	/**
	 * 
	 * @param file
	 */
	
	protected MetricAnalyzer(File file, MetrixControl metrixController) {
		this.className = file.getName();
		this.metrixController = metrixController;
		initializeMetrics(metrixController.getMetrics());

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

	
	private void initializeMetrics(ArrayList<String> arrayList) {
		for (String s : arrayList) {
			metrics.put(s, 0);
		}
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
	

	protected void addNewMetric(String name, double value) {
//		metrics.put(name, value);
		metrixController.addMetric(this);
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

	
	public void setExtensionPath(String extensionPath) {
		this.extensionPath = extensionPath;
	}
	
	public String getExtensionPath() {
		return extensionPath;
	}

	
	

}
