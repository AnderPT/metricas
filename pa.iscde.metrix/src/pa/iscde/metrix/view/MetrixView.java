package pa.iscde.metrix.view;

import java.io.File;
import java.util.Map;

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

import pa.iscde.metrix.ClassVisitor;
import pa.iscde.metrix.MetricAnalyzer;
import pa.iscde.metrix.MetrixExtension;
import pa.iscde.metrix.activator.Activator;
import pt.iscte.pidesco.extensibility.PidescoView;
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
					PackageElement root = projectServices.getRootPackage();
					getOpenedProjectMetrics(root);
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
				new MetrixExtension().exportMetricExtension();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		  
		  Button addMetricBut = new Button(viewArea, SWT.PUSH);
		  addMetricBut.setBounds(40, 50, 50, 20);
		  addMetricBut.setText("Add New Metric");
		  
		  addMetricBut.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
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
	
	protected void getOpenedProjectMetrics(PackageElement root) {
			
		if (root.hasChildren()) {
			for (SourceElement child : root.getChildren()) {
				if (child.isPackage()) {
					getOpenedProjectMetrics((PackageElement)child);
					System.out.println("Package: " + child.getName());

				} else if (child.isClass()) {
//					analyzeClass(child.getFile(), root);
					System.out.println("Class: " + child.getName());
				}
			}
		}
		tableTree.updatePackages();
		}
	
//	private void analyzeClass(File file, PackageElement root) {
//		MetricAnalyzer metric = new MetricAnalyzer(file);
//		cv = new ClassVisitor(metric);
//		editorServices.parseFile(file, cv);
//		tableTree.updateTable(metric);
//	}

	private void getOpenedFileMetrics() {
		File file = editorServices.getOpenedFile();
		analyzeMetrics(file);
	}

	private void analyzeMetrics(File file) {
		MetricAnalyzer metric = new MetricAnalyzer(file);
		cv = new ClassVisitor(metric);
		editorServices.parseFile(file, cv);
		tableTree.updateTable(metric);
	
	}

	private void getServices() {
		editorServices = Activator.getEditorService();
		projectServices = Activator.getProjectService();

	}
	
	
}
