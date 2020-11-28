JDFLAGS = -d
JCPFLAG = -classpath
JC = javac
CLASSPATH = ./target
DESTINATION = ./target
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JCPFLAG) $(CLASSPATH) $*.java $(JDFLAGS) $(DESTINATION)

CLASSES = \
		./src/iteso/entity/*.java \
		./src/iteso/calendar/*.java \
		./src/iteso/statistics/*.java \
		./src/iteso/game/*.java
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class