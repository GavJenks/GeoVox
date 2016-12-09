/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

/**
 *
 * @author Gavin
 */
public class Column {
    
    public int columnID;
    public int plate;
    public int currentX;
    public int currentY;
    public Goxel[] goxels;
    public int offset = 0; //offset of goxel 0 from initial 0 position.
    
    public Column(int initialThickness, String initialRock){
      //implement rock type from files
      goxels = new Goxel[initialThickness];
      for (int g = 0; g < initialThickness; g++){
          goxels[g] = new Goxel(initialRock);
      }
    }
}
