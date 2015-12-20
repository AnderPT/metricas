package pa.iscde.metrix.internal;

import java.util.Collection;

import pa.iscde.metrix.extensibility.DefaultMetrics;
import pa.iscde.metrix.extensibility.TypeNewMetric;

class NewMetricCalc {
	
	private String name;
	private TypeNewMetric type;
	private Collection<DefaultMetrics> targetList;
	private MetricAnalyzer metric;

	protected NewMetricCalc(String name, TypeNewMetric type, Collection<DefaultMetrics> targetList, MetricAnalyzer metric) {
		this.name = name;
		this.type = type;
		this.targetList = targetList;
		this.metric = metric;
	}
	
	protected void calcMetric() {
		int result = 0;
		for (DefaultMetrics metricName : targetList) {
			if (result != 0) {
				switch (type.name()) {
				case "SUBTRACT":
					result -= metric.getNumbMetric(new DefaultMetricsTranscation().getMetricName(metricName));
					break;
	
				default:
					break;
				}
			} else {
				result = metric.getNumbMetric(new DefaultMetricsTranscation().getMetricName(metricName));
			}
		}
		metric.addNewMetric(name, result);
	}

}
