# ClosestPair
Finds two closest (Euclidian) pair and prints. Input files contain multidimensional points in its lines. Every line contains 1 point whose coordinates are separeted by tab character. Program creates the file "output.txt" that contains the closest two points with their line number in the original file.

## Method
Program reads the input file and maps the points into 2D double array. It is assumed that file contains only numbers, any other input will give an exception. From this array while calculating distances, it also does a search with O(n^2) at the worst scenario in total. It prints the results into a .txt file. The coordinates are rounded in the output.

## Compile & Run:
The main method takes 1 arguement: **filepath**. 

```
javac ClosestPair.java  
java ClosestPair filepath
```
