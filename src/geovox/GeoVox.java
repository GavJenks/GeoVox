/*
 * Copyright Gavin Jenkins
 * All Rights Reserved.
 */
package geovox;
import worldStorage.World;

/**
 *
 * @author Gavin
 */
public class GeoVox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        int xSize = 1500; //should be specified eventually in a GUI by user, etc., hence just hardcoding for now. This is in goxel sized units.
        int ySize = 1000;
        World world = new World(xSize, ySize, 5, 2);
        //init swing GUI
        //make hotspots
        //voronoi those hotspots
        //store flow vectors in every column.
        //Determine 2+ cracks
        //reassign plates
        //determine plate overall vectors
        //decide subduction
        //implement new pillow basalt and volcanism
        //....
    }
    
}
