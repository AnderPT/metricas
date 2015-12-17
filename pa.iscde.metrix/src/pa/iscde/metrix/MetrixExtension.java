package pa.iscde.metrix;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;



import pa.iscde.metrix.extensibility.ExportMetrix;
import pa.iscde.metrix.extensibility.NewMetric;

public class MetrixExtension {
	
	private IExtensionPoint extensionPointExport;
	private IExtensionPoint extensionPointExportNewMetric;

	/**
	 * 
	 */
	
	public MetrixExtension() {
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		extensionPointExport = extRegistry.getExtensionPoint("pa.iscde.metrixexport");
		extensionPointExportNewMetric = extRegistry.getExtensionPoint("pa.iscde.addmetricext");

	}
	
	/**
	 * 
	 */
	public void exportMetricExtension() {
		IExtension[] extensions = extensionPointExport.getExtensions();
		for(IExtension e : extensions) {
			System.out.println("PASSOU");
			
		
			
		   IConfigurationElement[] confElements = e.getConfigurationElements();
		    for(IConfigurationElement c : confElements) {
		        try {
		            Object o = c.createExecutableExtension("class");
		            
		            String fileName;
		            		            
		            switch (((ExportMetrix)o).exportMetrix().toString()) {
		            case "CSV":
		            	 fileName = System.getProperty("user.home")+"/metrix.csv";
						
						System.out.println("Write CSV file:");
						CsvFileWriter.writeCsvFile(fileName);
		                break;
		            case "HTML":
		            
		            	 fileName = System.getProperty("user.home")+"/metrix.html";
						
						System.out.println("Write HTML file:");
						HtmlFileWrite.writeHtmlFile(fileName);
					
		            break;
		            
		            default:
		            	
		            	System.out.println("exportMetrix vazio");
		            	break;
		            }
		            
		        	
		            
		            /*
		            //((ExportMetrix)o).exportMetrix();
		            
		            //GERAR CSV
		            ClassHashMetrix cm = new ClassHashMetrix();
		            cm.init();
		            System.out.println(cm.getHmap().size());
		            //System.out.println();
		            
		            Set set = cm.getHmap().entrySet();
		            Iterator iterator = set.iterator();
		            while(iterator.hasNext()) {
		               Map.Entry mentry = (Map.Entry)iterator.next();
		               System.out.print("Metrica is: "+ mentry.getKey() + " & Value is: ");
		               System.out.println(mentry.getValue());
		            }
		            
		            */
		            
		        } catch (CoreException e1) {
		            // TODO Auto- catch block
		            e1.printStackTrace();
		        }
		    }
		}
	} 

	public void newMetricExtension() {
		IExtension[] extensions = extensionPointExportNewMetric.getExtensions();
		for(IExtension e : extensions) {
			System.out.println("PASSOU");
		    IConfigurationElement[] confElements = e.getConfigurationElements();
		    for(IConfigurationElement c : confElements) {
		            Object o;
					try {
						o = c.createExecutableExtension("class");
						System.out.println(((NewMetric)o).metricName());
			            //((ExportMetrix)o).exportMetrix();
						
						
					} catch (CoreException e1) {
						e1.printStackTrace();
					}
		            
		            
		            
		        
		    }
		}
	}
	
	
}
