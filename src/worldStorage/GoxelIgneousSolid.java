/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

/**
 *
 * @author Gavin
 */
public class GoxelIgneousSolid extends GoxelRockSolid {
//comments are for rock goxels for now. For other types like bio or ocean, many of these fields could have alternate meanings.

    public GoxelIgneousSolid() {
        type = 2;
    }

    public GoxelIgneousSolid(String rockName) {
        //USE ROCK CLASS METHODS to get mineral list, fractions, and use to procedurally fill in
        //byte0 grainsize, and rarechemistry and minerals
        //TODO: implement
    }
}
