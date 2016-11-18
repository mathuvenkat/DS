package CoffeeShopProblem;

import org.junit.Test;

import CoffeeShopProblem.CoffeeShopsOfInterest.CoffeeShopDistance;
import static org.junit.Assert.*;

public class CoffeeShopTest {

   @Test
   public void testCoffeeShopsOfInterest() {
      CoffeeShopsOfInterest engine =
            new CoffeeShopsOfInterest(Double.valueOf("47.6"),
                  Double.valueOf("-122.4"), "/Users/administrator/CoffeeShops.csv");

      engine.processInputFile();

      CoffeeShopDistance res = engine.queue.pollFirst();
      //engine.printTopCoffeeLocations();


      assertEquals("Starbucks Seattle2", res.coffeeShop.shopName);


      res = engine.queue.pollFirst();
      assertEquals("Starbucks Seattle", res.coffeeShop.shopName);

      res = engine.queue.pollFirst();
      assertEquals("Starbucks SF", res.coffeeShop.shopName);




   }
}
