package pa.iscde.metrix.extensibility;

/**
 * Representa uma nova metrica que pode ser adicionada á lista de metricas
 * Esta classe é accionada quando clicado no butão New Metric
 * Não tem construtor
 * 
 * @author Andre Carvalho
 *
 */
public interface NewMetricWVisitor {
	
	/**
	 * Metodo para decidir o nome da nova metrica
	 * Não deve devolver um null uma string em branco. 
	 * @return nome da metrica
	 */
	public String metricName();
	
	/**
	 * Metodo para calcular o valor da métrica eguarda-lo num atributo result
	 * Consiste em criar um MetrixVisitor que extende o ASTVisitor, usar uma função visit
	 * e guardar o valor no resultado
	 * @return MetrixVisitor
	 */
	public MetrixVisitor calcMetricByVisitor();

}
