/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package utility;

import java.util.ArrayList;
import java.util.HashMap;
import worldStorage.World;

public class AStar {

    public ArrayList<Coord> endPath = new ArrayList<Coord>();
    public ArrayList<Coord> closedSet = new ArrayList<Coord>();
    public ArrayList<Coord> openSet = new ArrayList<Coord>();
    public HashMap<Coord, Coord> cameFrom = new HashMap<Coord, Coord>();
    public HashMap<Coord, Short> gScore = new HashMap<Coord, Short>();//public ArrayList<ScoredCoord> gScore = new ArrayList<ScoredCoord>();
    public ArrayList<ScoredCoord> fScore;// = new ArrayList<ScoredCoord>(); //lowest scores first
    public short[][] costs;

    public AStar(short[][] costs, Coord start, Coord goal) {
        ArrayList<Coord> tempCoords = new ArrayList<Coord>();
        this.costs = costs;
        for (short x = 0; x < World.xSize; x++) {
            for (short y = 0; y < World.ySize; y++) {
                gScore.put(new Coord(x, y), Short.MAX_VALUE); //default ~infinity
                tempCoords.add(new Coord(x, y));
            }
        }
        fScore = (Wrap.gradientOval(tempCoords, start, goal));
        Wrap.sortScoredCoords(fScore); //fScore now stores all points' distances Euclidean (torus-wrapped) from the start point + to the end point, which is the default guess heuristic of A*
    }

    private ArrayList<Coord> findPath(Coord start, Coord goal) {
        openSet.add(start);
        while (!openSet.isEmpty()) {
            int i = 0;
            //find lowest fscore node in open set
            while (!openSet.contains(fScore.get(i))) { //equals has been overriden here to compare coordinate fields
                i++; //since fScore is already sorted by ascending fScore, it will be choosing points near the start expanding outward.
                if (i == fScore.size()) {
                    System.out.println("Something wrong with A*, openSet has an entry that fScore does not");
                }
            }
            Coord current = fScore.get(i);
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }
            openSet.remove(current);
            closedSet.add(current);

            for (Coord neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                short tentativeGScore = (short) (gScore.get(current) + costs[neighbor.xCoord][neighbor.yCoord]); 
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeGScore >= gScore.get(neighbor)) {
                    continue;
                }
                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentativeGScore);
                short fScoreToStore = (short) (gScore.get(neighbor) + Wrap.dist(neighbor.xCoord, neighbor.yCoord, goal.xCoord, goal.yCoord));
                int j = 0;
                while (fScore.get(j).score < fScoreToStore) {
                    j++;
                }
                fScore.add(j, new ScoredCoord(neighbor.xCoord, neighbor.yCoord, fScoreToStore));
            }
        }
        return null;
    }

    private ArrayList<Coord> getNeighbors(Coord center) {
        ArrayList<Coord> neighbors = new ArrayList<Coord>();
        for (short x = (short) (center.xCoord - 1); x < center.xCoord + 2; x++) {
            for (short y = (short) (center.yCoord - 1); y < center.yCoord + 2; y++) {
                Coord temp = new Coord(x, y);
                Wrap.fix(temp);
                neighbors.add(temp);
            }
        }
        return neighbors;
    }

    private ArrayList<Coord> reconstructPath(HashMap<Coord, Coord> cameFrom, Coord current) {
        ArrayList<Coord> finalPath = new ArrayList<Coord>();
        finalPath.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            finalPath.add(current);
        }
        return finalPath;
    }
}