/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package goxels;

import literals.Rock;
import literals.RockDictionary;

/**
 *
 * @author Gavin
 */
public class GoxelRockSolid extends GoxelLitho {
//comments are for rock goxels for now. For other types like bio or ocean, many of these fields could have alternate meanings.

    //byte0 = grain size 1-127
    //[Remaining bytes unused so far for this type]
    
    //fraction: For solid, this is 1% increments of eroded-away goxel (either caves or portion off the top goxel)
    
    //TODO: replace specific gravity, element, minerl stuff default settings with elementCalculator calls, all proper and procedural.
    //RAM tally: 1 + 8 + 4 + 14 + 2 + 12(object overhead) = 40 bytes, rounds to 40.
    
    public GoxelRockSolid(){
        
    }
    
    public GoxelRockSolid(String rockName){
        Rock rock = RockDictionary.rocks.get(rockName);
        
    }
    
    
    
    
    //public void fillMinerals(String rockName){
        //TODO: imeplement. Look up rock name in dictionary and fill up mineral1 through mineral5 with appropriate numbers.
        //used only when hardcode spawning goxels, probably only ever basalt, though, so... maybe ignore, since already defaults for that built into GoxelIgneousSolid
    //}
    
    
}
