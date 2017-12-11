/* [Sheep.java]
 * The sheep class
 * Author: Kailas Moon
 * Date: December 10th, 2017
 */

class Sheep extends Animal 
{
  //Constructor
  public Sheep(double breedRate, int health, int gender, boolean poisoned) 
  {
    super(breedRate, health, gender, poisoned);
  }
  
  //==================== Methods ====================
  
  //Method for the sheep to move and interact with other organisms
  public void moveSheep(int i, int j, int moveDirection, Organism[][] map) 
  {  
    if (moveDirection == 0)
    {
    } else if (moveDirection == 1 && i != 0) 
    {
      if (map[i-1][j] instanceof Plant)
      {
        eatPlant(map, i, j, i-1, j);
      } 
      else if (map[i-1][j] instanceof Soil)
      {
        step(map, i, j, i-1, j);
      } else if (map[i-1][j] instanceof Sheep && Math.abs(((Sheep)map[i-1][j]).getGender()-1) == ((Sheep)map[i][j]).getGender()) //Sheep can only breed if they are of opposite genders
      {
        if (((Sheep)map[i][j]).getHealth() >= 20 && ((Sheep)map[i-1][j]).getHealth() >=20) //They can only breed if both sheep have at least 20 health.
        {
          ((Sheep)map[i][j]).breedSheep(map, i, j);
          ((Sheep)map[i][j]).setHealth(((Sheep)map[i][j]).getHealth()-10); //Each sheep loses 10 health in the process
          ((Sheep)map[i-1][j]).setHealth(((Sheep)map[i-1][j]).getHealth()-10);
        }
      }
    } 
    else if (moveDirection == 2 && j != map[i].length-1)
    {
      if (map[i][j+1] instanceof Plant)
      {
        eatPlant(map, i, j, i, j+1);
      }
      else if (map[i][j+1] instanceof Soil)
      {
        step(map, i, j, i, j+1);
      } 
      else if (map[i][j+1] instanceof Sheep && Math.abs(((Sheep)map[i][j+1]).getGender()-1) == ((Sheep)map[i][j]).getGender())
      {
        if (((Sheep)map[i][j]).getHealth() >= 20 && ((Sheep)map[i][j+1]).getHealth() >=20)
        {
          ((Sheep)map[i][j]).breedSheep(map, i, j);
          ((Sheep)map[i][j]).setHealth(((Sheep)map[i][j]).getHealth()-10);
          ((Sheep)map[i][j+1]).setHealth(((Sheep)map[i][j+1]).getHealth()-10);
        }
      }
    } 
    else if (moveDirection == 3 && i != map.length-1) 
    {
      if (map[i+1][j] instanceof Plant)
      {
        eatPlant(map, i, j, i+1, j);
      } 
      else if (map[i+1][j] instanceof Soil) 
      {
        step(map, i, j, i+1, j);
      }
      else if (map[i+1][j] instanceof Sheep && Math.abs(((Sheep)map[i+1][j]).getGender()-1) == ((Sheep)map[i][j]).getGender())
      {
        if (((Sheep)map[i][j]).getHealth() >= 20 && ((Sheep)map[i+1][j]).getHealth() >=20)
        {
          ((Sheep)map[i][j]).breedSheep(map, i, j);
          ((Sheep)map[i][j]).setHealth(((Sheep)map[i][j]).getHealth()-10);
          ((Sheep)map[i+1][j]).setHealth(((Sheep)map[i][j+1]).getHealth()-10);
        }
      }
    }
    else if (moveDirection == 4 && j != 0) 
    { 
      if (map[i][j-1] instanceof Plant) 
      {
        eatPlant(map, i, j, i, j-1);
      }
      else if (map[i][j-1] instanceof Soil)
      {
        step(map, i, j, i, j-1);
      } 
      else if (map[i][j-1] instanceof Sheep && Math.abs(((Sheep)map[i][j-1]).getGender()-1) == ((Sheep)map[i][j]).getGender())
      {
        if (((Sheep)map[i][j]).getHealth() >= 20 && ((Sheep)map[i][j-1]).getHealth() >=20)
        {
          ((Sheep)map[i][j]).breedSheep(map, i, j);
          ((Sheep)map[i][j]).setHealth(((Sheep)map[i][j]).getHealth()-10);
          ((Sheep)map[i][j-1]).setHealth(((Sheep)map[i][j-1]).getHealth()-10);
        }
      }
    }
    
    this.setHealth(this.getHealth()-1); //Loses health each turn
    
    if (this.getPoisoned()) //Loses more health if it's poisoned
    {
      this.setHealth(this.getHealth()-1);
    }

    if(this.getHealth() <= 0) //Dies if it runs out of health
    {
      map[i][j] = new Soil(((double)(Math.random())));
      ((Soil)(map[i][j])).setStatus(false);
    }
  }//End of moveSheep
  
  //Method for stepping on soil
  public void step(Organism[][] map, int i, int j, int soilI, int soilJ) 
  {
    map[i][j] = new Soil(((double)(Math.random()))); //Random chance of becoming fertile
    ((Soil)(map[i][j])).setStatus(false); //Soil becomes dry
    map[soilI][soilJ] = new Sheep(this.getBreedRate(), this.getHealth(), this.getGender(), this.getPoisoned()); //Sheep moves
  }//End of step
  
  //Method for eating plants
  public void eatPlant(Organism[][] map, int i, int j, int plantI, int plantJ) 
  {
    ((Sheep)map[i][j]).setHealth(((Sheep)map[i][j]).getHealth()+((Plant)map[plantI][plantJ]).getNutrition()); //Sheep gains health based on the plant's nutrition
    map[i][j] = new Soil(((double)(Math.random()))); //The plant is now dead and dry soil is in its place.
    ((Soil)(map[i][j])).setStatus(false);
    map[plantI][plantJ] = new Sheep(this.getBreedRate(), this.getHealth(), this.getGender(), this.getPoisoned()); //Sheep moves
  }//End of eatPlant
  
  //Method to breed the sheep
  public void breedSheep(Organism[][] map, int i, int j) 
  {
    if (((double)(Math.random())) <= ((Sheep)map[i][j]).getBreedRate()) //Chance of the reproduction being successful based on user input
    {
      if(map[i+1][j] instanceof Soil || map[i+1][j] instanceof Plant) //Looking for a spot to have the baby sheep
      {
        map[i+1][j] = new Sheep(this.getBreedRate(), this.getHealth(), (int)Math.random(), this.getPoisoned());
      } 
      else if(map[i-1][j] instanceof Soil || map[i-1][j] instanceof Plant)
      {
        map[i-1][j] = new Sheep(this.getBreedRate(), this.getHealth(), (int)Math.random(), this.getPoisoned());
      } 
      else if(map[i][j+1] instanceof Soil || map[i][j+1] instanceof Plant)
      {
        map[i][j+1] = new Sheep(this.getBreedRate(), this.getHealth(), (int)Math.random(), this.getPoisoned());
      } 
      else if(map[i][j-1] instanceof Soil || map[i][j-1] instanceof Plant)
      {
        map[i][j-1] = new Sheep(this.getBreedRate(), this.getHealth(), (int)Math.random(), this.getPoisoned());
      }
    }
  }//End of breedSheep
}