# Specification
Here is the link of assignment specification:  
http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
  
# My Solution
This assignment is a connectivity problem which requires us to use `UnionFind` to sovle it.
In the course, professor give a very useful tip that we add two additional node, let's call them 'top' and 'bottom',
and union top with every node in first row, bottom with every node in last row.
