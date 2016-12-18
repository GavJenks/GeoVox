/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package utility;

/**
 *
 * @author Gavin
 */
public class Coord {

    public short xCoord;
    public short yCoord;

    public Coord(short xCoord, short yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coord)) {return false;}
        if (obj == this) {return true;}

        Coord coordObj = (Coord)obj;
        if (coordObj.xCoord == xCoord && coordObj.yCoord == yCoord){return true;}
        
        return false;
    }
}
