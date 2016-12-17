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
}
