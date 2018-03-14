import java.io.*;
import java.util.*;
class Graph
{
     private int V;
     private LinkedList<Integer> adj[];
     Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
     void addEdge(int v, int w)
    {
        adj[v].add(w);
    }
    void duplicator(Graph dup)
     {
         for(int v = 0; v < this.V; v++)
             for(Integer pCrawl: this.adj[v])
            	 dup.addEdge(v, pCrawl);
         System.out.println("Succesfully Cloned!");
     }
    void print()
    {
        for(int v = 0; v < this.V; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            for(Integer pCrawl: this.adj[v]){
                System.out.print(pCrawl+" ");
            }
            System.out.println("");
        }
    }
}
public class LinkList
{
	 public static void main(String args[])
	    {
	        Graph g = new Graph(7);
	        g.addEdge(1, 2);
	        g.addEdge(1, 5);
	        g.addEdge(2, 3);
	        g.addEdge(2, 6);
	        g.addEdge(3, 1);
	        g.addEdge(3, 4);
          g.addEdge(4, 5);
          g.addEdge(4, 2);
          g.addEdge(5, 6);
          g.addEdge(5, 2);
          g.addEdge(6, 3);
	        Graph u=new Graph(7);
	        g.duplicator(u);
	        u.print();
	    }
}
