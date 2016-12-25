/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

import goxels.Goxel;
import goxels.GoxelRockSolid;
import java.io.Serializable;

/**
 *
 * @author Gavin
 */
public class Column  implements Serializable{
    
    public int columnID;
    public short plate;
    public short currentX;
    public short currentY;
    public Goxel[] goxels;
    public short offset = 0; //offset of goxel 0 from initial 0 position.
    public short vectorX = 0; //in units of horizontal bars of pressure, same as pressure elsewhere.
    public short vectorY = 0;
    
    //sums:
    public short mass;
    public float density;
    public short avgTemp;
    public short avgPressure;
    public byte avgSpecificGravity;
    public short avgBrittle; //elasticity ignored, high value = snaps under pressure, low = goxels rearrange columns, columns slip by each other, etc.
    public short deformForce = 2000; //AMOUNT of pressure required to snap or rearrange, in bars.
    
    public Column(int initialThickness){
      //implement rock type from files
      goxels = new GoxelRockSolid[initialThickness];
      for (int g = 0; g < initialThickness; g++){
          goxels[g] = new GoxelRockSolid();
          //pressure based on assumption of basalt, just like default constructor:
          goxels[g].pressure = ((byte)((g*((byte)World.goxelSize)*294-((byte)World.goxelSize)*147)/1000)); //294 = bars per km of basalt pressure per cubic meter)
      }
    }
    
    public void calcAvgGoxelInfo(){
        int sumSpecificGravity = 0;
        int sumTemp = 0;
        int sumPressure = 0;
        int sumBrittle = 0;
        int sumMass = 0;
        for (int g = 0; g < goxels.length; g++){
            for (int m = 0; m < 5; m++){
                
            }
            sumSpecificGravity += goxels[g].specificGravity;
            sumTemp += goxels[g].temperature; //could multiply by fraction / 127? Slower perhaps, see how fast it runs, may not be important.
            sumPressure += goxels[g].pressure;
            byte type = goxels[g].type;
            if (type > 0 && type < 31){ //solid rock or crystal type
                //TODO:implement
                //fine-grained = more ductile
                //coarse grain = more brittle
            } else if (type > 30 && type < 81){ //liquid-like
                //do nothing, i.e. contribution to column brittleness is zero.
            } else {
                sumBrittle += goxels[g].getBrittle(); //is something weird, use local special instructions.
            }
        }
        double denominator =  (double)goxels.length;
        avgTemp = (short)(sumTemp/denominator);
        avgPressure = (short)(sumPressure/denominator);
        avgSpecificGravity = (byte)(sumSpecificGravity/denominator);
        avgBrittle = (short)(sumBrittle/denominator);
        mass = (short)(sumMass/denominator);
        density = mass / (float)(goxels.length);
    }
    
}
