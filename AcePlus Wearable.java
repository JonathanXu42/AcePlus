public class Wearable {
   private String name;
   private int price;
   private String color;
   private boolean owned;
   
   Wearable(String name, int price, String color, boolean owned) {
      this.name = name;
      this.price = price;
      this.color = color;
      this.owned = owned;
   }
   
   /*
   public void initialize(String name, int price, String color, boolean owned) {
      this.name = name;
      this.price = price;
      this.color = color;
      this.owned = owned;
   }
   */
   
   public boolean getOwnership() {
      return this.owned;
   }
   
   public void getDescription() {
      System.out.println("Name: " + name);
      System.out.println("Price: " + price + " coins");
      System.out.println("Color: " + color);
      
      if (owned == true) {
         System.out.println("Status: owned");
      }
      else
         System.out.println("Status: not owned");
         
      System.out.println();
   }
   
   public boolean buyCosmetic() {
      if (this.owned == true) {
         System.out.println("You already own " + name);
         return false;
      }
      else if (price > Test.numCoins) {
         System.out.println("You need " + price + " coins to buy " + name + ". You only have " + Test.numCoins);
         return false;
      }
      else {
         System.out.println("Congratulations! You now own " + name + "! You now have " + (Test.numCoins - price) + " left");
         Test.numCoins = Test.numCoins - price;
         return true;
      }
   }
}

class Test {
   public static int numCoins = 5000;
   
   public static void setNumCoins(int newNumCoins) {
      numCoins = newNumCoins;
   }
   
   /*
   The Test class is supposed to represent the user. Originally, the Test class was supposed to have a nested static class called
   CosmeticCollection, which would contain a static array with all of the Wearable objects in the game. The "owned" variable in each
   object would let the player know whether they owned that object or not. By iterating through the "allWearables" array, the player
   could scroll through all of the Wearable objects in the game, and the game could keep track of how many Wearable objects there were
   and how many the player owned.
   
   However, no matter what we did to implement it, we would get a NullPointerException when running the program. The elements of the
   allWearables array did not get initialized, and even when we tried initializing them inside a constructor, that didn't happen.
   */
   
   /*
   public static class CosmeticCollection {
      public static Wearable[] allWearables = new Wearable[4];
      //allWearables[0] = new Wearable("Mets baseball cap", 450, "white", false);
      //allWearables[1] = new Wearable("Plain hoodie", 900, "black", true);
      //allWearables[2] = new Wearable("Napoleon III mustache", 5000, "black", false);
      //allWearables[3] = new Wearable("Cactus", 90, "green", true);
      
      allWearables[0].initialize("Mets baseball cap", 450, "white", false);
      allWearables[1] = new Wearable("Plain hoodie", 900, "black", true);
      //allWearables[2] = new Wearable("Napoleon III mustache", 5000, "black", false);
      //allWearables[3] = new Wearable("Cactus", 90, "green", true);
      
      public static int numWearables = allWearables.length;
      
      public static int getNumWearablesOwned() {
         int numOwned = 0;
      
         for (int i = 0; i < numWearables; i++) {
            if (allWearables[i].getOwnership() == true) {
               numOwned++;
            }
         
         
         return numOwned;
         }
         //numWearablesOwned = numOwned;      
      }
   }
   */
   
   public static void main(String[] args) {
      /*
      CosmeticCollection.allWearables[0].initialize("Mets baseball cap", 450, "white", false);
      CosmeticCollection.allWearables[1].initialize("Plain hoodie", 900, "black", true);
      CosmeticCollection.allWearables[2].initialize("Napoleon III mustache", 5000, "black", false);
      CosmeticCollection.allWearables[3].initialize("Cactus", 90, "green", true);
      */
      
      //System.out.println("There are " + CosmeticCollection.numWearables + " wearables");
      //System.out.println("You own " + CosmeticCollection.getNumWearablesOwned() + " of them");
      
      Wearable baseballCap = new Wearable("Mets baseball cap", 450, "white", false);
      Wearable hoodie = new Wearable("Plain hoodie", 900, "black", true);
      Wearable mustache = new Wearable("Napoleon III mustache", 5000, "black", false);
      Wearable pottedPlant = new Wearable("Cactus", 90, "green", true);
      
      baseballCap.getDescription();
      hoodie.getDescription();
      mustache.getDescription();
      pottedPlant.getDescription();
      
      baseballCap.buyCosmetic();
      hoodie.buyCosmetic();
      mustache.buyCosmetic();
      pottedPlant.buyCosmetic();
   }
}
