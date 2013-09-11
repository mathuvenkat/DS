package DataStructures;

public class Edge
{
   int beginvertex;
   int endvertex;

   Edge nextEdge;


   public Edge(int beginvertex , int endvertex , Edge next)
   {
      this.beginvertex = beginvertex;
      this.endvertex = endvertex;
      this.nextEdge = next;

   }
}
