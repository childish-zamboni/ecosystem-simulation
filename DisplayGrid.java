/* [DisplayGrid.java]
 * A method for displaying the grid
 * Author: Kailas Moon & Mr. Mangat
 * Date: December 10th, 2017
 */

// Graphics Imports
import javax.swing.*;
import java.awt.*;
import java.awt.image.*; 
import javax.imageio.*; 

class DisplayGrid 
{ 

  private JFrame frame;
  private int maxX,maxY, GridToScreenRatio;
  private Organism[][] world;
  
  DisplayGrid(Organism[][] w) 
  { 
    this.world = w;
    
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = maxY / (world.length+1);  //ratio to fit in screen as square map
    
    System.out.println("Map size: "+world.length+" by "+world[0].length + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);
    
    this.frame = new JFrame("Map of World");
    
    GridAreaPanel worldPanel = new GridAreaPanel();
    
    frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setVisible(true);
  }
  
  
  public void refresh() { 
    frame.repaint();
  }
  
  
  
  class GridAreaPanel extends JPanel {
    public void paintComponent(Graphics g) {        
      //super.repaint();
      
      //Getting images
       Image sheep = Toolkit.getDefaultToolkit().getImage("./images/sheep.jpg");
       Image wolf = Toolkit.getDefaultToolkit().getImage("./images/wolf.jpg");
       Image greenGrass = Toolkit.getDefaultToolkit().getImage("./images/green-grass.jpg");
       Image deadGrass = Toolkit.getDefaultToolkit().getImage("./images/dead-grass.jpg");
       Image deadishGrass = Toolkit.getDefaultToolkit().getImage("./images/deadish-grass.jpg");
       Image fertileSoil = Toolkit.getDefaultToolkit().getImage("./images/fertile-soil.jpg");
       Image deadSoil = Toolkit.getDefaultToolkit().getImage("./images/dead-soil.jpg");
       
      setDoubleBuffered(true); 
      g.setColor(Color.BLACK);
      
      for(int i = 0; i<world[0].length;i=i+1)
      { 
        for(int j = 0; j<world.length;j=j+1) 
        { 
          
          if (world[i][j] instanceof Sheep)    //This block can be changed to match character-color pairs
            g.drawImage(sheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          else if (world[i][j] instanceof Wolf)
            g.drawImage(wolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          else if (world[i][j] instanceof Soil)
            if (((Soil)(world[i][j])).getStatus())
          {
            g.drawImage(fertileSoil,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          } else
          {
            g.drawImage(deadSoil,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }
          else if (world[i][j] instanceof Plant)
            if (((Plant)(world[i][j])).getNutrition() >= 8)
          {
            g.drawImage(greenGrass,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          } else if (((Plant)(world[i][j])).getNutrition() <= 3)
          {
            g.drawImage(deadGrass,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }
          else
          {
            g.drawImage(deadishGrass,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }
        }
      }
    }
  }//end of GridAreaPanel
  
} //end of DisplayGrid