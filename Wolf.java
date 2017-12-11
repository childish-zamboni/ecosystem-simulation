/* [Wolf.java]
 * The wolf class
 * Author: Kailas Moon
 * Date: December 10th, 2017
 */

class Wolf extends Animal implements Comparable<Wolf> 
{  
  //Constructor
  public Wolf(double breedRate, int health, int gender, boolean poisoned)
  {  
    super(breedRate, health, gender, poisoned);
  }
  
  //==================== Methods ====================
  
  //Method for the wolves to move around and interact with other organisms 
  public void moveWolf(int i, int j, int moveDirection, Organism[][] map) 
  {  
    if (moveDirection == 0)
    {
    } 
    else if (moveDirection == 1 && i != 0) 
    {
      if (map[i-1][j] instanceof Plant || map[i-1][j] instanceof Soil)
      {
        step(map, i, j, i-1, j);
      } 
      else if (map[i-1][j] instanceof Sheep)
      {
        eatSheep(map, i, j, i-1, j);
      } 
      else if (map[i-1][j] instanceof Wolf)
      {
        if ( Math.abs(((Wolf)map[i-1][j]).getGender()-1) == ((Wolf)map[i][j]).getGender()) //Wolves can only breed with an animal of the opposite gender.
        {
          if (((Wolf)map[i][j]).getHealth() >= 20 && ((Wolf)map[i-1][j]).getHealth() >=20) //They only breed when both wolves have 20 health or more.
          {
            ((Wolf)map[i][j]).breedWolf(map, i, j);
            ((Wolf)map[i][j]).setHealth(((Wolf)map[i][j]).getHealth()-10); //Each wolf loses 10 health in the process.
            ((Wolf)map[i-1][j]).setHealth(((Wolf)map[i-1][j]).getHealth()-10);
          }
        } 
        else if (((Wolf)map[i][j]).compareTo((Wolf)map[i-1][j]) >= 0) //Wolves fighting. If the attacker has more or the same health, he wins.
        {
          ((Wolf)map[i-1][j]).setHealth(((Wolf)map[i-1][j]).getHealth()-10); //The losing wolf loses 10 health.
        } 
        else 
        {
          ((Wolf)map[i][j]).setHealth(((Wolf)map[i-1][j]).getHealth()-10);
        }
      }
    }
    else if (moveDirection == 2 && j != map[i].length-1)
    {
      if (map[i][j+1] instanceof Plant || map[i][j+1] instanceof Soil)
      {
        step(map, i, j, i, j+1);
      } 
      else if (map[i][j+1] instanceof Sheep) 
      {
        eatSheep(map, i, j, i, j+1);
      }
      else if (map[i][j+1] instanceof Wolf)
      {
        if (Math.abs(((Wolf)map[i][j+1]).getGender()-1) == ((Wolf)map[i][j]).getGender())
        {
          if (((Wolf)map[i][j]).getHealth() >= 20 && ((Wolf)map[i][j+1]).getHealth() >=20)
          {
            ((Wolf)map[i][j]).breedWolf(map, i, j);
            ((Wolf)map[i][j]).setHealth(((Wolf)map[i][j]).getHealth()-10);
            ((Wolf)map[i][j+1]).setHealth(((Wolf)map[i][j+1]).getHealth()-10);
          }
        }
        else if (((Wolf)map[i][j]).compareTo((Wolf)map[i][j+1]) >= 0){
          ((Wolf)map[i][j+1]).setHealth(((Wolf)map[i][j+1]).getHealth()-10);
        } 
        else
        {
          ((Wolf)map[i][j]).setHealth(((Wolf)map[i][j+1]).getHealth()-10);
        }
      }
    } 
    else if (moveDirection == 3 && i != map.length-1) 
    { 
      if (map[i+1][j] instanceof Plant || map[i+1][j] instanceof Soil) 
      {
        step(map, i, j, i+1, j);
      } 
      else if (map[i+1][j] instanceof Sheep) 
      {
        eatSheep(map, i, j, i+1, j);
      }
      else if (map[i+1][j] instanceof Wolf)
      {
        if (Math.abs(((Wolf)map[i+1][j]).getGender()-1) == ((Wolf)map[i][j]).getGender())
        {
          if (((Wolf)map[i][j]).getHealth() >= 20 && ((Wolf)map[i+1][j]).getHealth() >=20)
          {
            ((Wolf)map[i][j]).breedWolf(map, i, j);
            ((Wolf)map[i][j]).setHealth(((Wolf)map[i][j]).getHealth()-10);
            ((Wolf)map[i+1][j]).setHealth(((Wolf)map[i+1][j]).getHealth()-10);
          }
        } 
        else if (((Wolf)map[i][j]).compareTo((Wolf)map[i+1][j]) >= 0){
          ((Wolf)map[i+1][j]).setHealth(((Wolf)map[i+1][j]).getHealth()-10);
        } 
        else 
        {
          ((Wolf)map[i][j]).setHealth(((Wolf)map[i+1][j]).getHealth()-10);
        }
      }
    } 
    else if (moveDirection == 4 && j != 0) 
    { 
      if (map[i][j-1] instanceof Plant || map[i][j-1] instanceof Soil) 
      {
        step(map, i, j, i, j-1);
      } 
      else if (map[i][j-1] instanceof Sheep) 
      {
        eatSheep(map, i, j, i, j-1);
      } 
      else if (map[i][j-1] instanceof Wolf)
      {
        if (Math.abs(((Wolf)map[i][j-1]).getGender()-1) == ((Wolf)map[i][j]).getGender())
        {
          if (((Wolf)map[i][j]).getHealth() >= 20 && ((Wolf)map[i][j-1]).getHealth() >=20)
          {
            ((Wolf)map[i][j]).breedWolf(map, i, j);
            ((Wolf)map[i][j]).setHealth(((Wolf)map[i][j]).getHealth()-10);
            ((Wolf)map[i][j-1]).setHealth(((Wolf)map[i][j-1]).getHealth()-10);
          }
        } 
        else if (((Wolf)map[i][j]).compareTo((Wolf)map[i][j-1]) >= 0)
        {
          ((Wolf)map[i][j-1]).setHealth(((Wolf)map[i][j-1]).getHealth()-10);
        }
        else
        {
          ((Wolf)map[i][j]).setHealth(((Wolf)map[i][j-1]).getHealth()-10);
        }
      }
    } 
    
    this.setHealth(this.getHealth()-1); //They lose 1 health each turn
    
    if (this.getPoisoned()) //They lose 1 more if they'r poisoned.
    {
      this.setHealth(this.getHealth()-1);
    }
    
    if(this.getHealth() <= 0) //They die if they run out of health.
    {
      map[i][j] = new Soil(((double)(Math.random())));
      ((Soil)(map[i][j])).setStatus(false);
    }
  }//End of moveWolf
  
  //Method for wolves stepping on soil
  public void step(Organism[][] map, int i, int j, int soilI, int soilJ) 
  {  
    map[i][j] = new Soil(((double)(Math.random()))); //New soil with random chance of becoming fertile
    ((Soil)(map[i][j])).setStatus(false); //The new soil starts off dry.
    map[soilI][soilJ] = new Wolf(this.getBreedRate(), this.getHealth(), this.getGender(), this.getPoisoned()); //The wolf moves.
  }//End of step
  
  //Method for eating sheep
  public void eatSheep(Organism[][] map, int i, int j, int sheepI, int sheepJ) 
  {
    ((Wolf)map[i][j]).setHealth(((Wolf)map[i][j]).getHealth()+((Sheep)map[sheepI][sheepJ]).getHealth()); //The wolf gains health based on the sheep's health.
    map[i][j] = new Soil(((double)(Math.random()))); //New soil with random chance of becoming fertile
    ((Soil)(map[i][j])).setStatus(false); //The new soil starts off dry.
    map[sheepI][sheepJ] = new Wolf(this.getBreedRate(), this.getHealth(), this.getGender(), this.getPoisoned()); //The wolf moves.
  }//End of eatSheep
  
  //Method for breeding wolf
  public void breedWolf(Organism[][] map, int i, int j) 
  {
    if (((double)(Math.random())) <= ((Wolf)map[i][j]).getBreedRate()) //Chance of reproduction being successful based on user input.
    {
      if(map[i+1][j] instanceof Soil || map[i+1][j] instanceof Plant) //Looking for spots for the baby wolf
      {
        map[i+1][j] = new Wolf(this.getBreedRate(), this.getHealth(), (int)Math.random(), this.getPoisoned());
      } 
      else if(map[i-1][j] instanceof Soil || map[i-1][j] instanceof Plant)
      {
        map[i-1][j] = new Wolf(this.getBreedRate(), this.getHealth(), (int)Math.random(), this.getPoisoned());
      } 
      else if(map[i][j+1] instanceof Soil || map[i][j+1] instanceof Plant)
      {
        map[i][j+1] = new Wolf(this.getBreedRate(), this.getHealth(), (int)Math.random(), this.getPoisoned());
      } 
      else if(map[i][j-1] instanceof Soil || map[i][j-1] instanceof Plant)
      {
        map[i][j-1] = new Wolf(this.getBreedRate(), this.getHealth(), (int)Math.random(), this.getPoisoned());
      }
    }
  }//End of breedWolf
  
  //Method to compare which wolf gets hurt when they fight
  @Override
  public int compareTo(Wolf wolf) {
    return Integer.compare(this.getHealth(), wolf.getHealth());
  }//End of compareTo
  
}//End of class