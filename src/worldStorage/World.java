/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

import java.util.HashMap;
import utility.B;
import utility.Wrap;

/**
 *
 * @author Gavin
 */
public class World {

    //basic constants:
    public static short xSize; //MUST BE MULTIPLE OF 8!
    public static short ySize; //MUST BE MULTIPLE OF 8!
    public static float goxelSize; //in kilometers
    public static int initialThickness;

    //Collections:
    //current strategy is store ID of column at coords, then hash up the column from ID.
    //almost certainly faster than overwriting columns constantly and storing them at absolute coords.
    public static int[][] voronoi; //closest plume to each coordinate pair.
    public static int[][] columns; //stores the ID # of a column at each coord. The columns move around
    public static short[][] score; //i.e. for pathfinding
    public static short[][] plumeHeat; //contribution of heat from plumes, to be added each turn to columns. units = "however many joules heat up one goxel of water 1 degree C"
    public static HashMap<Integer, Column> columnMap = new HashMap<Integer, Column>(); //use the id to get the actual column here.
    public static Plate[] plates = new Plate[1]; // stay below short number of plates.
    public static HashMap<Integer, Short[]> plumes = new HashMap<Integer, Short[]>(); //centers of mantle convection cells.

    //Objects
    public static Mantle mantle = new Mantle();
    public static Atmosphere atmosphere = new Atmosphere();

    public World(short xSize, short ySize, int initialThickness, int goxelSize, int numPlumes) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.goxelSize = goxelSize;
        this.initialThickness = initialThickness;
        plates[0] = new Plate(xSize * ySize);

        //initialize world... 5 goxel columns of basalt everywhere.
        columns = new int[xSize][ySize];
        int counter = 0;
        for (short x = 0; x < xSize; x++) {
            for (short y = 0; y < ySize; y++) {
                columns[x][y] = counter;
                Column col = new Column(this.initialThickness);
                columnMap.put(counter, col);
                col.columnID = counter;
                col.currentX = x;
                col.currentY = y;
                col.plate = 0;
                plates[0].setMember(1, x, y); //NOTE: raw coords like this without hashmap only valid in this first initialization. Normally need to consider both wrapmath and hashmap
                counter++;
            }
        }
        for (int p = 0; p < numPlumes; p++) {
            short tempX = (short) (Math.random() * xSize);
            short tempY = (short) (Math.random() * ySize);
            plumes.put(p, new Short[]{tempX, tempY});
        }
        B.ug("done");
    }

    public static void formFaults(int numSeeds, double minimumAverageScore, boolean firstFaults) {
        //first faults if true requires 2 faults no matter what, will just choose the highest scoring
        //two seeds regardless of minumum average score being met. Though if more than two faults
        //meet minimum average, more than two will still form.
        for (int seed = 0; seed < numSeeds; seed++) {
            //TODO Implement
        }
    }

    public static void slowAssVoronoi() { //replace with O(nlogn) algorithm eventually. But doesn't need to get called every tick until/if plumes move around.
        for (short x = 0; x < xSize; x++) {
            for (short y = 0; y < ySize; y++) {
                double bestDistance = Double.MIN_VALUE;
                int bestPlume = -1;
                for (int p = 0; p < plumes.size(); p++) {
                    Short[] pCoords = plumes.get(p);
                    double tempDistance = Wrap.dist(x, y, pCoords[0], pCoords[1]);
                    if (tempDistance < bestDistance) {
                        bestDistance = tempDistance;
                        //TODO: set heat gain here AND vectors are faster futher from plume? Only if plumes do move every tick though, and will have better algorithm then anyway.
                        bestPlume = p;
                    }
                }
                voronoi[x][y]=bestPlume;
            }
        }
    }
}
