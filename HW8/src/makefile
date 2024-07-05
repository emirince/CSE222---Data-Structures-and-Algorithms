JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java SocialNetworkGraph.java Person.java

default: $(TARGET)

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $(SOURCES)

run: $(TARGET)
	java $(TARGET)

clean:
	rm -f *.class

cleandoc:
	rm -rf doc

.PHONY: clean cleandoc default run

doc:
	javadoc -d doc $(SOURCES)