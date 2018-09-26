/*
 * File: Player.java
 * Date: 4/25/16
 * Class: CS 112
 *
 * Authors: Jake Bloomfeld (jtbloom@bu.edu) & Francis Zamora (francisz@bu.edu)
*/

public class Player {
    
    private static final int N = 6;
    private final int Inf = 100000;
    private final int negInf = -100000;
    private int depthValue = 8;  //this sets the depth of the search
    
    public Move chooseMove(Graph G) { 
        Move bestMove = new Move(0, 1);
        int max = negInf;   
        
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (G.isEdge(i, j) == false) {
                    if (i != j) {
                        bestMove = new Move(i, j);
                    }
                }
            }
        }
        for (int k = 0; k < N; k++) {
            for (int l = k + 1; l < N; l++) {
                if(G.isEdge(k, l) == false) {
                    if (k != l) {      
                        Move m = new Move(k, l);
                        int val = minMax( G, 1, negInf, Inf);
                        if(val > max) {
                            bestMove = m;
                            max = val; 
                        }
                        G.removeEdge(k, l);
                    }
                }
            }
        }
        return bestMove; 
    }
    
    public int eval(Graph G) {
        int A = 0;
        int B = 0;
        for (int i = 0; i < N; i++) {
            if (G.degree(i, 1) > 1) {
                A += G.degree(i, 1);
            }
            if (G.degree(i, -1) > 1) {
                B += G.degree(i, -1);
            }
        }
        if (G.isCycleOfLength(3, 1)) {
            A = Inf;
        }
        if (G.isCycleOfLength(3, -1)) {
            B = negInf;
        }
        int C = B - A;
        return C;
    }
    
    
    public int minMax(Graph G, int depth, int alpha, int beta) {
        boolean isLeaf;
        for(int k = 0; k < N; k++){
            if(G.degree(k) < N - 1)
                isLeaf = false;
        }
        isLeaf = true;                
    
        if (depth == depthValue || isLeaf) {
            return eval(G);
        }
        else if (depth % 2 == 0 ) {  
            int val = negInf;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (G.isEdge(i,j) == false) {
                        alpha = Math.max(alpha, val); 
                        if (beta < alpha) {
                            break;
                        }
                        G.addEdge(i,j, -1); 
                        val = Math.max(val, minMax(G, depth + 1, alpha, beta));
                        G.removeEdge(i,j);
                    }
                }
            }
            return val;
        }
        else {    
            int val = Inf;
            for (int k = 0; k < N; k++) {
                for (int l = k + 1; l < N; l++) {
                    if (G.isEdge(k,l) == false) {
                        beta = Math.min(beta, val); 
                        if (beta < alpha) {
                            break;
                        }
                        G.addEdge(k, l, 1); 
                        val = Math.min(val, minMax( G, depth + 1, alpha, beta )); 
                        G.removeEdge(k, l); 
                    }
                }
            }
            return val;
        }
   }
            
}
  


