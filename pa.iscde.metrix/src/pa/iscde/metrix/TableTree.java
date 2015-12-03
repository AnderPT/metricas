package pa.iscde.metrix;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class TableTree {
	
	private Tree tree;
	private String[] metrics = new String[] {"Number of Lines", "Number of Methods", "Number of Constructors"
			, "Number of Fields"};
	private ArrayList<TreeItem> listItems = new ArrayList<TreeItem>();

	public TableTree(ViewForm viewForm) {
		 tree = new Tree(viewForm, SWT.BORDER | SWT.FILL | SWT.FILL);
		 tree.setHeaderVisible(true);
		 
	}
	
	public void addColumns() {
		TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
	    column1.setText("Metric");
	    column1.setWidth(300);
	    TreeColumn column2 = new TreeColumn(tree, SWT.CENTER);
	    column2.setText("Value");
	    column2.setWidth(300);
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
		for (int i = 0; i < listItems.size(); i++) {
		    (listItems.get(i)).setText(new String[] { metrics[i], metric.getNumbMetric(metrics[i]) });
		}
	}

	

	

}
