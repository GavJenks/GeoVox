/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

import java.io.Serializable;
import utility.ChemCalculator;

/**
 *
 * @author Gavin
 */
public class Goxel implements Serializable{
//comments are for rock goxels for now. For other types like bio or ocean, many of these fields could have alternate meanings.

    byte type = 3; //type of goxel
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
    byte element0 = 102-128; //Oxygen 0-255 concentration BY MASS of element 1 (default Oxygen) in this goxel, assume for now numbers should add up to 255 unless rares present.
    byte element1 = 52-128; //Silicon
    byte element2 = 0-128; //Aluminum
    byte element3 = 33-128; //Iron
    byte element4 = 47-128; //Calcium
    byte element5 = 23-128; //Magnesium
    byte element6 = 0-128; //Sodium
    byte element7 = 0-128; //Potassium
    int rareChemistry = 0; //the rarer 16 elements, 2 BITS each so 0-3 for each. Assume that each one of these is the same fraction as one of the 255's above (~0.39%) so max rare % is a bit above 1% of goxel each
    short mineral1; //4 bits of mineral category GROUP code, 5 bits of proportions of group members (line or triangle distributino), 7 bits of 0-127 total group presence in goxel
    short mineral2;
    short mineral3; //####### TODO: fill in defaults for basalt once format worked out.
    short mineral4;
    short mineral5;
    short temperature = 1200; //in Celsius, signed
    short pressure; //in bars, signed.
    byte fraction = 0; //signed. For igneous goxels, this is in 1% increments the portion of the goxel molten. for sedimentary, portion eroded away, perhaps, etc.
    byte specificGravity = 29; //unsigned 0-255, in units of 1/10ths of the gravity of water. So normal 6.52 = 65 here. Water = 10 here, etc. Densest element in world is osmium at 22.48, so this handles any natural thing I think.

    //TODO: replace specific gravity, element, minerl stuff default settings with elementCalculator calls, all proper and procedural.
    //RAM tally: 1 + 8 + 4 + 14 + 2 + 12(object overhead) = 40 bytes, rounds to 40.
    
    public Goxel() { //assumes solid basalt with all the above default values
        //byte[] defaultElements = ElementCalculator.byMassFromMinerals(minerals, fractions);
    }
    
    public void setPressure(byte pressure){
        this.pressure = pressure;
    }

    public double elementToPercent(byte elementIndex) {
        switch (elementIndex) {
            case 0:
                return (this.element0+128) / 255d;
            case 1:
                return (this.element1+128) / 255d;
            case 2:
                return (this.element2+128) / 255d;
            case 3:
                return (this.element3+128) / 255d;
            case 4:
                return (this.element4+128) / 255d;
            case 5:
                return (this.element5+128) / 255d;
            case 6:
                return (this.element6+128) / 255d;
            case 7:
                return (this.element7+128) / 255d;
        }
        return -1;
    }

    public double rareToPercent(int rareElementIndex) { //index 0-15
        int temp = rareChemistry;
        temp = (temp>>(15-(rareElementIndex*2)));
        temp = temp&(int)3;
        return temp / (255.0d);
    }
    
    public int pressureToBars(){
        if (pressure > 0){
            return pressure*1000;
        }else if (pressure < 0){
            return pressure*-1;
        } else {
            return 0;
        }
    }
        
    public byte getBrittle(){
        //brittleness is not a field. This is for custom algorithms for things with odd values, like "river" or whatnot.
        //TODO: implement.
        return -128;
    }
}
