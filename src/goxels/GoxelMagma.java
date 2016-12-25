/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package goxels;

import goxels.Goxel;
import java.io.Serializable;
import java.util.ArrayList;
import literals.RockDictionary;
import utility.ChemCalculator;

/**
 *
 * @author Gavin
 */
public class GoxelMagma extends GoxelLitho {
//comments are for rock goxels for now. For other types like bio or ocean, many of these fields could have alternate meanings.

    //ELEMENTS present in still molten form:
    public byte byte0;
    public byte byte1;
    public byte byte2;
    public byte byte3;
    public byte byte4;
    public byte byte5;
    public byte byte6;
    public byte byte7;
    
    //byte0 = Oxygen 0-255 concentration BY MASS of element 1 (default Oxygen) in this goxel, assume for now numbers should add up to 255 unless rares present.
    //byte1 = Silicon
    //byte2 = Aluminum
    //byte3 = Iron
    //byte4 = Calcium
    //byte5 = Magnesium
    //byte6 = Sodium
    //byte7 = Potassium
    // fraction: For igneous molten goxels, this is in 1% increments the portion of the goxel molten. 

    //TODO: replace specific gravity, element, minerl stuff default settings with elementCalculator calls, all proper and procedural.
    //RAM tally: 1 + 8 + 4 + 14 + 2 + 12(object overhead) = 40 bytes, rounds to 40.
    public GoxelMagma() {
        type = 31;
        temperature = 1200;
        specificGravity = 29;
        byte[] chemicalPercentages = ChemCalculator.byMassFromMinerals(RockDictionary.rocks.get("Basalt"));
        byte0 = chemicalPercentages[0];
        byte1 = chemicalPercentages[1];
        byte2 = chemicalPercentages[2];
        byte3 = chemicalPercentages[3];
        byte4 = chemicalPercentages[4];
        byte5 = chemicalPercentages[5];
        byte6 = chemicalPercentages[6];
        byte7 = chemicalPercentages[7];
        for (int i = 0; i < 16; i++) {
            rareChemistry = rareChemistry | (chemicalPercentages[i] << (30 - 2 * (i - 1)));
        }
    }

    public double elementToPercent(byte elementIndex) {
        switch (elementIndex) {
            case 0:
                return (this.byte0 + 128) / 100d;
            case 1:
                return (this.byte1 + 128) / 100d;
            case 2:
                return (this.byte2 + 128) / 100d;
            case 3:
                return (this.byte3 + 128) / 100d;
            case 4:
                return (this.byte4 + 128) / 100d;
            case 5:
                return (this.byte5 + 128) / 100d;
            case 6:
                return (this.byte6 + 128) / 100d;
            case 7:
                return (this.byte7 + 128) / 100d;
        }
        return -1;
    }

    public float getMassFromSpareElements() {
        float totalMass = 0;
        totalMass = ((byte0 + 128) / 100f) * RockDictionary.elements.get(0).specificGravity;
        totalMass = ((byte1 + 128) / 100f) * RockDictionary.elements.get(1).specificGravity;
        totalMass = ((byte2 + 128) / 100f) * RockDictionary.elements.get(2).specificGravity;
        totalMass = ((byte3 + 128) / 100f) * RockDictionary.elements.get(3).specificGravity;
        totalMass = ((byte4 + 128) / 100f) * RockDictionary.elements.get(4).specificGravity;
        totalMass = ((byte5 + 128) / 100f) * RockDictionary.elements.get(5).specificGravity;
        totalMass = ((byte6 + 128) / 100f) * RockDictionary.elements.get(6).specificGravity;
        totalMass = ((byte7 + 128) / 100f) * RockDictionary.elements.get(7).specificGravity;
        //just ignore mass of rares.
        return totalMass;
    }

}
