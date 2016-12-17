/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package utility;

import literals.Mineral;
import literals.RockDictionary;

/**
 *
 * @author Gavin
 */
public class ChemCalculator {

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
        double weightPerElementUnit = totalAtomicWeight / 256d;
        for (int e = 0; e < 24; e++) { //per element
            result[e] = (byte)((totalAtomicWeights[e]/weightPerElementUnit)-128);
        }
        return result;
    }
}
