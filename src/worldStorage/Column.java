/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

import java.io.Serializable;

/**
 *
 * @author Gavin
 */
public class Column  implements Serializable{
    
    public int columnID;
    public int plate;
    public int currentX;
    public int currentY;
    public Goxel[] goxels;
    public int offset = 0; //offset of goxel 0 from initial 0 position.
    
    public Column(int initialThickness){
      //implement rock type from files
      goxels = new Goxel[initialThickness];
      for (int g = 0; g < initialThickness; g++){
          goxels[g] = new Goxel();
          //pressure based on assumption of basalt, just like default constructor:
          goxels[g].setPressure((byte)((g*((byte)World.goxelSize)*294-((byte)World.goxelSize)*147)/1000)); //294 = bars per km of basalt pressure per cubic meter)
      }
    }
}
