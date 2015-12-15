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

public class MetrixExtension {
	
	private IExtensionPoint extensionPointExport;

	/**
	 * 
	 */
	
	public MetrixExtension() {
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		extensionPointExport = extRegistry.getExtensionPoint("pa.iscde.metrixexport");
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
		            System.out.println(((ExportMetrix)o).exportMetrix());
		            //((ExportMetrix)o).exportMetrix();
		            
		            //GERAR CSV
		            ClassHashMetrix cm = new ClassHashMetrix();
		            cm.init();
		            System.out.println(cm.getHmap().size());
		            
		            Set set = cm.getHmap().entrySet();
		            Iterator iterator = set.iterator();
		            while(iterator.hasNext()) {
		               Map.Entry mentry = (Map.Entry)iterator.next();
		               System.out.print("Metrica is: "+ mentry.getKey() + " & Value is: ");
		               System.out.println(mentry.getValue());
		            }
		            
		        } catch (CoreException e1) {
		            // TODO Auto- catch block
		            e1.printStackTrace();
		        }
		    }
		}
	}

	
	
}
