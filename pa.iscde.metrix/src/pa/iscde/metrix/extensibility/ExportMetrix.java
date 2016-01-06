package pa.iscde.metrix.extensibility;

import java.io.File;
import java.util.HashMap;

/**
 * Interface que representa um conjunto de metodos a implementar para exportar
 * 
 * @author luislima
 *
 */

public interface ExportMetrix {
	
	
	
	
	/**
	 * Metodo para definir o tipo de exportação
	 * Não pode ser null
	 * @return o tipo de exportação
	 */
	public String setTypeExport();
	
	/**
	 * Metodo para definir o nome do ficheiro a exportar
	 * Não pode ser null
	 * @return o nome do file de exportação
	 */
	public String setNamedFile();
	
	/**
	 * Metodo para exportar 
	 * 
	 */
	public void exportFile(HashMap<String, Integer> hashMap);
	

}
