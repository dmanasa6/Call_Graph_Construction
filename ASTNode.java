import java.io.*;
import java.util.*;

public class ASTNode { 
	static Node<String> root = new Node<>("root");
	static <T> void printTree(Node<T> node, String appender) 
	{
		System.out.println(appender + node.getData());
		node.getChildren().forEach(each ->  printTree(each, appender + appender));
	}
}

class StartNode extends ASTNode
{
	private ProgramNode p;
	public StartNode(ProgramNode p)
	{
		System.out.println("---- Program ----");
		this.p = p; 
	}
}
class ProgramNode extends ASTNode
{
	private StmtNode s;
	public ProgramNode(StmtNode s)
	{
		this.s = s;
	}
}

class StmtNode extends ASTNode
{
	private VarDeclNode vd;
	private FuncDeclNode fd;
	private FuncDefNode fdef;
	private ClasDeclNode cd;
	private FunCallNode fc;
	private ObjectCreatNode oc;
	public StmtNode(VarDeclNode vd)
	{
		System.out.println("|____ Statement ");
		System.out.println();
		this.vd = vd;
	}
	public StmtNode(FuncDeclNode fd)
	{
		System.out.println("|____ Statement ");
		System.out.println();
		this.fd = fd;
	}
	public StmtNode(FuncDefNode fdef)
	{
		System.out.println("|____ Statement ");
		System.out.println();
		this.fdef = fdef;
	}
	public StmtNode(ClasDeclNode cd)
	{
		System.out.println("|____ Statement ");
		System.out.println();
		this.cd = cd;
		printTree(root," ");
	}
	public StmtNode(FunCallNode fc)
	{
		System.out.println("|____ Statement ");
		System.out.println();
		this.fc = fc;
	}
	public StmtNode(ObjectCreatNode oc)
	{
		System.out.println("|____ Statement ");
		System.out.println();
		this.oc = oc;
	}
}

abstract class DeclNode extends ASTNode
{}

class ClasDeclNode extends ASTNode
{
	private IdNode id;
	public ClasDeclNode(IdNode id,ClasModNode cm)
	{
		System.out.println("|---Class Declaration");
		this.cm = cm;
		this.id = id;
	}
	private ClasModNode cm;
}
class VarDeclNode extends DeclNode
{
	private TypeNode type;
	private IdNode id;
	private ModifierNode m;
	public VarDeclNode(TypeNode type, IdNode id)
	{
		System.out.println("|__Variable Declaration");
		this.type = type;
		this.id = id;
	}
	public VarDeclNode(ModifierNode m,TypeNode type, IdNode id)
	{
		System.out.println("|__Variable Declaration");
		this.m = m;
		this.type = type;
		this.id = id;
	}
}

abstract class CallGraph extends ASTNode {}

class FuncDeclNode extends DeclNode
{
	private TypeNode type;
	private IdNode id;
	private ParamNode parameters;
	public FuncDeclNode(TypeNode type,IdNode id, ParamNode parameters)
	{
		System.out.println("|__Function Declaration");
		this.type = type;
		this.id = id;
		this.parameters = parameters;
	}
	public FuncDeclNode(IdNode id, ParamNode parameters)
	{
		System.out.println("|__Function Declaration (Type : void)");
		this.id = id;
		this.parameters = parameters;
	}
}

class ExprNode extends ASTNode
{
	private OperNode operator;
	private IdNode id1;
	private IdNode id2;
	private String l_bracket;
	private String r_bracket;
	private ExprNode expr;
	private Integer n1;
	private Integer n2;
	public ExprNode(IdNode id1,OperNode operator,IdNode id2)
	{
		this.id1 = id1;
		this.id2 = id2;
		this.operator = operator;
		System.out.println("|_Arthimetic Operation");
	}
	public ExprNode(String l_bracket,ExprNode expr,String r_bracket)
	{
		this.l_bracket = l_bracket;
		this.r_bracket = r_bracket;
		this.expr = expr;
		System.out.println("|__Sub arthimetic expression");
	}
	public ExprNode(IdNode id1,OperNode operator,ExprNode expr)
	{
		this.id1 = id1;
		this.operator = operator;
		this.expr = expr;
		System.out.println("|___Arthimetic Expression");
	}
	public ExprNode(Integer n1,OperNode operator,Integer n2)
	{
		this.n1 = n1;
		this.n2 = n2;
		this.operator = operator;
		System.out.println("  Left Number : "+n1);
		System.out.println("  Right Number : "+n2);
		System.out.println("|_Arthimetic Expression");
	}
	public ExprNode(Integer n1,OperNode operator,ExprNode expr)
	{
		this.n1 = n1;
		this.operator = operator;
		this.expr = expr;
		System.out.println(" Left Number : "+n1);
		System.out.println("|__Arthimetic Expression");
	}
	public ExprNode(IdNode id1,OperNode operator,Integer n1)
	{
		this.id1 = id1;
		this.n1  = n1;
		this.operator = operator;
		System.out.println(" Number : "+n1);
		System.out.println("|_Arthimetic Operation");
	}
}

class OperandNode extends ASTNode
{
	private IdNode id;
	private ExprNode expr;
	private Integer n;
	private FunCallNode fc;
	public OperandNode(IdNode id)
	{
		this.id = id;
		System.out.println("|_Operand : ");
	}
	public OperandNode(ExprNode expr)
	{
		this.expr = expr;
		System.out.println("|_Operand : ");
	}
	public OperandNode(Integer n)
	{
		this.n = n;
		System.out.println("Number : "+n);
		System.out.println("|_Operand : ");
	}
	public OperandNode(FunCallNode fc)
	{
		this.fc = fc;
		System.out.println("Function : ");
		System.out.println("|_Operand : ");
	}
}

abstract class OperNode extends ASTNode {}
class AddNode extends OperNode
{
	private String op;
	public AddNode(String op)
	{
		this.op = op;
		System.out.println("Addition '+'");
	}
}
class SubNode extends OperNode
{
	private String op;
	public SubNode(String op)
	{
		this.op = op;
		System.out.println("Substraction '-'");
	}
}
class MultNode extends OperNode
{
	private String op;
	public MultNode(String op)
	{
		this.op = op;
		System.out.println("Multiplication '*'");
	}
}
class DivNode extends OperNode
{
	private String op;
	public DivNode(String op)
	{
		this.op = op;
		System.out.println("Division '/'");
	}
}
class ModNode extends OperNode
{
	private String op;
	public ModNode(String op)
	{
		this.op = op;
		System.out.println("Mod '%'");
	}
}
class EqNode extends OperNode
{
	private String op;
	public EqNode(String op)
	{
		this.op = op;
		System.out.println("Equal '='");
	}
}

class BoolExprNode extends ASTNode
{
	private OperandNode p1;
	private BoolOperNode op;
	private OperandNode p2;
	private FunCallNode fc;
	private ObjFuncCall ofc;
	public BoolExprNode(OperandNode p1, BoolOperNode op,OperandNode p2)
	{
		this.p1 = p1;
		this.op = op;
		this.p2 = p2;
		System.out.println("|__Boolean Arthimetic Condition");
		System.out.println();
	}
	public BoolExprNode(FunCallNode fc)
	{
		this.fc = fc;
		System.out.println("|__Boolean Function call");
	}
	public BoolExprNode(ObjFuncCall ofc)
	{
		this.ofc = ofc;
		System.out.println("|__Boolean Function call by an Object");
	}
}

abstract class BoolOperNode extends ASTNode {}

class LessThanNode extends BoolOperNode
{
	private String op;
	public LessThanNode(String op)
	{
		System.out.println("Less than '<'");
		this.op = op;
	}
}
class GreatThanNode extends BoolOperNode
{
	private String op;
	public GreatThanNode(String op)
	{
		System.out.println("Greater than '>'");
		this.op = op;
	}
}
class EqualsNode extends BoolOperNode
{
	private String op;
	public EqualsNode(String op)
	{
		System.out.println("Equal to '=='");
		this.op = op;
	}
}
class GreatEqlNode extends BoolOperNode
{
	private String op;
	public GreatEqlNode(String op)
	{
		System.out.println("Greater than or Equal to '>='");
		this.op = op;
	}
}
class LessEqlNode extends BoolOperNode
{
	private String op;
	public LessEqlNode(String op)
	{
		System.out.println("Less than or Equal to '<='");
		this.op = op;
	}
}
class NotEqlNode extends BoolOperNode
{
	private String op;
	public NotEqlNode(String op)
	{
		System.out.println("Not Equal to '!='");
		this.op = op;
	}
}

class PrintStmtNode extends ASTNode
{
	private String print;
	private String stmt;
	private IdNode id;
	public PrintStmtNode(String print,IdNode id)
	{
		this.print = print;
		this.id = id;
		System.out.println("|_Print Statement");
		System.out.println();
	}
	public PrintStmtNode(String print,String stmt)
	{
		this.print = print;
		this.stmt = stmt;
		System.out.println(" String : "+ stmt);
		System.out.println("|_Print Statement");
		System.out.println();
	}
}

class AssgnNode extends ASTNode
{
	private IdNode id1;
	private IdNode id2;
	private String eq;
	private ExprNode expr;
	private Integer n;
	private FunCallNode fc;
	private ObjFuncCall ofc;
	public AssgnNode(IdNode id1,String eq,IdNode id2)
	{
		this.id1 = id1;
		this.id2 = id2;
		this.eq = eq;
		System.out.println("|_Assignment Statement '='");
		System.out.println();
	}
	public AssgnNode(IdNode id1,String eq,ExprNode expr)
	{
		this.id1 = id1;
		this.eq = eq;
		this.expr = expr;
		System.out.println("|___Assignment Statement '='");
		System.out.println();
	}
	public AssgnNode(IdNode id1,String eq,Integer n)
	{
		this.id1 = id1;
		this.eq = eq;
		this.n = n;
		System.out.println("Number : "+n);
		System.out.println("|___Assignment Statement '='");
		System.out.println();
	}
	public AssgnNode(IdNode id1,String eq,FunCallNode fc)
	{
		this.id1 = id1;
		this.eq = eq;
		this.fc = fc;
		System.out.println("Function : ");
		System.out.println("|___Assignment Statement '='");
		System.out.println();
	}
	public AssgnNode(IdNode id1,String eq,ObjFuncCall fc)
	{
		this.id1 = id1;
		this.eq = eq;
		this.ofc = ofc;
		System.out.println("Function : ");
		System.out.println("|___Assignment Statement '='");
		System.out.println();
	}

}

class RetStmt extends ASTNode
{
	private OperandNode op;
	public RetStmt(OperandNode op)
	{
		this.op = op;
		System.out.println("|___Return value of function");
	}
}

class FuncDefNode extends ASTNode
{
	private TypeNode type;
	private IdNode id;
	private ParamNode parameters;
	private RetStmt ret;
	public FuncDefNode(TypeNode type, IdNode id, ParamNode parameters)
	{
		System.out.println("|___Function Definition");
		this.type = type;
		this.id = id;
		this.parameters = parameters;
	}
	public FuncDefNode(IdNode id, ParamNode parameters)
	{
		System.out.println("|___Function Definition (Type : void)");
		this.id = id;
		this.parameters = parameters;
	}
}

class FunCallNode extends ASTNode
{
	private IdNode id;
	private ArgNode param;
	private FunCallNode fc;
	public FunCallNode(IdNode id, ArgNode param)
	{
		System.out.println("|__Function call ");
		this.id = id;
		this.param = param;
	}
	public FunCallNode(IdNode id,FunCallNode fc)
	{
		System.out.println("|--Higher Order Function call");
		this.id = id;
		this.fc = fc;
	}
}

class ArgNode extends ASTNode
{
	private ArgumentNode a;
	private ArgNode para;
	private FunCallNode fc;
	public ArgNode(ArgumentNode a,ArgNode para)
	{
		System.out.println("|_Arguments ");
		this.a = a;
		this.para = para;
	}
	public ArgNode(ArgumentNode a)
	{
		System.out.println("|_Arguments ");
		this.a = a;
	}
	public ArgNode(ArgumentNode a,FunCallNode fc)
	{
		System.out.println("|__Arguments");
		System.out.println("|--Higher Order Function");
		this.fc = fc;
		this.a = a;
	}
	public ArgNode()
	{
		System.out.println("|_No Arguments");
	}
}

class ArgumentNode extends ASTNode
{
	private IdNode id;
	private ExprNode expr;
	private Integer n;
	public ArgumentNode(IdNode id)
	{
		this.id = id;
	}
	public ArgumentNode(ExprNode expr)
	{
		this.expr = expr;
	}
	public ArgumentNode(Integer n)
	{
		this.n = n;
		System.out.println("Number : "+n);
	}
}


abstract class TypeNode extends ASTNode
{}

class IntNode extends TypeNode
{
	public IntNode()
	{
		System.out.println(" Type : int");
	}
}

class BooleanNode extends TypeNode
{
	public BooleanNode()
	{
		System.out.println(" Type : Boolean");		
	}
}

class StringNode extends TypeNode
{
	public StringNode()
	{
		System.out.println(" Type : String");
	}
}
class OtherNode extends TypeNode
{
	private IdNode i;
	private IdNode i2;
	public OtherNode(IdNode i)
	{
		System.out.println(" Type : Defined type");
		this.i = i;
	}
	public OtherNode(IdNode i, IdNode i2)
	{
		System.out.println(" Type : Defined type");
		this.i = i;
		this.i2 = i2;
	}
}

class IdNode extends ASTNode
{
	public IdNode(String strVal)
	{
		this.strVal = strVal;
		System.out.println(" ID value : " + strVal);
	}
	private String strVal;
}

abstract class ClasModNode extends ASTNode
{}

class PublNode extends ClasModNode
{
	public PublNode()
	{
		System.out.println(" Modifier : public");
	}
}

class AbstNode extends ClasModNode
{
	public AbstNode()
	{
		System.out.println(" Modifier : abstract");
	}
}

class FinNode extends ClasModNode
{
	public FinNode()
	{
		System.out.println(" Modifier : final");
	}
}

abstract class ModifierNode extends ASTNode
{}

class PublicNode extends ModifierNode
{
	public PublicNode()
	{
		System.out.println(" Modifier : Public");
	}
}
class PrivateNode extends ModifierNode
{
	public PrivateNode()
	{
		System.out.println(" Modifier : Private");
	}
}
class StaticNode extends ModifierNode
{
	public StaticNode()
	{
		System.out.println(" Modifier : Static");
	}
}

class ParamNode extends ASTNode
{
	private TypeNode type;
	private IdNode id;
	private ParamNode parameters;
	public ParamNode(TypeNode type, IdNode id, ParamNode parameters)
	{
		System.out.println("|_Parameter");
		this.type = type;
		this.id = id;
		this.parameters = parameters;
	}
	public ParamNode(TypeNode type, IdNode id)
	{
		System.out.println("|_Parameter"); 
		this.type = type;
		this.id = id;
	}
	public ParamNode()
	{
		System.out.println("|_No parameters (ast)");
	}
}

class IfStmtNode extends ASTNode
{
	private ProgramNode p1;
	private BoolExprNode bexpr;
	private ProgramNode p2;
	public IfStmtNode(BoolExprNode bexpr,ProgramNode p1)
	{
		this.bexpr = bexpr;
		this.p1 =p1;
		System.out.println("|_____True Condition 'IF' ");
		System.out.println();
		//Node<String> node21 = node2.addChild(new Node<String>("node21"));
	}
	public IfStmtNode(ProgramNode p2)
	{
		this.p2 =p2;
		System.out.println("|_____False Condition 'ELSE' ");
		System.out.println();
	}
}

class ObjectCreatNode extends ASTNode
{
	private TypeNode t;
	private IdNode i2;
	private IdNode i3;
	public ObjectCreatNode(TypeNode t, IdNode i2, IdNode i3)
	{
		this.t = t;
		this.i2 = i2;
		this.i3 =i3;
		System.out.println("|_Class name : ");
		System.out.println("|_Object name : ");
		System.out.println("|___Object Creation");
	}
	public ObjectCreatNode(IdNode i2, IdNode i3)
	{
		this.i2 = i2;
		this.i3 =i3;
		System.out.println("|_Class name : ");
		System.out.println("|_Object name : ");
		System.out.println("|___Object Creation");
	}
}

class ObjFuncCall extends ASTNode
{
	private IdNode i1;
	private IdNode i2;
	private ArgNode a;
	public ObjFuncCall(IdNode i1, IdNode i2,ArgNode a)
	{
		this.i1 = i1;
		this.i2 = i2;
		this.a = a;
		System.out.println("|_ Object : ");
		System.out.println("|_ Method : ");
		System.out.println("|___ Object Method Call");
	}
}

abstract class LoopsNode extends ASTNode
{}

abstract class IncDecNode extends ASTNode
{}

class IncNode extends IncDecNode
{
	public IncNode()
	{
		System.out.println("Increment '++' ");
	}
}
class DecNode extends IncDecNode
{
	public DecNode()
	{
		System.out.println("Decrement '--' ");
	}
}

class ForNode extends LoopsNode
{
	private IdNode i1;
	private IdNode i2;
	private IdNode i3;
	private AssgnNode a;
	private BoolOperNode op;
	private IncDecNode id;
	private ProgramNode p;
	public ForNode(AssgnNode a, IdNode i1, BoolOperNode op, IdNode i2, IdNode i3, IncDecNode id, ProgramNode p)
	{
		System.out.println("|____For Loop");
	}

}
