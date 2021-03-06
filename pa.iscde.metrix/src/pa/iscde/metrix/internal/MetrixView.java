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

	private ClassVisitor cv;
	private TableTree tableTree;
	private String[] items = {"Current File","Current Project"};
	private Combo combo;
	private MetricAnalyzer metric;
	private HashMap<String, Integer> metricsList;
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<MetricAnalyzer> values = new ArrayList<MetricAnalyzer>();
	private MetrixControl metrixController;
	private Button addMetricBut;
	private Button exportMetricBut;

	

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		
		metrixController = new MetrixControl(this);
		
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
					addMetricBut.setEnabled(true);
					exportMetricBut.setEnabled(true);
					metrixController.analyzeMetrics();

				} else if (combo.getText().equals("Current Project")) {
					addMetricBut.setEnabled(false);
					exportMetricBut.setEnabled(false);
					metrixController.analyzeProjectMetrics();
				}
			}
		});
		
		//button export
		exportMetricBut = new Button(viewArea, SWT.PUSH);
		exportMetricBut.setBounds(40, 50, 50, 20);
		exportMetricBut.setText("Export");
		  
		exportMetricBut.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				metrixController.exportMetricExtension();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		  
		  addMetricBut = new Button(viewArea, SWT.PUSH);
		  addMetricBut.setBounds(40, 50, 50, 20);
		  addMetricBut.setText("Add New Metric");
		  
		  addMetricBut.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				metrixController.newMetricExtension();
			}
		
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		tableTree = new TableTree(viewArea);
		tableTree.init("Metrix", "Value");
		metrixController.init();

		metrixController.setEditorServiceListener(combo.getText());
		
	}
	
	protected void updateTree(MetricAnalyzer metric) {
		tableTree.updateTable(metric);
	}
	
	protected void updatePackages(ArrayList<MetricAnalyzer> classesMap, ArrayList<String> packages) {
		tableTree.updatePackages(classesMap, packages);
	}

	public void addLineTree(String metricName, int calcNewMetric) {
		if (combo.getText().equals("Current File")) {
			tableTree.addNewMetric(metricName, calcNewMetric);
		}
	}
	
}
