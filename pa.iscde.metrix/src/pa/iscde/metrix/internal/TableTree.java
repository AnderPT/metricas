package pa.iscde.metrix.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.google.common.collect.Multimap;

class TableTree {
	
	private Tree tree;
	private String[] metrics = new String[] {"Number of Lines", "Number of Methods", "Number of Constructors"
			, "Number of Fields", "Number of Comments", "Number of Characters" , "Number of Packages"};
	private ArrayList<TreeItem> listItems = new ArrayList<TreeItem>();
	private ArrayList<TreeItem> listPackages = new ArrayList<TreeItem>();
	private ArrayList<String> packages = new ArrayList<String>();


	protected TableTree(Composite viewArea) {
		 tree = new Tree(viewArea, SWT.BORDER | SWT.FILL | SWT.FILL);
		 tree.setHeaderVisible(true);
	}
	
	protected void init(String metricColumn, String valueColumn) {
		addColumn(metricColumn);
		addColumn(valueColumn);
		addItems();
	}
	
	private void addColumn(String name) {
		TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
	    column1.setText(name);
	    column1.setWidth(300);
	}
	
	private void addItems() {
		for (int i = 0; i < metrics.length ; i++) {
		      TreeItem item = new TreeItem(tree, SWT.NONE);
		      item.setText(new String[] { metrics[i], "" + 0 });
		      listItems.add(item);
		    }
	}
	
	protected void updateTable(MetricAnalyzer metric) {
		tree.clearAll(true);
		listItems.clear();
		try {
		tree.removeAll();
		} catch (SWTException e) {
			
		}
		addItems();
		HashMap< String, Integer> map = metric.getMetrics();
		int i = 0;
		for ( Map.Entry<String, Integer> entry : map.entrySet()) {
			(listItems.get(i)).setText(new String[] { entry.getKey(), entry.getValue() + "", });
			i++;
		}
//		for (int i = 0; i < map.size(); i++) {
//			(listItems.get(i)).setText(new String[] { metric.getInicialMetrics()[i], map.get(metric.getInicialMetrics()[i]) + "", });
//		}
		
	}

	protected void addSubtring(String path, String root) {
		if (root.equals("")) {
			System.out.println("*" + root + "*");
			packages.add(path);
			for (TreeItem ti : listItems) {
				TreeItem item = new TreeItem(ti, SWT.NONE);
				item.setText(new String[] {path, "0"});
				listPackages.add(item);
			}
		}
		if(packages.contains(root)) {
			for (TreeItem ti : listItems) {
				for(TreeItem t : listPackages) {
					if (t.getText(0).equals(root)) {
						TreeItem item = new TreeItem(ti, SWT.NONE);
						item.setText(new String[] {path, "0"});
						listPackages.add(item);
					}
				}
			}
			System.out.println("SIM: " + path);
		}
	}

	protected void updatePackages(ArrayList<MetricAnalyzer> map, ArrayList<String> packages) {
		tree.clearAll(true);
		listItems.clear();
		try {
			tree.removeAll();
		} catch (SWTException e) {}
		addItems();
		for (TreeItem ti : listItems) {
			for (String p : packages) {
				TreeItem packageItem = new TreeItem(ti, SWT.NONE);
				packageItem.setText(p);
				for (MetricAnalyzer classResult : map) {
					if (classResult.getExtensionPath().equals(p)) {
						TreeItem classItem = new TreeItem(packageItem, SWT.NONE);
						classItem.setText( new String[] { classResult.getClassName() , classResult.getNumbMetric(ti.getText()) + "" });
					}
				}
			}
		}
	}

	protected void addNewMetric(String name, int value) {
		 TreeItem item = new TreeItem(tree, SWT.NONE);
	      item.setText(new String[] {name, "" + value });
	      listItems.add(item);
	}

	

	

	

}
