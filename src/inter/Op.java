package inter;

import lexer.Token;
import symbols.*;


/**
 * @author sin
 * 赋值表达式
 */
public class Op extends Expr {
	public Op(Token tok,Type p) {
		super(tok,p);
	}
	public Expr reduce() {
		Expr x=gen();
		Temp t=new Temp(type);
		emit(t.toString()+" = "+x.toString());
		return t;
	}
}
