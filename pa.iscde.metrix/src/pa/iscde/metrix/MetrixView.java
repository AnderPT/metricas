package pa.iscde.metrix;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jface.dialogs.AbstractSelectionDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.javaeditor.internal.JavaEditorActivator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener.Adapter;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class MetrixView implements PidescoView {

	private JavaEditorServices services;
	private ClassVisitor cv;
	private MetrixView content;
	private int numMethods = 0; 
	private TableTree tableTree;
	private ViewForm viewForm;
	private String[] items = {"Current File","Current Project"};
	
	public MetrixView() {
		content = this;
	}

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		
//		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
//		IExtensionPoint extensionPoint = extRegistry.getExtensionPoint("pa.iscde.test.textext");
//		
//		IExtension[] extensions = extensionPoint.getExtensions();
//		for(IExtension e : extensions) {
//			System.out.println("PASSOU");
//		    IConfigurationElement[] confElements = e.getConfigurationElements();
//		    for(IConfigurationElement c : confElements) {
//		        String s = c.getAttribute("id");
//		        System.out.println(s);
//		        try {
//		            Object o = c.createExecutableExtension("class");
//		        } catch (CoreException e1) {
//		            // TODO Auto-generated catch block
//		            e1.printStackTrace();
//		        }
//		    }
//		}
		
		services = JavaEditorActivator.getInstance().getServices();
		viewArea.setLayout(new RowLayout());
//		viewForm = new ViewForm(viewArea, SWT.NONE);
//		viewForm.setLayout(new FillLayout());
//		viewForm.setBounds(10, 10, 200, 55);
		
		Combo combo = new Combo(viewArea, SWT.NONE);
		combo.setItems(items);
		combo.setBounds(0, 0, 200, 400);
//		viewForm.setTopLeft(combo);
		
		
		
		tableTree = new TableTree(viewArea);
		tableTree.addColumns();
		tableTree.addItems();
//		viewForm.setContent(tableTree.getTree());
		
		services.addListener( new JavaEditorListener.Adapter(){

		@Override
			public void fileOpened(File file) {
				System.out.println("Opened File");
				super.fileOpened(file);
				MetricAnalyzer metric = new MetricAnalyzer();
				cv = new ClassVisitor(metric);
				services.parseFile(file, cv);
				tableTree.updateTable(metric);
			}	
	      });
	}
	
	
}
