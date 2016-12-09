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
    public int xSize;
    public int ySize;
    public int[][] columns; //stores the ID # of a column at each coord. The columns move around
    public HashMap<Integer,Column> columnMap = new HashMap<Integer,Column>(); //use the id to get the column here.
    public Plate[] plates;
    
    public World(int xSize, int ySize, int initialThickness, String initialRock){
        this.xSize = xSize;
        this.ySize = ySize;
        
        //initialize world... 5 goxel columns of basalt everywhere.
        columns = new int[xSize][ySize];
        int counter = 0;
        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++){
                columns[x][y] = counter;
                Column col = new Column(initialThickness,initialRock);
                columnMap.put(counter, col);
                col.columnID = counter;
                col.currentX = x;
                col.currentY = y;
                col.plate = 0;                
            }
        }        
    }
}
