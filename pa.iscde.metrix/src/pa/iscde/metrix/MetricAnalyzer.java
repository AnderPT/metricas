package pa.iscde.metrix;

import java.util.HashMap;

import org.eclipse.swt.widgets.List;

public class MetricAnalyzer {
	
	private HashMap<String, Integer> metrics = new HashMap<String, Integer>();
	private String[] inicialMetrics = new String[] {"Number of Lines", "Number of Methods", "Number of Constructors"
			, "Number of Fields", "Number of Comments", "Number of Characters" , "Number of Packages"};

	
	public MetricAnalyzer() {
		putInicialMetrics();
	}
	
	private void putInicialMetrics() {
		for (String m : inicialMetrics) {
			metrics.put(m, 0);
		}
	}

//	private void addMetric(String name, int value) {
//		Metric m = new Metric(name, value);
//		metrics.put(m.getName(),m.getValue());
//	}

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
