package pa.iscde.metrix.view;

import java.awt.GridLayout;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import pa.iscde.metrix.ClassHashMetrix;
import pa.iscde.metrix.ClassVisitor;
import pa.iscde.metrix.MetricAnalyzer;
import pa.iscde.metrix.activator.Activator;
import pa.iscde.metrix.extensibility.ExportMetrix;
import pa.iscde.metrix.extensibility.typeExport;
import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.javaeditor.internal.JavaEditorActivator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class MetrixView implements PidescoView {

	private JavaEditorServices editorServices;
	private ProjectBrowserServices projectServices;
	private ClassVisitor cv;
	private TableTree tableTree;
	private String[] items = {"Current File","Current Project"};
	private Combo combo;
	
	public MetrixView() {
	}

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		
		//testExtension();
		getServices();
		viewArea.setLayout(new RowLayout());

		Composite bar = new Composite(viewArea, SWT.BORDER_DASH);
		bar.setLayout(new FillLayout());
		
		combo = new Combo(bar, SWT.READ_ONLY);
		combo.setItems(items);
		combo.select(0);
		combo.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (combo.getText().equals("Current File")) {
					getOpenedFileMetrics();

				} else if (combo.getText().equals("Current Project")) {
					getOpenedProjectMetrics();
				}
			}
		});
		
		//button export
		  final Button button = new Button(viewArea, SWT.PUSH);
		  button.setBounds(40, 50, 50, 20);
		  button.setText("Export");
		  
		  button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				testExtension();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		tableTree = new TableTree(viewArea);
		tableTree.addColumn("Metrix");
		tableTree.addColumn("Value");
		tableTree.addItems();
		editorServices.addListener(new JavaEditorListener.Adapter(){
					
			@Override
			public void fileOpened(File file) {				
				super.fileOpened(file);	
				if (combo.getText().equals("Current File")) {
					analyzeMetrics(file);
				}
			}
		});
		
	}
	
	protected void getOpenedProjectMetrics() {
			
			PackageElement root = projectServices.getRootPackage();
			
			if (root.hasChildren()) {
				for (SourceElement child : root.getChildren()) {
					if (child.isPackage()) {
						System.out.println("Package: " + child.getName());
	
					} else if (child.isClass()) {
						child.getFile();
						System.out.println("Class: " + child.getName());
					}
				}
			}
			
		}
	
	private void getOpenedFileMetrics() {
		File file = editorServices.getOpenedFile();
		analyzeMetrics(file);
	}

	private void analyzeMetrics(File file) {
		MetricAnalyzer metric = new MetricAnalyzer();
		cv = new ClassVisitor(metric);
		editorServices.parseFile(file, cv);
		tableTree.updateTable(metric);
	}

	private void getServices() {
		editorServices = Activator.getEditorService();
		projectServices = Activator.getProjectService();

	}

	private void testExtension() {
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extRegistry.getExtensionPoint("pa.iscde.metrixexport");
		
		IExtension[] extensions = extensionPoint.getExtensions();
		for(IExtension e : extensions) {
			System.out.println("PASSOU");
		    IConfigurationElement[] confElements = e.getConfigurationElements();
		    for(IConfigurationElement c : confElements) {
//		        String s = c.getAttribute("id");
//		        System.out.println(s);
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
