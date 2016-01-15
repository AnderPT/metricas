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
	 * Nesta funcao devera com o Hasmap de metricas criar um ficheiro
	 * exportando as metricas e os valores para um tipo de ficheiro que pretender
	 * @param hashMap - Hasmap com metricas
	 */
	public void exportFile(HashMap<String, Integer> hashMap);
	

}
