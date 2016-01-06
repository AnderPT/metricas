package pa.iscde.metrix.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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
	private MetricAnalyzer metric;
	private HashMap<String, Integer> metricsList;
	private Multimap<String, MetricAnalyzer > map;
	private ArrayList<String> listPackages = new ArrayList<String>();
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<MetricAnalyzer> values = new ArrayList<MetricAnalyzer>();

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		
		map =  ArrayListMultimap.create();
		metric = new MetricAnalyzer();
		metricsList = metric.initializeMap();
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
					ArrayList<String> packages  = getListPackages(root, "");
					
					
					tableTree.updatePackages(map, packages);
					packages.clear();
					map.clear();
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
				new MetrixExtension(metric).exportMetricExtension();
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
				new MetrixExtension(metric).newMetricExtension();
			}
		
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		tableTree = new TableTree(viewArea);
		tableTree.init("Metrix", "Value");
		//analyzeMetrics(editorServices.getOpenedFile());
		
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
	
	private ArrayList<String> getListPackages(PackageElement root, String extension) {
		
		if (root.hasChildren()) {
			if (!root.equals(projectServices.getRootPackage())) {
				listPackages.add(extension);
			}
			for (SourceElement child : root.getChildren()) {
				String newExt = extension; 
				if (!extension.equals("")) {
					newExt += ".";
				}
				newExt += child.getName();
				if (child.isPackage()) {
					getListPackages((PackageElement)child, newExt);
				} else if (child.isClass()) {
					if ( extension.equals("") ){
						extension = "default";
					}
					
					MetricAnalyzer childAnalizer = new MetricAnalyzer(child.getFile(), this);
					cv = new ClassVisitor(childAnalizer);
					editorServices.parseFile(child.getFile(), cv);
//					System.out.println(child.getName() + " -::- " + childAnalizer.getMetrics().toString() );
					map.put(extension, childAnalizer);
					
					System.out.println("--" + extension + "--");
					System.out.println(childAnalizer.printMetrics());
					System.out.println("**********MAPP***************");
					for (MetricAnalyzer key : map.get(extension)) {
						System.out.println(key.getClassName());
						System.out.println(key.getMetrics().toString());
					}
					System.out.println("_______________________");
					names.add(extension);
					values.add(childAnalizer);
					
				}
			}
		}
		
		return listPackages;
	}

	private void getOpenedFileMetrics() {
		File file = editorServices.getOpenedFile();
		analyzeMetrics(file);
	}

	private void analyzeMetrics(File file) {
		metric = new MetricAnalyzer(file, this);
		cv = new ClassVisitor(metric);
		editorServices.parseFile(file, cv);
		tableTree.updateTable(metric);
	}

	private void getServices() {
		editorServices = Activator.getEditorService();
		projectServices = Activator.getProjectService();

	}

	public HashMap<String, Integer> getMetricsList() {
		return metricsList;
	}

	public void addMetric(String name, int value) {
		metricsList.put(name, value);
		tableTree.addNewMetric(name, value);
		tableTree.updateTable(metric);
	}
	
	
	
	
}
