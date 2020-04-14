f = open("output_ast","w+")


for lines in reversed(open("ast").readlines()):
	f.write(lines)

f.close()
	


