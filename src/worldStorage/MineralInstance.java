/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

/**
 *
 * @author Gavin
 */
public class MineralInstance {
    //mineral info within a particular goxel
    //4 bits of mineral category GROUP code, 5 bits of proportions of group members (line or triangle distributino), 7 bits of 0-127 total group presence in goxel
    public byte groupCode;
    public byte proportion;
    public float mass;
    
    public MineralInstance(byte groupCode, byte proportion, float mass){
        this.groupCode = groupCode;
        this.proportion = proportion;
        this.mass = mass;
    }
}
