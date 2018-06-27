package assignments.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    final int N;
    boolean[][] grid;
    WeightedQuickUnionUF uf;
    int openSites;

    /*
     // create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        N = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n*n+2);
    }

    /*
    // open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        if (isOpen(row,col)) return;

        int matrixRow = row-1;
        int matrixCol = col-1;

        grid[matrixRow][matrixCol] = true;

        if (row == 1) {
            uf.union(0, col);
        } else if (row == N) {
            uf.union( N*(N-1)+col, N*N);
        }

        // Checking top
        if (matrixRow > 0 && grid[matrixRow-1][matrixCol]) uf.union((matrixRow-1)*N+col,(matrixRow)*N+col);
        // Checking bottom
        if (matrixRow < N-1 && grid[matrixRow+1][matrixCol]) uf.union((matrixRow)*N+col,(matrixRow+1)*N+col);
        // Checking left
        if (matrixCol > 0 && grid[matrixRow][matrixCol-1]) uf.union(matrixRow*N+col-1,matrixRow*N+col);
        // Checking right
        if (matrixCol < N-1 && grid[matrixRow][matrixCol+1]) uf.union(matrixRow*N+col,matrixRow*N+col+1);

        openSites++;
    }

    /*
    // is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        return grid[row-1][col-1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col)
    {
        return uf.connected(0,(row-1)*N+col);
    }

    /*
     // number of open sites
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /*
     // does the system percolate?
     */
    public boolean percolates() {
        return uf.connected(0, N*N+1);
    }

}
