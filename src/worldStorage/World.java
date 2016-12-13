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
    public static Plate[] plates = new Plate[1];
    public static float goxelSize; //in kilometers
    public static Mantle mantle = new Mantle();
    public static int initialThickness;
    
    public World(int xSize, int ySize, int initialThickness, int goxelSize){
        this.xSize = xSize;
        this.ySize = ySize;
        this.goxelSize = goxelSize;
        this.initialThickness = initialThickness;
        plates[0] = new Plate(xSize*ySize);
        
        //initialize world... 5 goxel columns of basalt everywhere.
        columns = new int[xSize][ySize];
        int counter = 0;
        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++){
                columns[x][y] = counter;
                Column col = new Column(this.initialThickness);
                columnMap.put(counter, col);
                col.columnID = counter;
                col.currentX = x;
                col.currentY = y;
                col.plate = 0;   
                plates[0].columnMembers[counter]=true;
            }
        }        
    }
}
