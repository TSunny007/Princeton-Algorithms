package assignments.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;
    private boolean[] grid;
    private WeightedQuickUnionUF uf;
    private int openSites;

    /*
     // create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        N = n;
        grid = new boolean[n*n+2];
        uf = new WeightedQuickUnionUF(n*n+2);
    }

    /*
    // open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        validateIndices(row, col);
        if (isOpen(row,col)) return;

        grid[index(row, col)] = true;

        if (row == 1) {
            uf.union(0, col);
        } else if (row == N) {
            uf.union( index(row,col), N*N+1);
        }

        // Checking top
        if (isValid(row-1,col)&& isOpen(row-1,col))
            uf.union(index(row-1,col),index(row,col));
        // Checking bottom
        if (isValid(row+1,col)&& isOpen(row+1,col))
            uf.union(index(row+1,col),index(row,col));
        // Checking left
        if (isValid(row,col-1)&& isOpen(row,col-1))
            uf.union(index(row,col-1),index(row,col));
        // Checking right
        if (isValid(row,col+1)&& isOpen(row,col+1))
            uf.union(index(row,col+1),index(row,col));
        openSites++;
    }

    private int index(int row, int col) {
        validateIndices(row, col);
        return (N * (row - 1) + col);
    }

    private boolean isValid(int i, int j) {
        i -= 1;
        j -= 1;
        return i >= 0 && j >= 0 && i < N && j < N;
    }
    /*
    // is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return grid[index(row,col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col)
    {
        validateIndices(row, col);
        return uf.connected(0, index(row,col));
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

    private void validateIndices(int i, int j) {
        if (!isValid(i, j)) throw new IllegalArgumentException("Invalid indices provided.");
    }
}
