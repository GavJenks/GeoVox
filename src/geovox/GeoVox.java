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
        World world = new World(1500, 1000);
    }
    
}
