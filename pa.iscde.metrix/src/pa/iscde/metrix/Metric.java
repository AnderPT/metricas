package pa.iscde.metrix;

public class Metric {
	
	private String name;
	private int value;

	public Metric(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	

}
