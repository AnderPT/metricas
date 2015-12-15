package pa.iscde.metrix.extensibility;

import org.eclipse.swt.widgets.List;

public interface NewMetric {
	
	String metricName();
	
	TypeNewMetric typeMetric();
	
	List targetMetrics();
	
	

}
