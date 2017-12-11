/* [Animal.java]
 * The animal class
 * Author: Kailas Moon
 * Date: December 10th, 2017
 */

class Animal extends Organism 
{
  
  private int health;
  private int gender; //0 or 1
  private boolean poisoned;
  
  public Animal(double breedRate, int health, int gender, boolean poisoned)
  {
    super(breedRate);
    this.health = health;
    this.gender = gender;
    this.poisoned = poisoned;
  }
  
  //==================== Getters and setters ====================
  
  public int getHealth() 
  {
    return this.health;
  }
  public void setHealth(int newHealth) 
  {
    this.health = newHealth; 
  }
  
  public int getGender() 
  {
    return this.gender;
  }
  
  public boolean getPoisoned() 
  {
    return this.poisoned;
  }
  
}//End of class