import java.util.Scanner;

public class WeightedUF {
    private int id[];       // parent link (site indexed)
    private int sz[];      // size of components for roots (site indexed)
    private int count;    // number of components

     public WeightedUF(int N) {
         count = N;
         id = new int[N];
         for (int i = 0; i < id.length; i++)
             id[i] = i;
         sz = new int[N];
         for (int i = 0; i < id.length; i++)
             sz[i] = i;
     }

     public int count() {
         return count;
     }

     public boolean connected(int p, int q) {
         return find(p) == find(q);
     }

     private int find(int p) {
         // Follow the links to find a root
         while (p != id[p]) {
             // id[p] = id[id[p]];  // path comparison
             p = id[p];
         }
         return p;
     }

     public void union(int p, int q) {
         int i = find(p);
         int j = find(q);
         if (i == j) return;

         // Make smaller root point to larger one
         if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
         else                  { id[j] = i; sz[i] += sz[j]; }
         count--;
     }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        WeightedUF uf = new WeightedUF(N);
        while (in.hasNextInt())
        {
            int p = in.nextInt();
            int q = in.nextInt();
            if(uf.connected(p, q)) continue;
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
}
