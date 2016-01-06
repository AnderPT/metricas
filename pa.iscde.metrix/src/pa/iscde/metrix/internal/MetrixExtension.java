package pa.iscde.metrix.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import pa.iscde.metrix.extensibility.DefaultMetrics;
import pa.iscde.metrix.extensibility.ExportMetrix;
import pa.iscde.metrix.extensibility.NewMetric;

class MetrixExtension {
	
	private IExtensionPoint extensionPointExport;
	private IExtensionPoint extensionPointExportNewMetric;
	private MetricAnalyzer metric;

	/**
	 * @param metric 
	 * 
	 */
	
	protected MetrixExtension(MetricAnalyzer metric) {
		
		this.metric = metric;
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		extensionPointExport = extRegistry.getExtensionPoint("pa.iscde.metrixexport");
		extensionPointExportNewMetric = extRegistry.getExtensionPoint("pa.iscde.addmetricext");

	}
	
	/**
	 * 
	 */
	protected void exportMetricExtension() {
		
		IExtension[] extensions = extensionPointExport.getExtensions();
		for(IExtension e : extensions) {
		    IConfigurationElement[] confElements = e.getConfigurationElements();
		    System.out.println("cnf " + confElements.length);
		    for(IConfigurationElement c : confElements) {
		    							System.out.println("ENTROU");

		            Object o;
					try {
						o = c.createExecutableExtension("class");
						((ExportMetrix)o).exportFile(metric.getMetrics());
						
					} catch (CoreException e1) {
						e1.printStackTrace();
					}
		                
		        
		    }
 
		}
	} 

	protected void newMetricExtension() {
		IExtension[] extensions = extensionPointExportNewMetric.getExtensions();
		for(IExtension e : extensions) {
			System.out.println("PASSOU");
		    IConfigurationElement[] confElements = e.getConfigurationElements();
		    for(IConfigurationElement c : confElements) {
		            Object o;
					try {
						o = c.createExecutableExtension("class");
						System.out.println(((NewMetric)o).metricName());
						new NewMetricCalc(((NewMetric)o).metricName(),((NewMetric)o).typeMetric(),
								((NewMetric)o).targetMetrics(DefaultMetrics.values()), metric).calcMetric();;
		
						
					} catch (CoreException e1) {
						e1.printStackTrace();
					}
		            
		            
		            
		        
		    }
		}
	}
	
	
}
