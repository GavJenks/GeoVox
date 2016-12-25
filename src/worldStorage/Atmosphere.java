/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

/**
 *
 * @author Gavin
 */
public class Atmosphere {
    //Eventually should keep track of chemical content, but for now, pretending it's just always similar to modern air
    //Only used for heat model at the moment (simplified heat model)
    
    //TODO: make this stuff procedural.
    public static byte specificHeat = 2; //in tenths of water, remember.
    public static float massPerColumn = 0.5f;//1/2352th density of air on AVERAGE between sea level and 5km altitude, so ~0.5 water goxel weights.
    public static float totalMass = World.xSize*World.ySize*massPerColumn;
    public static short temperature = 500; //early hot earth, just random number somewhat lower than 1200 for rocks below
}
