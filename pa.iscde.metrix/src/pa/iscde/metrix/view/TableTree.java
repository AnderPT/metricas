package pa.iscde.metrix.view;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import pa.iscde.metrix.MetricAnalyzer;

public class TableTree {
	
	private Tree tree;
	private String[] metrics = new String[] {"Number of Lines", "Number of Methods", "Number of Constructors"
			, "Number of Fields", "Number of Comments", "Number of Characters" , "Number of Packages"};
	private ArrayList<TreeItem> listItems = new ArrayList<TreeItem>();

	public TableTree(Composite viewArea) {
		 tree = new Tree(viewArea, SWT.BORDER | SWT.FILL | SWT.FILL);
		 tree.setHeaderVisible(true);
	}
	
	public void addColumn(String name) {
		TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
	    column1.setText(name);
	    column1.setWidth(300);
	}
	
	public void addItems() {
		for (int i = 0; i < metrics.length ; i++) {
		      TreeItem item = new TreeItem(tree, SWT.NONE);
		      item.setText(new String[] { metrics[i], "" + 0 });
		      listItems.add(item);
		      
		     /* for (int j = 0; j < 4; j++) {
		        TreeItem subItem = new TreeItem(item, SWT.NONE);
		        subItem.setText(new String[] { "subitem " + j, "jklmnop", "qrs" });
		        for (int k = 0; k < 4; k++) {
		          TreeItem subsubItem = new TreeItem(subItem, SWT.NONE);
		          subsubItem.setText(new String[] { "subsubitem " + k, "tuv", "wxyz" });
		        }
		      } */
		    }
	}
	
	public Control getTree() {
		return tree;
	}

	public void updateTable(MetricAnalyzer metric) {
		tree.clearAll(true);
		HashMap< String, Integer> map = metric.getMetrics();
		for (int i = 0; i < map.size(); i++) {
			(listItems.get(i)).setText(new String[] { metric.getInicialMetrics()[i], map.get(metric.getInicialMetrics()[i]) + "", });
		}
	}

	

	

}
