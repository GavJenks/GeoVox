/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package literals;

import java.util.ArrayList;
import worldStorage.MineralInstance;

/**
 *
 * @author Gavin
 */
public class Rock {

    public short mineral1; //4 bits of mineral category GROUP code, 5 bits of proportions of group members (line or triangle distributino), 7 bits of 0-127 total group presence in goxel
    public short mineral2;
    public short mineral3; //####### TODO: fill in defaults for basalt once format worked out.
    public short mineral4;
    public short mineral5;
    public byte grain;

    public Rock(int rockID, String rockName, byte grain, short mineral1, short mineral2, short mineral3, short mineral4, short mineral5) {
        this.mineral1 = mineral1;
        this.mineral2 = mineral2;
        this.mineral3 = mineral3;
        this.mineral4 = mineral4;
        this.mineral5 = mineral5;
        this.grain = grain;
        //stuff like brittleness, etc. can go here, and in between rocks can be extrapolated from it.
        //*******
    }

    public Mineral[] getMinerals() {
        return null;
        //TODO: implement
    }

    public float[] getFractions() {
        return null;
        //TODO: implement
    }

    public byte getBrittle() {

        //brittleness is not a field. This is for custom algorithms for things with odd values, like "river" or whatnot.
        //TODO: implement.
        return -128;
    }

    public ArrayList<MineralInstance> getMineralInstances() {
        ArrayList<MineralInstance> instances = new ArrayList<MineralInstance>();
        byte groupID = (byte) (mineral1 >> 12);
        byte proportion = (byte) ((mineral1 & 3968) / 100f);
        float mass = RockDictionary.minerals.get(groupID).specificGravity * (proportion / 100f);
        instances.add(new MineralInstance(groupID, proportion, mass));

        groupID = (byte) (mineral2 >> 12);
        proportion = (byte) ((mineral2 & 3968) / 100f);
        mass = RockDictionary.minerals.get(groupID).specificGravity * (proportion / 100f);
        instances.add(new MineralInstance(groupID, proportion, mass));

        groupID = (byte) (mineral3 >> 12);
        proportion = (byte) ((mineral3 & 3968) / 100f);
        mass = RockDictionary.minerals.get(groupID).specificGravity * (proportion / 100f);
        instances.add(new MineralInstance(groupID, proportion, mass));

        groupID = (byte) (mineral4 >> 12);
        proportion = (byte) ((mineral4 & 3968) / 100f);
        mass = RockDictionary.minerals.get(groupID).specificGravity * (proportion / 100f);
        instances.add(new MineralInstance(groupID, proportion, mass));

        groupID = (byte) (mineral5 >> 12);
        proportion = (byte) ((mineral5 & 3968) / 100f);
        mass = RockDictionary.minerals.get(groupID).specificGravity * (proportion / 100f);
        instances.add(new MineralInstance(groupID, proportion, mass));

        return instances;
    }

}
