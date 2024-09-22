// testing adjacency-matrix graph traversals
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class TestTraverser {

    // create a graph from a text file with given format
    public static Graph_Matrix loadGraph(String fName) throws Exception {
        Graph_Matrix g = null;
        FileReader fr = new FileReader(fName);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine().trim();
        g = new Graph_Matrix(line);
        int size = line.length();
        int[][] m = new int[size][size];
        StringTokenizer stk;
        for (int i = 0; i < size; i++) {
            line = br.readLine().trim();
            stk = new StringTokenizer(line, ",");
            for (int j = 0; j < size; j++) {
                m[i][j] = Integer.parseInt(stk.nextToken().trim());
            }
        }
        br.close();
        fr.close();
        g.setAdjMatrix(m);
        return g;
    }

    public static void main(String[] args) throws Exception {
        String source = "graph01.txt";
        String BF_fname1 = "BF_fname1.txt";
        String BF_fnameAll = "BF_fnameAll.txt";
        String DF_fname2 = "DF_fname2.txt";
        String DF_fnameAll = "DF_fnameAll.txt";
        Graph_Matrix g = loadGraph(source);
        g.displayAdjMatrix(); // print the adjacency matrix
        System.out.println();
        
        // traversals to monitor
        System.out.println("\nBF traversal a component from B:");
        g.BF_traverseComponent(1); // vertex 1: B
        System.out.println("\nBF traversal all vertices from A:");
        g.BF_traverseAll(); // BF traverse all vertices
        System.out.println("\nDF traversal all vertices from C:");
        g.DF_traverseComponent(2); // vertex 2: C
        System.out.println("\nBF traversal a component from A:");
        g.DF_traverseAll(); // DFT all from 0(A) to file
        
        //Traversals to files
        g.BF_traverseComponent_F(BF_fname1, 1); // BFT from vertex 1(B) to file
        g.BF_traverseAll_F(BF_fnameAll); // BFT all from 0(A) to file
        g.DF_traverseComponent_F(DF_fname2, 2); // DFT from 2(C) to file
        g.DF_traverseAll_F(DF_fnameAll); // DFT all vertices to file
        System.out.println();
    }
}
