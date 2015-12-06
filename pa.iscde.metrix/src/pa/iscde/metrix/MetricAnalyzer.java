package pa.iscde.metrix;

import org.eclipse.swt.widgets.List;

public class MetricAnalyzer {
	
	private int numbMethods;
	private int numbConstructors;
	private int numbFields;
	private int numbLines;
	private int numbComments;
	
	public void setNumbLines(int numbLines) {
		this.numbLines = numbLines;
	}
	
	public void addMethod() {
		numbMethods++;
	}
	
	public void addField() {
		numbFields++;
	}
	
	public void addConstructor() {
		numbConstructors++;
	}
	
	public void addLine() {
		numbLines++;
	}
	
	public void addComment() {
		numbComments++;
	}

	public String getNumbMetric(String string) {
		switch (string) {
		case "Number of Lines":
			return "" + numbLines;
		case "Number of Methods":
			return "" + numbMethods;
		case "Number of Constructors":
			return "" + numbConstructors;
		case "Number of Fields":
			return "" + numbFields;
		case "Number of Comments":
			return "" + numbComments;
		default:
			return null;
		}
	}

	


}
