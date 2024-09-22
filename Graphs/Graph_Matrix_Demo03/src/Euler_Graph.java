
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Stack;

// class for a undirected multi-graph with Euler property supported
public class Euler_Graph extends Graph_Matrix {

    ArrayList<Integer> E_cycle;
    int startV = 0; // start vertex is used to find the Euler cycle

    Euler_Graph() {
        super();
    }

    // constructor: user assigns vertex names in a string
    Euler_Graph(String vertexNames) {
        super(vertexNames);
    }

    // determining a vertex's degree <-- sum of a row in the adjacency matrix
    private int degree(int v) {
        int result = 0;
        for (int i = 0; i < nVertices; i++) {
            result += adjMatrix[v][i];
        }
        return result;
    }

    // counting number of odd vertices in the graph
    private int countOddVertices() {
        int count = 0;
        for (int i = 0; i < nVertices; i++) {
            if (degree(i) % 2 > 0) {
                count++;
            }
        }
        return count;
    }

    // checking whether the graph has the Euler cycle or not - Euler theorem
    // a graph has the Euler cycle <--> all vertices having even degree
    public boolean hasEulerCycle() {
        return countOddVertices() == 0;
    }

    // a graph has the Euler path <--> there are exacly 2 odd-degree vertices
    public boolean hasEulerPeth() {
        return countOddVertices() == 2;
    }

    // when the vertex v is considered when detecting the Euler cycle
    // we can choose an arbitrary adjacency of v
    // to simplify, we choose the first adjacent of v
    private int firstAdjacent(int v) {
        for (int i = 0; i < nVertices; i++) {
            if (adjMatrix[v][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // copy this graph to the other
    // when the Euler algorithm runs, adjacency matrix must be changed
    public Euler_Graph copy() {
        Euler_Graph result = new Euler_Graph();
        result.nVertices = this.nVertices;
        result.vSet = this.vSet;
        result.setAdjMatrix(this.adjMatrix);
        return result;
    }

    // from u, going to v, one edge/connection must be removed
    private void removeOneEdge(int u, int v) {
        if (adjMatrix[u][v] > 0) {
            adjMatrix[u][v]--;
            adjMatrix[v][u]--;
        }
    }

    // finding the Euler cycle from the vertex startV
    public ArrayList<Integer> findEulerCycle(int startV) {
        if (!hasEulerCycle()) {
            return E_cycle = null;
        }
        this.startV = startV;
        Euler_Graph gr = this.copy(); // copy this graph to gr
        E_cycle = new ArrayList<>(); // initiate the Euler cycle result
        Stack<Integer> stk = new Stack<>(); // set of vertices examined
        stk.push(startV); // push to the stack all vertices of each sub-cycle
        int u;
        while (!stk.empty()) {
            u = stk.peek(); // detect edges of a sub-cycle
            int v = gr.firstAdjacent(u); // the first adjacent of u
            if (v >= 0) { // v is an adjacent of u --> an edge of a sub-cycle
                stk.push(v); // put u to the set of vertices examined
                gr.removeOneEdge(u, v); // remove connection u <--> v
            } // when u is isolated, all sub-cycles related to u are detected
            // u is added to the E-cycle
            else {
                E_cycle.add(stk.pop());
            }
        }
        return E_cycle;
    }

    // get Euler cycle in string format
    public String EulerCycleStr() {
        if (E_cycle == null) {
            return null;
        }
        String result = "Euler cycle from " + this.vSet[startV] + ": ";
        for (Integer v : E_cycle) {
            result += this.vSet[v] + " ";
        }
        return result;
    }

    // write the Euler cycle to file
    public boolean printEulerCycleToFile(String filename) throws Exception {
        if (E_cycle == null) {
            return false;
        }
        File f = new File(filename); // open new file
        if (f.exists()) {
            f.delete();
        }
        RandomAccessFile rf = new RandomAccessFile(f, "rw");
        rf.writeBytes(this.EulerCycleStr() + "\r\n");
        rf.close();
        return true;
    }
} // Euler_Graph class
