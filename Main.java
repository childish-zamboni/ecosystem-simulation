/* [Main.java]
 * A program that simulates an ecosystem with plants, soil, wolves, and sheep.
 * Author: Kailas Moon
 * Date: December 10th, 2017
 */

//==================== Detailed description ====================
/* The ecosystem consists of soil, plants, sheep, and wolves.
 * 
 * The soil starts off dry and has a chance of becoming fertile every turn.
 * 
 * Plants can only grow on fertil soil. They have a chance of reproducing in all possible directions each turn.
 * They have a chance of being poisonous.
 * They start off with 0 nutrition and gain some each turn until they hit a maximum.
 * 
 * The Sheep move and eat plants. If poisoned, they lose an extra health point every turn. Eaten plants turn into dry soil.
 * When two sheep of opposite genders collide and they both have at least 20 health, they have a chance of breeding. Both sheep lose 10 health in the process.
 * 
 * The Wolves move and eat sheep If poisoned, they lose an extra health point every turn. They can also step on plants.
 * When two wolves of opposite genders collide and they both have at least 20 health, they have a chance of breeding. Both sheep lose 10 health in the process.
 * If they do not reproduce, the wolves fight. If the attacker has the same or more health, the othe wolf loses 10 health. Otherwise, the attacker loses 10 health.
 */

//Import statements
import java.util.Scanner;

class Main 
{ 
  //==================== Declaring static variables with recommended values ====================
  
  static double soilBreedRate = 1.0;
  static double plantPoisonChance = 1.0;
  static double plantBreedRate = 1.0;
  static double sheepBreedRate = 1.0;
  static double wolfBreedRate = 0.5;
  
  static int maxPlantNutrition = 10;
  static int initialSheepHealth = 40;
  static int initialWolfHealth = 5;
  
  //Variables for how many of each organism there is
  static int plantNumber = 1;
  static int sheepNumber = 1;
  static int wolfNumber = 1;
  
  public static void main(String[] args) 
  { 
    
    //Instance of scanner
    Scanner input = new Scanner(System.in);
    
    //Asking users for input
    System.out.println("How likely should it be for dry soil to become fertile (0-1)? Plants only grow on fertile soil.");
    soilBreedRate = input.nextDouble();
    System.out.println("How likely should it be that plants are poisoned (0-1)? Poison is tranferred through reproduction and being eaten");
    plantPoisonChance = input.nextDouble();
    System.out.println("How likely should it be for plants to reproduce?");
    plantBreedRate = input.nextDouble();
    System.out.println("How likely should it be for sheep to breed successfully (0-1)?");
    sheepBreedRate = input.nextDouble();
    System.out.println("How likely should it be for wolf to breed successfully (0-1)?");
    wolfBreedRate = input.nextDouble();
    System.out.println("What should the plants' maximum nutrition be (integer)?");
    maxPlantNutrition = input.nextInt();
    System.out.println("What should the sheep's initial health be (integer)?");
    initialSheepHealth = input.nextInt();
    System.out.println("What should the wolves' initial health be (integer)?");
    initialWolfHealth = input.nextInt();
    
    System.out.println("How large should the map be?");
    int mapDimension = input.nextInt();
    
    //Map
    Organism map[][] = new Organism[mapDimension][mapDimension];
    
    // Initialize Map
    initializeMap(map);
    
    //Set up Grid Panel
    DisplayGrid grid = new DisplayGrid(map);
    
    //Display the grid on a Panel
    grid.refresh();
    
    while (wolfNumber !=0 && sheepNumber != 0) //Loops until either wolf or sheep becomes extinct
    {
      // Initialize Map (Making changes to map)
      moveMap(map);
      //Small delay
      try{ Thread.sleep(1000); }catch(Exception e) 
      {
        System.out.println(e);
      };
      
      //Display the grid on a Panel
      grid.refresh();
    }
    
    //Tells the user which organisms died out
    if (plantNumber == 0)
    {
      System.out.println("Plants are extinct!");
    }
    if (sheepNumber == 0) {
      System.out.println("Sheep are extinct!");
    }
    if (wolfNumber == 0)
    {
      System.out.println("Wolf are extinct!");
    }
    
  }
  
  // Method to initiallize the map
  public static void initializeMap(Organism[][] map) { 
    
    for(int i = 0; i<map[0].length;i++)        
      for(int j = 0; j<map.length;j++) 
    {
      int random = ((int)(Math.random()*4)); // Random integer for which organism to create (0-3)
      int randomGender = ((int)(Math.random())); //Random integer for gender (0-1)
      
      boolean randomPoisoned; //Chance of plants being poisoned based on user input
      if (Math.random() < plantPoisonChance) 
      {
        randomPoisoned = true; 
      }
      else
      {
        randomPoisoned = false;
      }
      
      if (random == 0) 
      {
        map[i][j]=new Plant(plantBreedRate, 0, randomPoisoned); //New plant
      } 
      else if (random == 1)
      {
        map[i][j]=new Sheep(sheepBreedRate, initialSheepHealth, randomGender, false); //New sheep
      } 
      else if (random == 2) 
      {
        map[i][j]=new Wolf (wolfBreedRate, initialWolfHealth, randomGender, false); //New wolf
      } 
      else if (random == 3) {
        map[i][j] = new Soil (soilBreedRate); //Soil. Soil contains lots of microorganisms, so it too is an organism.
      }
    }
  } //End of initializeMap
  
  //Method to make the organisms move and interact
  public static void moveMap(Organism[][] map){
    
    for(int i = 0; i<map[0].length;i++)        
      for(int j = 0; j<map.length;j++) 
    {
      int moveDirection = ((int)(Math.random()*5)); //Random integer for direction (0-4)
      double spawn = (double)(Math.random()); //Random double to help decide if a plant will spawn
      
      if (map[i][j] instanceof Sheep) //Sheep
      {  
        ((Sheep)map[i][j]).moveSheep(i, j, moveDirection, map);
      } 
      else if (map[i][j] instanceof Wolf) //Wolf
      {
        ((Wolf)map[i][j]).moveWolf(i, j, moveDirection, map);
      } 
      else if (map[i][j] instanceof Plant) //Plant
      {
        ((Plant)map[i][j]).regenerate(maxPlantNutrition); //Plants nutrition regenerates over time.
        if (spawn <= ((Plant)map[i][j]).getBreedRate()) //Decides if the plant will reproduce.
        {
          ((Plant)map[i][j]).breedPlant(i, j, map);
        }
        
      } else if (map[i][j] instanceof Soil) 
      {
        if (((Soil)map[i][j]).getStatus() == false)
        {
          ((Soil)map[i][j]).newStatus();
        }
      }
    }
    
    //Reset variables
    plantNumber = 0;
    sheepNumber = 0;
    wolfNumber = 0;
    
    for(int i = 0; i<map[0].length;i++)  //Counting how many animals there are after they're done moving 
    {
      for(int j = 0; j<map.length;j++)
      {
        if (map[i][j] instanceof Plant)
        {
          plantNumber++;
        }
        else if (map[i][j] instanceof Sheep)
        {
          sheepNumber++;
        }
        else if (map[i][j] instanceof Wolf)
        {
          wolfNumber++;
        }
      }
    }
    
  }//End of moveMap
  
}//End of class