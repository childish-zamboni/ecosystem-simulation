/* [Plant.java]
 * The plant class
 * Author: Kailas Moon
 * Date: December 10th, 2017
 */

class Plant extends Organism 
{
  //Varibles
  private int nutrition;
  private boolean poisoned;
  
  //Constructor
  public Plant (double breedRate, int nutrition, boolean poisoned) 
  {
    super(breedRate);
    this.nutrition = nutrition;
    this.poisoned = poisoned;
  }
  
  //==================== Getters and setters ====================
  
  public int getNutrition() 
  {
    return this.nutrition;
  }
  
  public boolean getPoisoned()
  {
    return this.poisoned;
  }
  
  
  //==================== Methods ====================
  
  //Method to regenerate the plant's nutrition up to a certain maximum
  public void regenerate(int maxPlantNutrition) {
    if (this.nutrition < maxPlantNutrition) {
      this.nutrition += 1;
    }
  }//End of regenerate
  
  //Method to breed the plant. They breed asexually, and they only grow on fertile soil. It also breeds in multiple directions at once.
  public void breedPlant(int i, int j, Organism[][] map) 
  {
    if (i != 0 && map[i-1][j] instanceof Soil && ((Soil)map[i-1][j]).getStatus()) //Checking if there's fertile soil
    {
      map[i-1][j] = new Plant(this.getBreedRate(), 0, this.getPoisoned());
    } 
    if (j != map[i].length-1 && map[i][j+1] instanceof Soil && ((Soil)map[i][j+1]).getStatus()) 
    {
      map[i][j+1] = new Plant(this.getBreedRate(), 0, this.getPoisoned());
    }
    if (i != map.length-1 && map[i+1][j] instanceof Soil && ((Soil)map[i+1][j]).getStatus())  
    {
      map[i+1][j] = new Plant(this.getBreedRate(), 0, this.getPoisoned());
    }
    if (j != 0 && map[i][j-1] instanceof Soil && ((Soil)map[i][j-1]).getStatus()) 
    { 
      map[i][j-1] = new Plant(this.getBreedRate(), 0, this.getPoisoned());
    }
  }//End of breedPlant
  
}//End of class