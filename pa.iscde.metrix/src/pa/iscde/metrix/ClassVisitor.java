package pa.iscde.metrix;

import java.io.File;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;

public class ClassVisitor extends ASTVisitor {
	
	private MetricAnalyzer view;
	
	
	@Override
	public boolean visit(BlockComment node) {
		System.out.println("AAAA");
		int start = node.getStartPosition();
		int end = start + node.getLength();
		System.out.println(start + "  " + end);
		view.addComment();
		return true;
	}

	public ClassVisitor(MetricAnalyzer view) {
		this.view = view;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
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
	public boolean visit(CharacterLiteral node) {
		
		//NAO FUNCIONA
		

	
		
		view.addCharacters();
		return super.visit(node);
	}
	@Override
	public boolean visit(PackageDeclaration node) {
		view.addPackages();
		return super.visit(node);
	}


}
