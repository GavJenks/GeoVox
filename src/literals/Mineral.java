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
    public int mineralClass; //0=igneous, 
    public float specificGravity;
    public byte[] commonChemistry;
    public byte[] rareChemistry;
    
    public Mineral(int mineralClass, int mineralID, String name, float hardness, short melt, float specificGravity, byte[] commonChemistry, byte[] rareChemistry){
        this.mineralClass = mineralClass;
        this.mineralID = mineralID;
        this.name = name;
        this.hardness = hardness;
        this.melt = melt;
        this.specificGravity = specificGravity;
        this.commonChemistry = commonChemistry;
        this.rareChemistry = rareChemistry;
    }
}
