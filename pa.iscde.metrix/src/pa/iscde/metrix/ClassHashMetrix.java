package pa.iscde.metrix;

import java.util.HashMap;


/**
 * 
 * 
 */

public class ClassHashMetrix {
	
	private HashMap<String, Integer > hmap = new HashMap<String, Integer>();
	
	
	/**
	 * Método 
	 */
	
	public void init() {
		
				
		for(int i = 1; i < 6; i++)
		      hmap.put("Metrica "+i, i);
	}

	
	/**
	 * 
	 * @return hmap
	 */
	
	public HashMap<String, Integer> getHmap() {
		return hmap;
	}

	
	
	  
	
	
  
	

}
