/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

import java.util.ArrayList;
import java.util.HashMap;
import utility.B;
import utility.Coord;
import utility.ScoredCoord;
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
    public static int initialThickness; //Of the CRUST. The lower lithosphere / supper mantle is pretty much just ignored here, since it's proportional everywhere more or less
    //and would waste a ton of RAM. floating on top of asthenosphere calculations should be modified as if it exists, though.

    //Collections:
    //current strategy is store ID of column at coords, then hash up the column from ID.
    //almost certainly faster than overwrit,ing columns constantly and storing them at absolute coords.
    public static HashMap<Integer,ArrayList<ScoredCoord>> voronoi; //closest plume to each coordinate pair..
    public static int[][] columns; //stores the ID # of a column at each coord. The columns move around
    public static short[][] score; //i.e. for pathfinding
    public static short[][] plumeHeat; //contribution of heat from plumes, to be added each turn to columns. units = "however many joules heat up one goxel of water 1 degree C"
    public static HashMap<Integer, Column> columnMap = new HashMap<Integer, Column>(); //use the id to get the actual column here.
    public static Plate[] plates = new Plate[1]; // stay below short number of plates.
    public static HashMap<Integer, Short[]> plumes = new HashMap<Integer, Short[]>(); //centers of mantle convection cells.

    //Objects
    public static LiquidMantle liquidMantle = new LiquidMantle();
    public static Atmosphere atmosphere = new Atmosphere();
    
    //notes on thickness:
    //mid ocean ridge = 0 depth of entire lithosphere. Further out ocean gets up to ~10km crust and another 40km litho. 
    //continental crust anywhere from 10 to 75
    //continental lithosphere anywhere from 40 to 250km
    //crust vs. rest of litho is characterized by the sudden discontinuity in chemistry from basalts to dunites, not gradual. "Moho" disctontinuity.

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

    public static void slowAssVoronoi() { //replace with O(nlogn) algorithm eventually? But doesn't need to get called every tick until/if plumes move around.
        //temp map holds which plumes each column is closest to.
        //then at bottom, each voronoi cell is given scores based on how far from the plume that that cell is based on.
        //the final numbers contribute both variable heat, and also double as vector contributions (faster as you go from plume)
        HashMap<Integer,ArrayList<Coord>> tempVoronoi = new HashMap<>();
        for (int p = 0; p < plumes.size(); p++){
            tempVoronoi.put(p,new ArrayList<Coord>());
        }
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
                voronoi.get(bestPlume).add(new ScoredCoord(x,y,0));
            }
        }
        for (int p = 0; p < plumes.size(); p++){
            voronoi.put(p,Wrap.gradientCircle(tempVoronoi.get(p),new Coord(plumes.get(p)[0],plumes.get(p)[1])));
        }
    }
}
