package pa.iscde.metrix.internal;

public class Metric {
	
	private String name;
	private int value;

	
	/**
	 * 
	 * @param name o nome da metrica
	 * @param value o valor da metrica
	 */
	
	public Metric(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * 
	 * @return name
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return value
	 */
	
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @param value
	 */
	
	public void setValue(int value) {
		this.value = value;
	}
	
	

}
