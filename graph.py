file = open("output_ast",'r+')
write_file = open("call_graph.gv","w+")
lines = file.readlines()
i=0
func_list = []
relation = []
print "digraph G {"
write_file.write("digraph G {")
for line in lines:
	i=i+1
	if((line == "|___Function Definition\n") or (line == "|___Function Definition (Type : void)\n")):
		current_node = lines[i][12:].rstrip()
		if(current_node not in func_list):
			func_list.append(current_node)
	
	if(line == "|__Function call \n"):
		callee_node = lines[i].rstrip()[12:]
		print current_node + " -> " + callee_node + ";"
		write_file.write(current_node + " -> " + callee_node + ";")
		if(callee_node not in func_list):
			func_list.append(callee_node)
		relation.append([current_node,callee_node])

	if(line == "|___ Object Method Call\n"):
		callee_node = lines[i+2].rstrip()[12:]
		print current_node + " -> " + callee_node + ";"
		write_file.write(current_node + " -> " + callee_node + ";")
		if(callee_node not in func_list):
			func_list.append(callee_node)
		relation.append([current_node,callee_node])

print "}"
write_file.write("}")

func_count = len(func_list)
print "No. of Functions : " + str(func_count)

matrix = [ [ 0 for i in range(func_count)] for j in range(func_count)]


# Print the matrix
print matrix
#Print the function relations with names
print relation
# Print the functions 
print func_list

degree = [0 for i in range(func_count)]

for i in range(len(relation)):
	for j in range(2):
		for k in range(func_count):
			if(relation[i][j]==func_list[k]):
				relation[i][j] = k
				degree[k] = degree[k]+1


# Print the function relation with numbers
#print relation

# Adjacenvy Matrix 
for i in range(func_count):
	for j in range(func_count):
		for k in relation:
			if([i, j] == k):
				matrix[i][j] = matrix[i][j]+1

print " Adjacency Matix : "

for i in range(func_count):
	print("	" + func_list[i]),
print
for i in range(func_count):
	print(func_list[i]) + "	",
	for j in range(func_count):
		print(str(matrix[i][j]) + "	"),
	print


# Degree matrix :: sum of indegree and outdegree 
degree_matrix = [ [ 0 for i in range(func_count)] for j in range(func_count)]

for i in range(func_count):
	degree_matrix[i][i] = degree[i]

print " Degree Matix : "

for i in range(func_count):
	print("	" + func_list[i]),
print
for i in range(func_count):
	print(func_list[i]) + "	",
	for j in range(func_count):
		print(str(degree_matrix[i][j]) + "	"),
	print

# Laplacian Matrix :: Difference of degree matrix and adjacency matrix

laplacian_matrix = [[0 for i in range(func_count)] for j in range(func_count)]

print " Laplacian Matrix : "
for i in range(func_count):
	print("	" + func_list[i]),
print

for i in range(func_count):
	print(func_list[i] + "	"),
	for j in range(func_count):
		laplacian_matrix[i][j] = degree_matrix[i][j] - matrix[i][j]
		print(str(laplacian_matrix[i][j]) + "	"),
	print