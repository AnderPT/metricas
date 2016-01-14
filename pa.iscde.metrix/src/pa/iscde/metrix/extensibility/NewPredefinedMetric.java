package pa.iscde.metrix.extensibility;

import java.util.ArrayList;

/**
 * Representa uma nova metrica que pode ser adicionada � lista de metricas
 * N�o tem construtor
 * 
 * @author Andre Carvalho
 *
 */
public interface NewPredefinedMetric {
	

	/**
	 * Metodo para escolher quais as m�tricas escolhidas para o c�lculo 
	 * da nova m�trica. 
	 * Dever� retornar uma array com pelo menos size > 1
	 * @return 
	 */
	public ArrayList<DefaultMetrics> targetMetrics(DefaultMetrics[] possibleMetrics);

}
