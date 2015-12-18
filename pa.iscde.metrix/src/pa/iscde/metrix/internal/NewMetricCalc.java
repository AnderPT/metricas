package pa.iscde.metrix.internal;

import java.util.Collection;

import pa.iscde.metrix.extensibility.TypeNewMetric;

public class NewMetricCalc {
	
	private String name;
	private TypeNewMetric type;
	private Collection<String> targetList;
	private MetricAnalyzer metric;

	public NewMetricCalc(String name, TypeNewMetric type, Collection<String> targetList, MetricAnalyzer metric) {
		this.name = name;
		this.type = type;
		this.targetList = targetList;
		this.metric = metric;
	}
	
	public void calcMetric() {
		int result = 0;
		for (String metricName : targetList) {
			if (result != 0) {
				switch (type.name()) {
				case "SUBTRACT":
					result -= metric.getNumbMetric(metricName);
					break;
	
				default:
					break;
				}
			} else {
				result = metric.getNumbMetric(metricName);
			}
		}
		metric.addNewMetric(name, result);
	}

}
