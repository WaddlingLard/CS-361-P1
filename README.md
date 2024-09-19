# Project 1: Deterministic Finite Automata

* Author: Brian Wu ,Max Ma
* Class: CS361 Section 1
* Semester: Fall 2024 

## Overview

This Java application displays an fully functioning Determinstic Finite Automata that implements fa.dfa.DFAInterface which extends fa.dfa.FAInterface, then we apply test-based development using JUnit test cases to apply 18 given test cases.

## Reflection

Overall it is good project, we contribute equally, and we learn how to store DFA by using Linked Hashset because it is ordered, because we used Hashset before it causes a couple tests failing, we had fixed by the end of it. 

- What worked well and what was a struggle? 
  - team communication is well but need to be more constructive on things we stay on the same page, one of biggest struggle would be we overlook small things like how should we store components in DFA. 
- What concepts still aren't quite clear?
  - there are still room for better understanding of Java Collections API available in Java’s java.util package that 
 provides us with  used data structures such as sets, maps, lists (sequences) that represent different collections of objects.
- What techniques did you use to make your code easy to debug and modify?
  - using IntellJ, and creating .jar testing classes to test the code.
- What would you change about your design process?
  - better structure, more clear path.
- If you could go back in time, what would you tell yourself about doing this project?
  - Be really careful what the document is asking you to do. 

## Compiling and Using

To compile, execute the following command in the main project directory:
```
$ javac -cp .:/usr/share/java/junit.jar ./test/dfa/DFATest.java
```

Run the compiled class with the command:
```
$ java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar
org.junit.runner.JUnitCore test.dfa.DFATest
```

## Sources used

- https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashSet.html 
