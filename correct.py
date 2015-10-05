import sys, os
for i in os.listdir(os.getcwd()):
    if i.startswith('my') or i.endswith('.py'):
        continue
    else:
        filename = i
        newfilename = "my"+i
        f = open(filename, 'r')
        newf = open(newfilename, 'w')
        check = False
        newline = ""
        seperator = ""
        for line in f:
            if check:
                try:
                    int(line.split()[0])
                    int(line.split()[1])
                except ValueError:
                    newline += "\n" + line
                    line = newline
                    check = False
                except IndexError:
                    check = True
                    seperator = line
            if ord(line[0]) == 12:
                check = True
                seperator = line
            else:
                if check:
                    newf.write(seperator)
                check = False
                newline = line
            if not check:
                newf.write(line)
        f.close()
        os.remove(os.path.abspath(i))
        newf.close()
print "done!"
