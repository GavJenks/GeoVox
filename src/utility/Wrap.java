/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import worldStorage.World;

/**
 *
 * @author Gavin
 */
public class Wrap {

    public static double dist(short x1, short y1, short x2, short y2) {
        return Math.sqrt(Math.pow(Math.min(Math.abs(x1 - x2), World.xSize - Math.abs(x1 - x2)), 2) + Math.pow(Math.min(Math.abs(y1 - y2), World.ySize - Math.abs(y1 - y2)), 2));
    }
    
    public static double dist(Coord c1, Coord c2){
        return dist(c1.xCoord,c1.yCoord,c2.xCoord,c2.yCoord);
    }

    public static ArrayList<ScoredCoord> gradientCircle(ArrayList<Coord> coords, Coord center) {
        ArrayList<ScoredCoord> results = new ArrayList<ScoredCoord>();
        for (Coord c:coords){
            results.add(new ScoredCoord(c.xCoord,c.yCoord,(short)dist(c,center)));
        }
        return results;
    }

    public static void sortScoredCoords(ArrayList<ScoredCoord> coords) {
        Collections.sort(coords,new ScoredCoordsComparator());
    }

    public static class ScoredCoordsComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            ScoredCoord sc1 = (ScoredCoord)o1;
            ScoredCoord sc2 = (ScoredCoord)o2;
            return sc2.score-sc1.score;
        }
    }

    public static void fix(Coord c) {
        c.xCoord = (short) (c.xCoord % World.xSize);
        c.yCoord = (short) (c.xCoord % World.xSize);
        if (c.xCoord < 0) {
            c.xCoord = (short) (c.xCoord + World.xSize);
        }
        if (c.yCoord < 0) {
            c.yCoord = (short) (c.yCoord + World.ySize);
        }
    }
}
