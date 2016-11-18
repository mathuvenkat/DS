package dsProblems;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WordLadderProcessor {


   public class Node {
      String word;
      boolean isVisited;

      public Node(String word) {
         super();
         this.word = word;
         this.isVisited = false;
      }

      public boolean isVisited() {
         return isVisited;
      }

      public void setVisited(boolean isVisited) {
         this.isVisited = isVisited;
      }

      public String getWord() {
         return word;
      }
   }


   Queue<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
   Map<String, Node> dictionary = new HashMap<String, Node>();

   public void addToDict(String word) {
      dictionary.put(word, new Node(word));
   }

   public static void main(String args[]) {
      WordLadderProcessor wp = new WordLadderProcessor();

      wp.addToDict("hit");
      wp.addToDict("hot");
      wp.addToDict("dot");
      wp.addToDict("dog");
      wp.addToDict("lot");
      wp.addToDict("log");
      wp.addToDict("cog");
      wp.addToDict("hog");

      LinkedList<Node> res = wp.shortestPath("hit", "dog");

      System.out.println("BFS Complete - Shortest path...");
      for (Node n : res) {
         System.out.println("->" + n.getWord());
      }

      wp.allPaths("hit", "dog");


   }

   public List<String> getPossibleMoves(String source) {
      List<String> ret = new ArrayList<String>();
      for (String word : dictionary.keySet()) {

         int diffCount = 0;
         if (word.length() == source.length()) {
            for (int i = 0; i < word.length(); i++) {
               if (word.charAt(i) != source.charAt(i)) {
                  diffCount++;
               }
               if (diffCount > 1) {
                  break;
               }
            }
            if (diffCount == 1) {
               ret.add(word);
            }
         }
      }
      return ret;
   }


   public LinkedList<Node> shortestPath(String source, String target) {
      LinkedList<Node> list = new LinkedList<Node>();
      list.add(dictionary.get(source));
      queue.add(list);
      LinkedList<Node> nodeList = null;
      while (!queue.isEmpty()) {
         nodeList = queue.remove();
         Node lastNode = nodeList.getLast();

         if (target.equals(lastNode.getWord())) {
            break;

         }

         List<String> possibleMoves = getPossibleMoves(lastNode.getWord());
         for (String move : possibleMoves) {
            if (!dictionary.get(move).isVisited()) {
               LinkedList<Node> newList = new LinkedList<Node>();
               newList.addAll(nodeList);
               newList.add(dictionary.get(move));
               queue.add(newList);
            }
         }
         lastNode.setVisited(true);
      }
      return nodeList;
   }


   public void allPaths(String source, String target) {
      for (Node n : dictionary.values()) {
         n.setVisited(false);
      }
      List<LinkedList<Node>> list = new ArrayList<LinkedList<Node>>();
      List<String> visited = new ArrayList<String>();
      visited.add(source);

      RDFS(list, target, visited, source);
      System.out.println("DFS Complete - printing all paths :");

      for (int i = 0; i < list.size(); i++) {
         List<Node> path = list.get(i);
         System.out.println("New Path from " + source + " to " + target);
         for (int j = 0; j < path.size(); j++) {
            System.out.println("->" + path.get(j).getWord());
         }

      }

   }

   public void RDFS(List<LinkedList<Node>> list, String target,
         List<String> visited, String currentNode) {
      if (currentNode.equals(target)) {

         LinkedList<Node> path = new LinkedList<Node>();
         for (String word : visited) {
            path.add(new Node(word));
         }
         list.add(path);
         return;
      } else {
         List<String> possibleMoves = getPossibleMoves(currentNode);
         for (String move : possibleMoves) {
            if (!visited.contains(move)) {
               List<String> tempvisited = new ArrayList<String>();
               tempvisited.addAll(visited);
               tempvisited.add(move);
               RDFS(list, target, tempvisited, move);
            }
         }
      }
   }
}
