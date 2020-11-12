JFLAGS = -d
JC = javac
DESTINATION = ../target
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java $(JFLAGS) $(DESTINATION)

CLASSES = \
        com/iteso/game/*.java \
        com/iteso/game/entity/*.java \
		com/iteso/game/entity/enemy/*.java \
		com/iteso/game/entity/material/*.java \
		com/iteso/game/graphics/*.java \
		com/iteso/game/math/*.java \
		com/iteso/game/noise/*.java \
		com/iteso/game/states/*.java \
		com/iteso/game/tiles/blocks/*.java \
		com/iteso/game/tiles/*.java \
		com/iteso/game/ui/*.java \
		com/iteso/game/utils/*.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
