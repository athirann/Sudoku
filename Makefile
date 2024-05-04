### VARIABLES ###
JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### ESSENTIAL RULES ###

Sudoku.class: Sudoku.java sudokuBesoin/SudokuGridPanel.class sudokuBesoin/SudokuValidator.class sudokuBesoin/SudokuGrid.class sudokuBesoin/SudokuFichierLis.class
	$(JC) $(JCFLAGS) Sudoku.java

sudokuBesoin/SudokuGridPanel.class: sudokuBesoin/SudokuGridPanel.java sudokuBesoin/SudokuValidator.class
	$(JC) $(JCFLAGS) sudokuBesoin/SudokuGridPanel.java

sudokuBesoin/SudokuValidator.class: sudokuBesoin/SudokuValidator.java sudokuBesoin/SudokuGrid.class
	$(JC) $(JCFLAGS) sudokuBesoin/SudokuValidator.java

sudokuBesoin/SudokuGrid.class: sudokuBesoin/SudokuGrid.java
	$(JC) $(JCFLAGS) sudokuBesoin/SudokuGrid.java

sudokuBesoin/SudokuFichierLis.class: sudokuBesoin/SudokuFichierLis.java
	$(JC) $(JCFLAGS) sudokuBesoin/SudokuFichierLis.java

### OPTIONAL RULES ###

run1: Sudoku.class
	$(JVM) $(JVMFLAGS) -cp .:sudokuBesoin Sudoku

run2: SudokuResolution.class
	$(JVM) $(JVMFLAGS) -cp .:sudokuBesoin SudokuResolution

SudokuResolution.class: SudokuResolution.java
	$(JC) $(JCFLAGS) SudokuResolution.java

clean:
	-rm -f *.class sudokuBesoin/*.class

mrproper: clean Sudoku.class

### PHONY TARGETS ###

.PHONY: run clean mrproper make1
