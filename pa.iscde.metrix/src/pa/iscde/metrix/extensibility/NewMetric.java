package pa.iscde.metrix.extensibility;

import java.util.Collection;

public interface NewMetric {
	
	public String metricName();
	
	public TypeNewMetric typeMetric();
	
	public Collection<String> targetMetrics();
	
	

}
