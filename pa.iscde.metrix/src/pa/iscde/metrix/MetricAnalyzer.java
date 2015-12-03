package pa.iscde.metrix;

import org.eclipse.swt.widgets.List;

public class MetricAnalyzer {
	
	private int numbMethods;
	private int numbConstructors;
	private int numbFields;
	private int numbLines;
	

	public int getNumbMethods() {
		return numbMethods;
	}

	public void setNumbMethods(int numbMethods) {
		this.numbMethods = numbMethods;
	}

	public int getNumbConstructors() {
		return numbConstructors;
	}

	public void setNumbConstructors(int numbConstructors) {
		this.numbConstructors = numbConstructors;
	}

	public int getNumbFields() {
		return numbFields;
	}

	public void setNumbFields(int numbFields) {
		this.numbFields = numbFields;
	}

	public int getNumbLines() {
		return numbLines;
	}

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
		default:
			return null;
		}
	}


}
