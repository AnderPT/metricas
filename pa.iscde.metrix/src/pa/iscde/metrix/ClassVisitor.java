package pa.iscde.metrix;

import java.io.File;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class ClassVisitor extends ASTVisitor {
	
	private MetricAnalyzer view;
	
	public ClassVisitor(MetricAnalyzer view) {
		this.view = view;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		System.out.println(node.toString());
		view.addField();
		return super.visit(node);
	}
	

	@Override
	public boolean visit(MethodDeclaration node) {
		if (node.isConstructor()) {
			view.addConstructor();
		}
		view.addMethod();
		return super.visit(node);
	}
	
	@Override
	public boolean visit(LineComment node) {
		System.out.println("AAAAAA");
		return super.visit(node);
	}

}
