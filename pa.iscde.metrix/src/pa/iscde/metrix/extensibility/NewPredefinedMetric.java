package pa.iscde.metrix.extensibility;

import java.util.ArrayList;

/**
 * Representa uma nova metrica que pode ser adicionada á lista de metricas
 * Não tem construtor
 * 
 * @author Andre Carvalho
 *
 */
public interface NewPredefinedMetric {
	

	/**
	 * Metodo para escolher quais as métricas escolhidas para o cálculo 
	 * da nova métrica. 
	 * Deverá retornar uma array com pelo menos size > 1
	 * @return 
	 */
	public ArrayList<DefaultMetrics> targetMetrics(DefaultMetrics[] possibleMetrics);

}
