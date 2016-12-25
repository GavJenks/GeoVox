/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

import java.io.Serializable;

/**
 * This is a lightweight class that keeps track of a plate's vectors of movement,
 * which boundaries it subducts under vs. over, which columns belong to it (but NOT
 * storing actual goxels or data for columns, just their IDs)
 * @author Gavin
 */
public class Plate  implements Serializable{
    //public boolean[] columnMembers; //hmmmmm
    private byte[] memberMap = new byte[(World.xSize*World.ySize)/8]; //Every **BIT** not byte is a column in book reading style top left to bottom right 1 for member 0 for not.
    //memberMap uses functions below to interpret from outside world beyond this class.
    //if using whole booleans, with like 40 plates in the world, it would be, say 3000x3000x40xbyte = 351 MB memory, significant portion of budget. This is 44 MB for 40 plates.
    
    public Plate(int numColumnsInWorld){
        //columnMembers = new boolean[numColumnsInWorld]; //defaults full of false;
    }
    
    public boolean isMember(short x, short y){
        //REMEMBER COORDINATES BEGIN AT ZERO
        //every y is World.xSize / 8 bytes in.
        //then x/8 rounded down bytes in, and x%8 bits in that byte.
        byte temp = memberMap[y*(World.xSize/8) + (int)Math.floor(x/8d)];
        return (temp & (byte)(128/Math.pow(2,x%8))) > 0;
    }
    
    public void setMember(int membership, short x, short y){ //membership 0 or 1
        int address = y*(World.xSize/8) + (int)Math.floor(x/8d);
        byte temp = memberMap[address];
        if (membership == 1){
            temp = (byte)(temp | (byte)(128/Math.pow(2,x%8)));
        } else{
            temp = (byte)(temp & ~((byte)(128/Math.pow(2,x%8))));
        }
        memberMap[address] = temp;
    }
    public void runThermo(){
        //set up storage of initial state so I can change temps afterward without changing what the next column is using as a reference point
        short[][][] tempTemps = new short[World.xSize][World.ySize][];
        for (short x = 0;x < World.xSize; x++){
            for (short y = 0; y < World.ySize; y++){
                if (isMember(x,y)){
                    Column tempColumn = World.columnMap.get(World.columns[x][y]);
                    short[] tempTempAdd = new short[tempColumn.goxels.length];
                    tempTemps[x][y] = tempTempAdd;
                    for (int g = 0; g < tempColumn.goxels.length; g++){
                        tempTemps[x][y][g] = tempColumn.goxels[g].temperature;
                    }
                }
            }
        }
        
        //Heat flow between each goxel and its neighbors
            //If bottom (no goxel neighbors below only, ignore sides for now): Heat flow between mantle and bottom goxels
            //If top (no goxel neighbors above only): Heat flow between each goxel and the atmosphere
                //Surface goxel radiation
        //Atmospheric radiation
        //Mantle decay new heat generation
        
        //Notes:
        //Heat flow was about 3x todays 4 billion BC, 2x today's 2.5 billion BC. This should translate to convection speed and everything speed as well.
        //see image in folder for good curve to use for earth. For other size planets just divide by mass and spread the terawatts out evenly,. Or chet for earthlike look.
        //internal heat flow is 0.03% of solar radiation today
        //4 billion BC, surface temp was about what it is today.
        //on earth, convecting mantle is ~3,000km thick and 84% of volume of the planet, so tiny portions of elements can equal huge % in the thin crust.
        //the atmosphere today cuts down about 80% of what would be radiated off otherwise. The moon loses 300 C in 2 hours by comparison during eclipse.
        //greenhouse gases are easy to look up and calculate insulation of the atmosphere for different chemical compsitions in the model.
        //it works because sunlight has a lot of visible which gas is transparent to, but radiant emissive energy is in IR which these gases are opaque to.
    }
}
