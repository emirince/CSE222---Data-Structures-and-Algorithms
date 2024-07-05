JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java AVLTree.java GUIVisualization.java Stock.java StockDataManager.java


default: $(TARGET) clean

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^
	java -Xint $(TARGET) random_input.txt

clean:
	rm -f *.class

.PHONY: clean default

doc:
	javadoc -d doc $(SOURCES)