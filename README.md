# ClosestPair
Finds two closest (Euclidian) pair and prints. Input files contain multidimensional points in its lines. Every line contains 1 point whose coordinates are separeted by tab character. Program creates the file "input_file_name_output.txt" in the same directory that contains the closest two points with their line number in the original file.

## Method
Program reads the input file and creates an array of Point objects. Point objects hold the coordinates and index's of the input points. To find the closest pair there are couple of options. One of them is obvious, brute-force method: find every distance and select the minimum. However, this method has complexity of O(n^2). The algorithm that is used here is *Recursive Divide and Conquer*. Basically, the data gets recursively divided into two equal parts to find the closest pairs in those halves. 

However, this is not enough since there may be points closer to the boundaries. Therefore this algorithm also needs to check distances near the boundary. This algorithm works at complexity O(n*logn), similar to MergeSort.

## Compile & Run:
The main method takes 1 arguement: **filepath**. 

```
javac ClosestPair.java  
java ClosestPair filepath
```

Test files are written with JUnit5, they can be run with many methods:

* Recommended: Using an IDE, Intellij IDEA preferred.

* Using Gradle or Maven: [Instructions](https://junit.org/junit5/docs/current/user-guide/#running-tests-build) 

* JUnit Console Launcher: [Instructions](https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher) 
