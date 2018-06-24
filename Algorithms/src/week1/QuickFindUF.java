package week1;

public class QuickFindUF implements UF {
    private int[] id;
    private int count;

    public QuickFindUF(int n) {
     id = new int[n];
     for (int i = 0; i < n; i++) {
         id[i] = i;
     }
     count = n;
    }

    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        if (pid != qid) {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pid) {
                    id[i] = qid;
                }
            }
            count--;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public int count() {
        return count;
        //return Arrays.stream(id).boxed().collect(Collectors.toSet()).size();
    }
}
