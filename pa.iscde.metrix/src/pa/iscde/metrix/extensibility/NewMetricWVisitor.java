package pa.iscde.metrix.extensibility;

/**
 * Representa uma nova metrica que pode ser adicionada � lista de metricas
 * Esta classe � accionada quando clicado no but�o New Metric
 * N�o tem construtor
 * 
 * @author Andre Carvalho
 *
 */
public interface NewMetricWVisitor {
	
	/**
	 * Metodo para decidir o nome da nova metrica
	 * N�o deve devolver um null uma string em branco. 
	 * @return nome da metrica
	 */
	public String metricName();
	
	/**
	 * Metodo para calcular o valor da m�trica eguarda-lo num atributo result
	 * Consiste em criar um MetrixVisitor que extende o ASTVisitor, usar uma fun��o visit
	 * e guardar o valor no resultado
	 * @return MetrixVisitor
	 */
	public MetrixVisitor calcMetricByVisitor();

}
