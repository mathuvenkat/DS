package graphImpl;

public class Vertex
{
   int vertexData;
   Edge firstEdge;
   boolean isVisited = false;
   //String color = "white"; // create enums( white / grey / black )
   Color color = Color.WHITE;

   public Vertex(int data )
   {
      this.vertexData = data;
      this.firstEdge = null;
   }
}
