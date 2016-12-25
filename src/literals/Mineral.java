/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package literals;

/**
 *
 * @author Gavin
 */
public class Mineral {
    public int mineralID;
    public String name;
    public float hardness;
    public short melt;
    public byte fractureToughness;
    public int mineralClass; //0=igneous, 
    public float specificGravity;
    public byte[] commonChemistry;
    public byte[] rareChemistry;
    
    public Mineral(int mineralID, int mineralClass, String name, float hardness, short melt, byte fractureToughness, float specificGravity, byte[] commonChemistry, byte[] rareChemistry){
        this.mineralClass = mineralClass;
        this.mineralID = mineralID;
        this.name = name;
        this.hardness = hardness;
        this.melt = melt;
        this.fractureToughness = fractureToughness;
        this.specificGravity = specificGravity;
        this.commonChemistry = commonChemistry;
        this.rareChemistry = rareChemistry;
    }
}
