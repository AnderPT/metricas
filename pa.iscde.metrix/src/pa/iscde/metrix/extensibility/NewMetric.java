package pa.iscde.metrix.extensibility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representa uma nova metrica que pode ser adicionada á lista de metricas
 * Esta classe é accionada quando clicado no butão New Metric
 * Não tem construtor
 * 
 * @author Andre Carvalho
 *
 */
public interface NewMetric {
	
	/**
	 * Metodo para decidir o nome da nova metrica
	 * Não deve devolver um null uma string em branco. 
	 * @return nome da metrica
	 */
	public String metricName();
	
	/**
	 * Escolher o tipo de cálculo a ser usado entre metricas
	 * 
	 * @return Deve retornar um tipo de uma nova metrica
	 */
	public TypeNewMetric typeMetric();
	
	
	/**
	 * Metodo dado um Hash Map com as metricas e valores, podera calcular
	 * de forma que entender o valor da nova metrica.
	 * @return deve retornar o valor da nova metrica
	 */
	public double calcNewMetric (HashMap<String, Integer> metrics );
	


}
