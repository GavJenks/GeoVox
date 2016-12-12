/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package worldStorage;

import java.util.HashMap;

/**
 *
 * @author Gavin
 */
public class World {
    public static int xSize;
    public static int ySize;
    public static int[][] columns; //stores the ID # of a column at each coord. The columns move around
    public static HashMap<Integer,Column> columnMap = new HashMap<Integer,Column>(); //use the id to get the column here.
    public static Plate[] plates;
    public static float goxelSize = 2; //in kilometers
    
    public World(int xSize, int ySize, int initialThickness, String initialRock){
        this.xSize = xSize;
        this.ySize = ySize;
        
        //initialize world... 5 goxel columns of basalt everywhere.
        columns = new int[xSize][ySize];
        int counter = 0;
        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++){
                columns[x][y] = counter;
                Column col = new Column(initialThickness);
                columnMap.put(counter, col);
                col.columnID = counter;
                col.currentX = x;
                col.currentY = y;
                col.plate = 0;                
            }
        }        
    }
}
