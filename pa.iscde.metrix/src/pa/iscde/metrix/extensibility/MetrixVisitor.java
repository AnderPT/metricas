package pa.iscde.metrix.extensibility;

import org.eclipse.jdt.core.dom.ASTVisitor;

public abstract class MetrixVisitor extends ASTVisitor{
	

	abstract double getResults();
	
}
