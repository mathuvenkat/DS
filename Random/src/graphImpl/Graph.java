package graphImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph
{
   public int numNodes;
   public Vertex[] graph;
   private boolean cycleExists = false;

   public Graph(int size)
   {
      this.numNodes = size;
      this.graph = new Vertex[size];

      // build the adj list - ith location corresponds to i vertex
      for (int i = 0; i < size; i++) {
         this.graph[i] = new Vertex(i);
      }
   }

   public void insertEdge(int startVertex,
                          int endVertex,
                          boolean directed)
   {
      insert(startVertex, endVertex);
      if (!directed) {
         insert(endVertex, startVertex);
      }
   }

   private void insert(int startVertex,
                       int endVertex)
   {
      if (graph[startVertex].firstEdge == null) {
         graph[startVertex].firstEdge = new Edge(startVertex, endVertex, null);
      } else {
         Edge curr = graph[startVertex].firstEdge;
         graph[startVertex].firstEdge = new Edge(startVertex, endVertex, curr);
      }
   }

   private List<Integer> getAdjacents(Vertex sourceVertex)
   {
      List<Integer> ret = new ArrayList<Integer>();

      Edge edge = sourceVertex.firstEdge;

      while (edge != null) {
         ret.add(edge.endvertex);
         edge = edge.nextEdge;
      }
      return ret;
   }

   public void BFS(int sourceVertex)
   {
      Vertex node = graph[sourceVertex];
      System.out.println(node.vertexData);

      Queue<Vertex> q = new LinkedList<Vertex>();
      q.add(node);
      node.isVisited = true;

      Vertex temp;
      while (!q.isEmpty()) {
         temp = q.remove();
         List<Integer> adjVertices = getAdjacents(graph[temp.vertexData]);

         for (int vertex : adjVertices) {
            if (!graph[vertex].isVisited) {
               System.out.println(graph[vertex].vertexData);
               graph[vertex].isVisited = true;
               q.add(graph[vertex]);
            }
         }
      }
   }

   public int numComponents()
   {
      int components = 0;

      for (int i = 0; i < graph.length; i++) {
         graph[i].isVisited = false;
      }

      for (int i = 0; i < graph.length; i++) {
         if (graph[i].isVisited == false) {
            RDFS(i);
            components++;
         }
      }
      return components;
   }

   public void DFS(int sourceVertex)
   {
      for (int i = 0; i < graph.length; i++) {
         graph[i].isVisited = false;
      }

      RDFS(sourceVertex);
   }

   private void RDFS(int sourceVertex)
   {
      Vertex node = graph[sourceVertex];
      System.out.println(node.vertexData);
      node.isVisited = true;

      List<Integer> adjVertices = getAdjacents(node);

      for (int vertex : adjVertices) {
         if (!graph[vertex].isVisited) {
            RDFS(vertex);
         }
      }
   }

   public boolean cycleExists()
   {
      for (int i = 0; i < graph.length; i++) {
         graph[i].color = Color.WHITE;
      }

      for (int i = 0; i < graph.length; i++) {
         if (graph[i].color == Color.WHITE) {
            if (cycleExists(i)) {
               cycleExists = false;
               return true;
            }
         }
      }
      return false;
   }

   public boolean cycleExists(int sourceVertex)
   {
      Vertex node = graph[sourceVertex];  
      node.isVisited = true;
      node.color = Color.GRAY;

      List<Integer> adjVertices = getAdjacents(node);

      for (int vertex : adjVertices) {
         if (graph[vertex].color == Color.WHITE) {
            graph[vertex].color = Color.GRAY;
            cycleExists(vertex);
         } else if (graph[vertex].color == Color.GRAY) {
            System.out.println("cycle exists at " + node.vertexData + " and "
                     + graph[vertex].vertexData);
            cycleExists = true;
            return true;  //got to break here and end recursion
         }
      }
      node.color = Color.BLACK;

      return (cycleExists||false);
   }
}
