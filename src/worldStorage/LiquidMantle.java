/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

import literals.Rock;

/**
 *
 * @author Gavin
 */
public class LiquidMantle {
    //store info about elemental makeup etc. of upper mantle.
    //possibly radiation heating level
    //possibly lower mantle stuff too for long term shifts?
    public short temperature; //in Celsius, signed
    public float specificHeat; //Same units as normal, but much more precision for the mantle than a regular goxel.
    public float specificGravity; // ditto ^
    public int pressure; //in bars, signed. made into an int, not hort, but short MAY still be enough? 36 kilobars?
    public int rareChemistry = 0;
    public byte byte0;
    public byte byte1;
    public byte byte2;
    public byte byte3;
    public byte byte4;
    public byte byte5;
    public byte byte6;
    public byte byte7;
    
    Rock rockType; //average over whole mantle
    //can't hold properly needed precison, though... may need to have a method that counts over all existing goxels once in awhile and
    //figures out total largescale mineral portions contributed from the mantle, and subtracts it from this object.
}
