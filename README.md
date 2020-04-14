# CallGraphConstruction

### The Call Graph can be generated for all types of Java Programs : 
* Simple Programs
* Programs with functions invoked from a different class
* Recursive Functions
* Higher Order Functions
* Lambda Function

### Prerequisite :
* JLEX 
* Graphviz
* CUP

### Steps for Execution :

	******************************* 
	*  Execute the shell command  *
	*	./run_script          *
	*******************************

* `cup declaration.cup` : It creates the files : Parser.java , Sym.java

* Generate the java file for declaration using jlex and complie the java file :

    `jlex declaration` :  generates declaration.java
    
    `javac declaration.java` : generates declaration.class , Main.class , Token.class

* Compile all java files
`javac declaration.java sym.java Main.java sym.java`  OR `javac *.java`

* `java Main`

* Store the ouput of the AST produced on the terminal in a file : `java Main > ast`

* Inverse the ast file and store it in another file 'output_ast' : `python rev.py`

* Read the 'output' file and create nodes for function call and callee : `python graph.py`

* Store this output in a graphviz file : `python graph.py`

* To create the call graph [ This is defined in the graph.py ]

gedit call_graph.gv (digraph name { nodes })

dot -Tps call_graph.gv -o call_graph.ps

parser.cup -> parses the code checks syntax correctness
scaner.lex -> splits into tokens
ast.java -> generates the ast 

java JLex.Main scaner.lex
cup parser.cup
javac *.java



