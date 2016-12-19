/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

/**
 *
 * @author Gavin
 */
public class GoxelRockSolid extends Goxel {
//comments are for rock goxels for now. For other types like bio or ocean, many of these fields could have alternate meanings.

    //byte0 = grain size 1-127
    //[Remaining bytes unused so far for this type]
    
    //fraction: For solid, this is 1% increments of eroded-away goxel (either caves or portion off the top goxel)
    
    //TODO: replace specific gravity, element, minerl stuff default settings with elementCalculator calls, all proper and procedural.
    //RAM tally: 1 + 8 + 4 + 14 + 2 + 12(object overhead) = 40 bytes, rounds to 40.
    
    @Override
    public byte getBrittle() {
        fraction = 0;
        //brittleness is not a field. This is for custom algorithms for things with odd values, like "river" or whatnot.
        //TODO: implement.
        return -128;
    }
    
    //public void fillMinerals(String rockName){
        //TODO: imeplement. Look up rock name in dictionary and fill up mineral1 through mineral5 with appropriate numbers.
        //used only when hardcode spawning goxels, probably only ever basalt, though, so... maybe ignore, since already defaults for that built into GoxelIgneousSolid
    //}
    
    
}
