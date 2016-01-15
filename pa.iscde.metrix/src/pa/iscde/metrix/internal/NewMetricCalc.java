package pa.iscde.metrix.internal;

import java.util.Collection;

import pa.iscde.metrix.extensibility.DefaultMetrics;

class NewMetricCalc {
	
	private String name;
	private String type;
	private Collection<DefaultMetrics> targetList;
	private MetricAnalyzer metric;

	protected NewMetricCalc(String name, String type, Collection<DefaultMetrics> targetList, MetricAnalyzer metric) {
		this.name = name;
		this.type = type;
		this.targetList = targetList;
		this.metric = metric;
	}
	
	protected void calcMetric() {
		int result = 0;
		for (DefaultMetrics metricName : targetList) {
			if (result != 0) {
				switch (type) {
				case "SUBTRACT":
					result -= metric.getNumbMetric(new DefaultMetricsTranscation().getMetricName(metricName));
					break;
				case "AVERAGE":
					result += metric.getNumbMetric(new DefaultMetricsTranscation().getMetricName(metricName));
					break;
				case "COUNT": 
					result++;
					break;
				case "SUM":
					result += metric.getNumbMetric(new DefaultMetricsTranscation().getMetricName(metricName));;
					break;
				default:
					break;
				}
			} else {
				result = metric.getNumbMetric(new DefaultMetricsTranscation().getMetricName(metricName));
			}
		}
		if (type.equals("AVERAGE")) {
			result = result / targetList.size();
		}
		addMetric(name,result);
	}
	
	protected void addMetric(String nameA, int resultA) {
		metric.addNewMetric(nameA, resultA);
	}

}
