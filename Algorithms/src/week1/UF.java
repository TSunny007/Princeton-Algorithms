package week1;


/* Union find is a data structure that tracks a set of elements partitioned into a number of disjoint
 (non-overlapping) subsets. It provides near-constant-time operations to add new sets, to merge existing sets,
  and to determine whether elements are in the same set.
  */

public interface UF {
    public void union(int p, int q);
    public boolean connected(int p, int q);
    public int find(int p);
    public int count();
}
