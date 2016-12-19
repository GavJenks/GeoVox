/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package utility;

import literals.Mineral;
import literals.Rock;
import literals.RockDictionary;

/**
 *
 * @author Gavin
 */
public class ChemCalculator {

    //need a massByElements. DON'T need a mass by minerals, already pretty simply done in goxel.
    
    
    //This gives you the loadout of common and rare elements by percent, if the whole goxel were composed of this one set of minerals in these fractions.
    //THIS IS PROBABLY WRONG, TODO: fix to use two-proportion-tiered system in place currently.
    public static byte[] byMassFromMinerals(Mineral[] minerals, float[] fractions) {
        int totalAtomicWeight = 0;
        int[] totalAtomicWeights = new int[24];
        for (int e = 0; e < 24; e++) { //per element
            if (e < 8) { //primary elements
                for (int m = 0; m < minerals.length; m++){ //per mineral
                    totalAtomicWeights[e] = (int)(minerals[m].commonChemistry[e]*fractions[m]*RockDictionary.elements.get(e).atomicWeight);
                    totalAtomicWeight += totalAtomicWeights[e];
                }
            } else { //secondary elements
                for (int m = 0; m < minerals.length; m++){ //per mineral
                    totalAtomicWeights[e] = (int)(minerals[m].rareChemistry[e]*fractions[m]*RockDictionary.elements.get(e).atomicWeight);
                    totalAtomicWeight += totalAtomicWeights[e];
                }
            }
        }
        byte[] result = new byte[24];
        double weightPerElementUnit = totalAtomicWeight / 100d;
        for (int e = 0; e < 24; e++) { //per element
            result[e] = (byte)((totalAtomicWeights[e]/weightPerElementUnit));
        }
        return result;
    }
    
    //same bt for a rock type
    public static byte[] byMassFromMinerals(Rock rock) {
        return byMassFromMinerals(rock.getMinerals(), rock.getFractions());
    }
}
