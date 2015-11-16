package pa.iscde.metrix;

import java.util.Map;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import pt.iscte.pidesco.extensibility.PidescoView;

public class MetrixView implements PidescoView {

	public MetrixView() {
	}

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {

        Table table = new Table(viewArea, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
 
        String[] titles = { "Metric Name", "Value"};
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).pack();
        }
        
        for (int i = 0 ; i<= 50 ; i++){
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText (0, "Package/Class/Method " +i);
            item.setText (2, String.valueOf(i));
            
        }
        
        for (int i=0; i<titles.length; i++) {
            table.getColumn (i).pack ();
        }    
        table.addListener(SWT.DefaultSelection, new Listener() {
				
			@Override
			public void handleEvent(Event event) {
			}
		});
        
        
        viewArea.pack ();
	}
	
	
}
