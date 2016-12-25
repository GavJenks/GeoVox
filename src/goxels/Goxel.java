/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package goxels;

import java.io.Serializable;
import java.util.ArrayList;
import literals.RockDictionary;
import worldStorage.MineralInstance;

/**
 *
 * @author Gavin
 */
public class Goxel implements Serializable {
//comments are for rock goxels for now. For other types like bio or ocean, many of these fields could have alternate meanings.

    public byte type; //type of goxel
    public short temperature; //in Celsius, signed
    public byte specificHeat;
    public byte specificGravity;
    public short pressure; //in bars, signed.
    public byte fraction = 0; //signed. For igneous goxels, this is in 1% increments the portion of the goxel molten. for sedimentary, portion eroded away, perhaps, etc.
    public int rareChemistry = 0; //the rarer 16 elements, 2 BITS each so 0-3 for each. Assume that each one of these is the same fraction as one of the 255's above (~0.39%) so max rare % is a bit above 1% of goxel each
    //RAM 24 bytes
    
    //**ALL TYPES DEFINED IN FUTURE MUST ADHERE TO RANGES BELOW** Other logic elsewhere relies on these.
    //**range 0 reserved for "air" don't know if ever will use, usually Atmosphere object assumed to just be above everything.
    //**range 1-30 reserved for solid rock of any type held together strongly/covalently/as crystals. Includes ice.
    //**range 31-60 reserved for any molten or semi-molten rock types (must always be at least semi-molten to qualify, upon solidification always switch types to 1-30 range)
    //**range 61-80 reserved for any pure LIQUID (not ice) water (or other planet's primary ocean forming liquid) types, including biological water types (where info fields used for species, etc.)
    //**range 81-120 reserved for any special terrestrial climate/landscape surface types of mixed composition, rivers, loose sand deserts, etc. INCLUDING terrestrial biological types
    //**range 120-127 reserved for bizarre miscellaneous types, but be aware that various logic areas in the code may ignore them. Avoid using if possible.
    //**NOTE that -1 to -128 is also available if need be. Logic will avoid assuming positive ranges in main code to allow for this.

    //0 = air if ever relevant,
    //1 = ice, whole goxel's composition (but fraction = erosion / partial air)
    //2 = igneous solid (fraction = erosion)
    //31 = igneous molten or semi-molten (fraction = amount solidified)
    //61 = default ocean
    //########TODO: replace with actual procedural element parser
    
    //TODO: replace specific gravity, element, minerl stuff default settings with elementCalculator calls, all proper and procedural.
    //RAM tally: 1 + 8 + 4 + 14 + 2 + 12(object overhead) = 40 bytes, rounds to 40.
    public Goxel() { //assumes solid basalt with all the above default values
        //byte[] defaultElements = ElementCalculator.byMassFromMinerals(minerals, fractions);
    }

    public double rareToPercent(int rareElementIndex) { //index 0-15
        int temp = rareChemistry;
        temp = (temp >> (15 - (rareElementIndex * 2)));
        temp = temp & (int) 3;
        return temp / (100d);
    }

}
