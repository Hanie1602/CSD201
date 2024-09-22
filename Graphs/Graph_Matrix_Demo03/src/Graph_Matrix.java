// class for a simple graph using adjacency matrix

import java.io.File; // for accessing files
import java.io.RandomAccessFile;

public class Graph_Matrix {

    int nVertices; // number of vertices
    int[][] adjMatrix; // adjacency matrix
    char[] vSet; // vertex names - as default, they are ABCDE ...

    // constructor: assigning default vertex names as ABCDE ...
    Graph_Matrix() {
        vSet = "ABCDEFGHIJKLMNOPQRSTUVXYZ".toCharArray();
    }

    // constructor: user assigns vertex names in a string
    Graph_Matrix(String vertexNames) {
        vSet = vertexNames.toCharArray();
    }

    // assigning an adjacency matrix to the graph
    void setAdjMatrix(int[][] m) {
        nVertices = m.length;
        adjMatrix = new int[nVertices][nVertices];
        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                adjMatrix[i][j] = m[i][j];
            }
        }
    }

    // display the adjacency matrix
    void displayAdjMatrix() {
        int i, j;
        System.out.println("\nThe adjacency matrix:");
        for (i = 0; i < nVertices; i++) {
            System.out.println();
            for (j = 0; j < nVertices; j++) {
                System.out.printf("%3d", adjMatrix[i][j]);
            }
        }
    }

    // create boolean visited[] - it is used in traversal algorithms
    private boolean[] creatFlags() {
        boolean[] visited = new boolean[nVertices];
        for (int i = 0; i < nVertices; i++) {
            visited[i] = false;
        }
        return visited;
    }

    // METHODS FOR TRAVELSING A GRAPH - OUTPUT TO MONITOR
    // visiting the i(th) vertex: print out the vertex name
    void visit(int i) {
        System.out.print(vSet[i] + " ");
    }

    // breath-first traversing a connected component from the k(th) vertex
    void BF_traverseComponent(int k) {
        MyQueue q = new MyQueue();
        boolean[] visited = creatFlags();
        q.enqueue(k); // start the traversal
        visited[k] = true;
        int v;
        while (!q.isEmpty()) {
            v = q.dequeue();
            visit(v);
            for (int i = 0; i < nVertices; i++) { // goto it's adjacents
                if (!visited[i] && adjMatrix[v][i] > 0) {
                    q.enqueue(i);
                    visited[i] = true;
                }
            }
        }
    }

    // breath-first traverse all vertices from the first vertex: 0
    void BF_traverseAll() {
        MyQueue q = new MyQueue();
        boolean[] visited = creatFlags();
        int i, j;
        for (i = 0; i < nVertices; i++) { // start the traversal
            if (visited[i] == false) { // i(th) vertex is not visited
                visited[i] = true;
                q.enqueue(i);
                while (!q.isEmpty()) {
                    int v = q.dequeue();
                    visit(v);
                    for (j = 0; j < nVertices; j++) { // goto it's adjacents
                        if (adjMatrix[v][j] > 0 && !visited[j]) {
                            q.enqueue(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }
    }

    // depth-first traversal the vertex v - recursive implementation
    void DFS(int v, boolean[] visited) {
        visit(v);
        visited[v] = true;
        for (int j = 0; j < nVertices; j++) { // goto it's adjacent vertices
            if (!visited[j] && adjMatrix[v][j] > 0) {
                DFS(j, visited);
            }
        }
    }

    // depth-first traverse a connected component from the k(th) vertex
    void DF_traverseComponent(int k) {
        boolean[] visited = creatFlags(); // create flag array
        DFS(k, visited); // traverse a component from the vertex k
    }

    // depth-first traverse all vertices from the first vertex: 0
    void DF_traverseAll() {
        boolean[] vistied = creatFlags(); //create flags
        for (int i = 0; i < nVertices; i++) { // DF traversal all vertices
            if (vistied[i] == false) {
                DFS(i, vistied);
            }
        }
    }

    // METHOD FOR TRAVERSING A GRAPH - OUTPUT TO A FILE
    // create new file
    private RandomAccessFile createFile(String fname) throws Exception {
        File f = new File(fname); // open new file for writing result
        if (f.exists()) {
            f.delete();
        }
        RandomAccessFile rf = new RandomAccessFile(f, "rw");
        return rf;
    }

    // visiting the i(th) vertex: write the vertex name to file
    void visit_F(RandomAccessFile f, int i) throws Exception {
        f.writeBytes(vSet[i] + " ");
    }

    // breath-first traversing a connected component from the k(th) vertex
    void BF_traverseComponent_F(String filename, int k) throws Exception {
        RandomAccessFile rf = createFile(filename);
        boolean[] visited = creatFlags();
        MyQueue q = new MyQueue(); // traversing the graph to file
        q.enqueue(k);
        visited[k] = true;
        int v;
        rf.writeBytes("BF traversal from the vertex " + vSet[k] + ":\r\n");
        while (!q.isEmpty()) {
            v = q.dequeue();
            visit_F(rf, v);
            for (int j = 0; j < nVertices; j++) {
                if (!visited[j] && adjMatrix[v][j] > 0) {
                    q.enqueue(j);
                    visited[j] = true;
                }
            }
        }
        rf.writeBytes("\r\n");
        rf.close(); // closing the file
    }

    // breath-first traverse all vertices from the first vertex
    void BF_traverseAll_F(String filename) throws Exception {
        RandomAccessFile rf = createFile(filename);
        boolean[] visited = creatFlags();
        rf.writeBytes("BF all vertices, from " + vSet[0] + ":\r\n");
        MyQueue q = new MyQueue();
        int i, j;
        for (i = 0; i < nVertices; i++) { // traversing all vertices
            if (visited[i] == false) { // if the i(th) vertex is not visited
                visited[i] = true;
                q.enqueue(i);
                while (!q.isEmpty()) {
                    int v = q.dequeue();
                    visit_F(rf, v);
                    for (j = 0; j < nVertices; j++) { // examining all it's adjacents
                        if (adjMatrix[v][j] > 0 && !visited[j]) {
                            q.enqueue(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }
        rf.writeBytes("\r\n");
        rf.close();
    }

    // depth-first search the vertex v of the graph - recursive implementation
    // using the marked array, name visited
    void DFS_F(RandomAccessFile rf, int v, boolean[] visited) throws Exception {
        visit_F(rf, v);
        visited[v] = true;
        for (int j = 0; j < nVertices; j++) { // examining adjacent vertices
            if (!visited[j] && adjMatrix[v][j] > 0) {
                DFS_F(rf, j, visited);
            }
        }
    }

    // depth-first traverse a connected component from the k(th) vertex
    void DF_traverseComponent_F(String filename, int k) throws Exception {
        RandomAccessFile rf = createFile(filename);
        boolean[] visited = creatFlags();
        rf.writeBytes("DF from " + vSet[k] + "\r\n");
        DFS_F(rf, k, visited);
        rf.writeBytes("\r\n");
        rf.close();
    }

    // depth-first traverse all vertices from the first vertex
    void DF_traverseAll_F(String filename) throws Exception {
        RandomAccessFile rf = createFile(filename);
        boolean[] visited = creatFlags();
        rf.writeBytes("BF traversal all vertices from " + vSet[0] + ":\r\n");
        int i;
        for (i = 0; i < nVertices; i++) {
            visited[i] = false;
        }
        
        // DF traversal all components
        for (i = 0; i < nVertices; i++) {
            if (visited[i] == false) {
                DFS_F(rf, i, visited);
            }
        }
        rf.writeBytes("\r\n");
        rf.close();
    }
} // Graph_Matrix class
