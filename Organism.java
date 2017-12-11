/* [Organism.java]
 * The organism class
 * Author: Kailas Moon
 * Date: December 10th, 2017
 */

class Organism 
{
  
//Variables
  private double breedRate;
  
  //Constructor
  public Organism(double breedRate) {
    this.breedRate = breedRate;  
  }
  
  //==================== Getters and setters ====================
  
  public double getBreedRate() {
    return this.breedRate;
  }
  
}//End of class