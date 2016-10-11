package CoffeeShopProblem;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;


public class CoffeeShopsOfInterest {


   private static final int _LOCATION_COUNT = 3;

   private static final int BIG_DECIMAL_SCALE = 4;

   private double inputLat;

   private double inputLng;

   private String inputFileName;

   // so as to only keep top three

   BoundedTreeSet queue = new BoundedTreeSet(new DistanceComparator(), 3);


   public CoffeeShopsOfInterest() {

   }


   public CoffeeShopsOfInterest(double lat, double lng, String filePath) {

      this.inputLat = lat;

      this.inputLng = lng;

      this.inputFileName = filePath;


   }


   private void processCoffeeShop(CoffeeShopLocation location) {


      double distance = computeDistance(location.lat, location.lng);

      System.out.println("Distance computed -> Coffee shop Name:"
            + location.shopName + ", Distance to user: "

            + distance + " \n");


      queue.add(new CoffeeShopDistance(location, distance));


      // adds the coffee shop to our priority queue if its the top 3 closest

      // from the user


   }


   private double computeDistance(double x1, double y1) {

      double a = (x1 - inputLat) * (x1 - inputLat);

      double b = (y1 - inputLng) * (y1 - inputLng);

      return Math.sqrt(a + b);

   }


   class DistanceComparator implements Comparator<CoffeeShopDistance> {


      @Override
      public int compare(CoffeeShopDistance o1, CoffeeShopDistance o2) {

         return o1.distanceToCoffeeShop.compareTo(o2.distanceToCoffeeShop);

      }

   }


   class CoffeeShopDistance {


      CoffeeShopLocation coffeeShop;

      Double distanceToCoffeeShop;


      public CoffeeShopDistance(CoffeeShopLocation coffeeShop,
            Double distanceToCoffeeShop) {

         this.coffeeShop = coffeeShop;

         this.distanceToCoffeeShop = distanceToCoffeeShop;

      }


   }


   public class CoffeeShopLocation {


      String shopName;

      double lat;

      double lng;


      // do not create Location object without all fields


      public CoffeeShopLocation(String shopName, double x, double y) {


         this.shopName = shopName;

         this.lat = x;

         this.lng = y;


      }


   }

   class BoundedTreeSet extends TreeSet<CoffeeShopDistance> {

      private static final long serialVersionUID = 1L;

      private int bound;


      public BoundedTreeSet(DistanceComparator distanceComparator, int bound) {

         super(distanceComparator);

         this.bound = bound;

      }


      @Override
      public boolean add(CoffeeShopDistance e) {

         if (queue.size() < bound) {

            super.add(e);

            return true;

         } else if (e.distanceToCoffeeShop < queue.last().distanceToCoffeeShop) {

            super.add(e);

            queue.pollLast();

            return true;

         }

         return false;


      }


   }


   public void processInputFile() {


      BufferedReader br = null;

      String line = "";

      String delimiter = ",";

      int lineNumber = 0;


      try {

         br = new BufferedReader(new FileReader(inputFileName));

         while ((line = br.readLine()) != null) {

            lineNumber++;

            // use comma as separator

            String[] tokens = line.split(delimiter);


            if (tokens.length != 3) {

               System.err.println("Malformed input - ignoring at line number:"
                     + lineNumber);

               continue;

            }

            System.out.println(

            "Processing input -> Name:" + tokens[0] + ", Lat: " + tokens[1]
                  + ", Lng:" + tokens[2]);


            processCoffeeShop(

            new CoffeeShopLocation(tokens[0], Double.valueOf(tokens[1]),
                  Double.valueOf(tokens[2])));


         }


      } catch (FileNotFoundException e) {

         e.printStackTrace();

      } catch (IOException e) {

         e.printStackTrace();

      } finally {

         if (br != null) {

            try {

               br.close();

            } catch (IOException e) {

               e.printStackTrace();

            }

         }

      }

   }


   public static void main(String args[]) throws Exception {

      if (args.length != 3) {

         throw new Exception("INVALID ARGUMENTS");

      }

      CoffeeShopsOfInterest engine = new CoffeeShopsOfInterest();


      try {

         engine.inputLat = Double.valueOf(args[0]);

         engine.inputLng = Double.valueOf(args[1]);

         engine.inputFileName = args[2];

      } catch (Exception e) {

         throw new Exception("INVALID ARGUMENTS: " + e.getLocalizedMessage());

      }


      engine.processInputFile();

      engine.printTopCoffeeLocations();


   }


   private void printTopCoffeeLocations() {

      int size = queue.size();


      if (size <= 0) {

         System.err.println("No nearby coffee shops");

         return;

      }

      for (int i = 0; i < size; i++) {

         CoffeeShopDistance shop = queue.pollFirst();

         System.out.println(

         shop.coffeeShop.shopName
               + ", "
               + new BigDecimal(shop.distanceToCoffeeShop,
                     MathContext.DECIMAL128)

               .setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP));

      }

   }


}