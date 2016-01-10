package pa.iscde.metrix.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import pa.iscde.metrix.activator.Activator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.model.PackageElement;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class MetrixControl {
	

	private JavaEditorServices editorServices;
	private ProjectBrowserServices projectServices;
	private ArrayList<String> listPackages = new ArrayList<String>();
	private ArrayList<MetricAnalyzer> classesMetrics = new ArrayList<MetricAnalyzer>();
	private ArrayList<String> metrics = new ArrayList<String>();
	private Multimap<String, MetricAnalyzer > map;
	private ClassVisitor cv;

	
	private String[] inicialMetrics = new String[] {"Number of Lines", "Number of Methods", "Number of Constructors"
			, "Number of Fields", "Number of Comments", "Number of Characters" , "Number of Packages"};
	private MetrixView view;

	protected MetrixControl(MetrixView view) {
		this.view = view;
	}
	
	protected void init() {
		setServices();
		inicializeMetrics();
		map =  ArrayListMultimap.create();
	}	
	
	private void inicializeMetrics() {
		for (String m : inicialMetrics) {
			metrics.add(m);
		}
	}

	protected void analyzeMetrics() {
		File file = editorServices.getOpenedFile();
		inspectFile(file);
	}
	
	private void inspectFile(File file) {
		MetricAnalyzer metric = new MetricAnalyzer(file, this);
		cv = new ClassVisitor(metric);
		editorServices.parseFile(file, cv);
		view.updateTree(metric);
	}

	

	private void setServices() {
		editorServices = Activator.getEditorService();
		projectServices = Activator.getProjectService();

	}

	public void analyzeProjectMetrics() {
		PackageElement root = projectServices.getRootPackage();
		ArrayList<String> packages  = getListPackages(root, "");		
		view.updatePackages(classesMetrics, packages);
		packages.clear();
		map.clear();
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
					File file = child.getFile();
					if ( extension.equals("") ){
						extension = "default";
					}
					MetricAnalyzer childAnalizer = new MetricAnalyzer(file, this);
					childAnalizer.setExtensionPath(extension);
					cv = new ClassVisitor(childAnalizer);
					editorServices.parseFile(file, cv);
					classesMetrics.add(childAnalizer);
				}
			}
		}
		
		return listPackages;
	}

	public void setEditorServiceListener(final String comboText) {
		editorServices.addListener(new JavaEditorListener.Adapter(){
			@Override
			public void fileOpened(File file) {				
				super.fileOpened(file);	
				if (comboText.equals("Current File")) {
					inspectFile(file);
				}
			}
		});
	}

	public ArrayList<String> getMetrics() {
		return metrics;
	}

	public void addMetric(MetricAnalyzer metricAnalyzer) {
		metrics.add(metricAnalyzer.getClassName());
		view.updateTree(metricAnalyzer);
	}

}
