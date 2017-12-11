/* [Soil.java]
 * The soil class. Soil contains a lot of microorganisms so it is an organism in itself.
 * Author: Kailas Moon
 * Date: December 10th, 2017
 */

class Soil extends Organism 
{  
  //Variables
  private boolean fertile;
  
  //Conustrctor
  public Soil(double breedRate) 
  {  
    super(breedRate);
    
    double random = ((double)(Math.random()));
    if (random <= this.getBreedRate()) //chance of being fertile based on user input
    {
      this.fertile = true;
    } 
    else
    {
      this.fertile = false;
    }
  }
  
  //==================== Getters and setters ====================
  
  public boolean getStatus() 
  {
    return fertile;
  }
  
  public void setStatus(boolean newStatus) 
  {
    this.fertile = newStatus;
  }
  
  //==================== Methods ====================
  
  //Method for soil to become fertile based on chance, and breedRate (given by user)
  public void newStatus() {
    double random = ((double)(Math.random()));
    if (random <= this.getBreedRate())
    {
      this.fertile = true;
    } 
    else
    {
      this.fertile = false;
    }
  } //End of newStatus
  
}//End of class