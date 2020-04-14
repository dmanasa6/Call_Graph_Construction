import java.io.*;
public class Main
{
	public static void main(String[] args)throws Exception
	{
		Node<String> root = new Node<>("root");
		//parser p = new parser(new Yylex(new FileReader("try")));
		parser p = new parser(new Yylex(new FileReader("ackermann")));
		p.parse();
		//printTree(root," ");
	}
}
