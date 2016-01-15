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
import pa.iscde.metrix.extensibility.NewPredefinedMetric;

class MetrixExtension {
	
	private IExtensionPoint extensionPointExport;
	private IExtensionPoint extensionPointExportNewMetric;
	private MetrixControl metric;
	private MetricAnalyzer metricAnalyzer;

	/**
	 * @param metrixControl 
	 * 
	 */
	
	protected MetrixExtension(MetrixControl metrixControl) {
		
		this.metric = metrixControl;
		this.metricAnalyzer = metric.getAnalyzerFile();
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
						((ExportMetrix)o).exportFile(metricAnalyzer.getMetrics());
						
					} catch (CoreException e1) {
					}
		                
		        
		    }
 
		}
	} 

	protected void newMetricExtension() {
		IExtension[] extensions = extensionPointExportNewMetric.getExtensions();
		for(IExtension e : extensions) {
		    IConfigurationElement[] confElements = e.getConfigurationElements();
		    for(IConfigurationElement c : confElements) {
		            Object o;
					try {
						switch (c.getName()) {
						case "newMetric":
							o = c.createExecutableExtension("class");
							metric.addNewMetric(((NewMetric)o).metricName(), ((NewMetric)o).calcNewMetric(metricAnalyzer.getMetrics()));
							break;
						case "newPredefinedMetric":
							o = c.createExecutableExtension("class");
							new NewMetricCalc(c.getAttribute("metricName"), c.getAttribute("metricType"),
									((NewPredefinedMetric)o).targetMetrics(DefaultMetrics.values()), metricAnalyzer).calcMetric();							
							
							break;
						case "newMetricVisit":
							break;
						default:
							break;
						}
					
						
					} catch (CoreException e1) {
					}
		    }
		}
	}
	
	
}
