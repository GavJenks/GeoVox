/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package utility;

/**
 *
 * @author Gavin
 */
public class ScoredCoord extends Coord{
    
    public short xCoord;
    public short yCoord;
    public int score;
    
    public ScoredCoord(short xCoord, short yCoord, int score){
        super(xCoord,yCoord);
        this.score = score;
    }
}
