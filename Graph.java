/*
 * File: Graph.java
 * Purpose: This is a version of the Sim Pencil Game (see Wiki for details);
 *     it draws the game on  an 800x800 window and allows the user to 
 * Date: 4/28/16
 * Class: CS 112
 *
 * Authors: Jake Bloomfeld (jtbloom@bu.edu) & Francis Zamora (francisz@bu.edu)
 */

public class Graph {
    
    private static final int N = 6;
    private static int[][] B = new int[N][N]; 
       
    int vertices; 
    
    public Graph(int N) { // a constructor for a instance of the class with N vertices 
       vertices = N;
    }
    
    public void addEdge(int u, int v, int w) { // add an edge from vertex u to v with value w (which in this hw will be  only 0, -1, or 1)
        B[u][v] = w;
        B[v][u] = w;
    }
    
    public void removeEdge(int u, int v) { // remove the edge from u to v and the (duplicate) edge from v to u
        B[u][v] = 0;
        B[v][u] = 0;
    }
    
    public int getEdge(int u, int v) { // return the value (-1, 0, or 1) of the edge that goes from u to v
        return B[u][v];
    }
    
    public boolean isEdge(int u, int v) { // return true or false depending on whether there is an edge (of either color) from u to v
        if (B[u][v] == -1 || B[u][v] == 1) {
            return true;
        }
        return false;
    }
    
    public int degree(int v) { // return the number of edges of either color connected to vertex v
        int counter = 0;
        for (int i = 0; i < B.length; i++) {
            if (B[v][i] == 1 || B[v][i] == -1) {
                counter ++;
            }
        }
        return counter;
    }
    
    public int degree(int v, int w) { // return the number of edges of color w connected to vertex v
        int counter = 0;
        for (int i = 0; i < B.length; i++) {    
            if (B[v][i] == w) {
                counter ++;
             }
        }
        return counter;
    }
        
    public void printEdges() { // print out the edge matrix, as shown above; this is only for debugging;
        System.out.print("\t");
        for (int k = 0; k < N; k++) {
            System.out.print(k);
            System.out.print("\t");
        }
        System.out.println();
        for (int i = 0; i < B.length; i++) {
            System.out.println();
            System.out.print(i + ": ");
            for (int j = 0; j < B.length; j++) {                
                System.out.print("\t" + B[i][j]);
            }
            System.out.print("\n");
        }
    }
      
    
    public boolean isCycleOfLength(int n, int w) {  // return true iff there is a cycle of length n among edges of color w 
        for (int i = 0; i < B.length; i++) {
            if (isCycleHelper(i,i,n,w)) {
                return true;
            }
        }
        return false;
        }
    
    private boolean isCycleHelper(int u, int v, int n, int w) { // returns true iff there is a path of length n from u to v, connected only with edge of color w:
        if (n == 1) {
            if (getEdge(u,v) == w) {
                return true;
            }
            return false;
        }
        else {
            for (int i = u + 1; i < B.length; i++) {
                if (getEdge(u,i) == w) {
                    return isCycleHelper(i, v, --n, w);
                }  
            }
            return false;
        }
    }
   
                
    public static void main (String[] args) {
        
        System.err.println("Unit Test for Graph Class");
        Graph g = new Graph(6);
        System.out.println("\nCreated new Graph");
        
        System.out.println("\n[1] Testing addEdge...adding edge from vertex 0 to" 
                               + " vertex 1 with value -1..then print board.\n");
        g.addEdge(0,1,-1);
        g.printEdges();
                
        System.out.println("\n\n[2] Testing getEdge...getting value of the edge"
                               + " from vertex 0 to vertex 1...should print out -1.\n");
        System.out.println(g.getEdge(0,1));
        
        System.out.println("\n\n[3] Testing addEdge...adding edge from vertex 0"
                               + " to vertex 2 with value -1...then print board.\n");
        g.addEdge(0,2,-1);
        g.printEdges();
        
        System.out.println("\n\n[4] Testing degree(int v, int w)...checking to"
                               + " see if there are any blue edges (-1s) connected"
                               + " to of vertex 0...should print out 2.\n");
        System.out.println(g.degree(0,-1));
        
        System.out.println("\n\n[5] Testing isEdge...checking if an edge exists"
                               + " between vertex 0 and vertex 2...should print"
                               + " true.\n");
        System.out.println(g.isEdge(0,2));
        
        System.out.println("\n\n[6] Testing removeEdge...removing edge from"
                               + " vertex 0 to vertex 2...then print board.\n");
        g.removeEdge(0,2);
        g.printEdges();
        
        System.out.println("\n\n[7] Testing isEdge...checking if an edge exists"
                               + " between vertex 0 and 2...should print false.\n");
        System.out.println(g.isEdge(0,2));
        
        System.out.println("\n\n[8] Testing addEdge...adding edge from vertex 4"
                               + " to vertex 1 with value 1...then print board.\n");
        g.addEdge(4,1,1);
        g.printEdges();
        
        System.out.println("\n\n[9] Testing getEdge...getting value of the edge from"
                               + " vertex 4 to vertex 1...should print out 1.\n");
        System.out.println(g.getEdge(4,1));
        
        System.out.println("\n\n[10] Testing addEdge...adding edge from vertex 2" 
                               + " to vertex 3 with value 1...then print board.\n");
        g.addEdge(2,3,1); 
        g.printEdges();
        
        System.out.println("\n\n[11] Testing degree(int v)...getting degree (the" 
                               + " number of edges of either color connected to a"
                               + " certain vertex) of vertex 2...should print out 1.\n");
        System.out.println(g.degree(2));
                
        System.out.println("\n\n[12] Testing addEdge...adding edge from vertex 2"
                               + " to vertex 5 with value 1...then print board.\n");
        g.addEdge(2,5,1);
        g.printEdges();
        
        System.out.println("\n\n[13] Testing degree(int v)...getting degree (the" 
                               + " number of edges of either color connected to a"
                               + " certain vertex) of vertex 2...should print out 2.\n");
        System.out.println(g.degree(2));
        
        System.out.println("\n\n[14] Testing addEdge...adding edge from vertex 3"
                               + " to vertex 0 with value -1...then print board.\n");
        g.addEdge(3,0,-1);
        g.printEdges();
        
        System.out.println("\n\n[15] Testing degree(int v, int w)...checking to"
                               + " see if there are any blue edges (-1s) connected"
                               + " to of vertex 3...should print out 1.\n");
        System.out.println(g.degree(5,1));
        
        
        System.out.println("\n\n[16] Testing addEdge...adding edge from vertex 3"
                               + " to vertex 2 with value 1...then print board.\n");
        g.addEdge(3,2,1);
        g.printEdges();
        
        System.out.println("\n\n[17] Testing degree(int v, int w)...checking to" 
                               + " see if there are any blue edges (-1s) connected"
                               + " to of vertex 4...should print out 0.\n");
        System.out.println(g.degree(4,-1));
        
        System.out.println("\n\n[18] Testing removeEdge...removing edge from"
                               + " vertex 2 to vertex 5...then print board.\n");
        g.removeEdge(2,5);
        g.printEdges();
        
        System.out.println("\n\n[19] Testing removeEdge...removing all edges from"
                               + " the board...then print board.\n");
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B.length; j++) {
                g.removeEdge(i,j);
            }
        }
        g.printEdges();
        
        System.out.println("\n\n[20] Testing isCycleOfLength...adding edge from"
                               + " vertex 2 to vertex 3 with value 1...adding"
                               + " edge from vertex 2 to vertex 5 with value 1"
                               + "...adding edge from vertex 3 to vertex 5 with"
                               + " value 1...this creates a cycle of length 3..."
                               + " then printing board...isCycleOfLength(3,1) should"
                               + " return true.\n");
        g.addEdge(2,3,1);
        g.addEdge(2,5,1);
        g.addEdge(3,5,1);
        g.printEdges();
        System.out.println("\n" + g.isCycleOfLength(3,1));
        
        System.out.println("\n\n[21] Testing isCycleOfLength...removing edge from"
                               + " vertex 2 to vertex 3...then printing board..."
                               + "isCycleOfLength(3,1) should return false.\n");
        g.removeEdge(2,3);
        g.printEdges();
        System.out.println("\n" + g.isCycleOfLength(3,1));
        
        System.err.println("\n\nUnit tests passed!");

    }
         
}