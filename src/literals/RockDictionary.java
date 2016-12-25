/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package literals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import static javafx.scene.input.KeyCode.R;

/**
 *
 * @author Gavin
 */
public class RockDictionary {
    public static ArrayList<Element> elements = new ArrayList<Element>();
    public static ArrayList<Mineral> minerals = new ArrayList<Mineral>();
    public static HashMap<String, Integer> mineralStringToID = new HashMap<>();
    public static ArrayList<Integer> mineralGroupSizes = new ArrayList<Integer>(); //TODO:Implement. Include "group" for EVERY mineral. lone ones just have group size = 1;
    public static HashMap<String,Rock> rocks = new HashMap<String,Rock>(); //TODO: implement, also should have ID based map probably.
    
    //1) load up minerals
    public static void loadMinerals() {
        //########### MAKE MINERAL ZERO HAVE ZERO MASS
        try{
        int mineralID = 0;
        for (String line : Files.readAllLines(Paths.get("Minerals.txt"))) {
            if (!line.isEmpty() && !line.startsWith("%")){
                String[] parts = line.split(",");
                byte[] commonMinerals = new byte[8];
                byte[] rareMinerals = new byte[16];
                for (int i = 0; i < 8;i++){
                    commonMinerals[i] = Byte.parseByte(parts[i+7]);
                }
                for (int i = 0; i < 16;i++){
                    rareMinerals[i] = Byte.parseByte(parts[i+15]);
                }
                minerals.add(new Mineral(mineralID, Integer.parseInt(parts[0]),parts[1],Float.parseFloat(parts[2]),Short.parseShort(parts[3]),Byte.parseByte(parts[4]),Float.parseFloat(parts[5]),commonMinerals,rareMinerals));
                mineralStringToID.put(parts[1],mineralID);
                mineralID++;
            }
        }
        }catch (IOException e){
        }
    }

    //2) load up elements, including calcing specific gravity
    public static void loadElements(){
        try{
        int elementID = 0;
        for (String line : Files.readAllLines(Paths.get("Elements.txt"))) {
            if (!line.isEmpty() && !line.startsWith("%")){
                String[] parts = line.split(",");
                String name = parts[0];
                int atomicWeight = Integer.parseInt(parts[1]);
                float percentCrust = Float.parseFloat(parts[2]);
                byte percentExponentCrust = (byte)Math.log(percentCrust); //percent in rareElements format is approximated by the two rare bits x 10^(this byte)
                float percentMantle = Float.parseFloat(parts[2]);
                byte percentExponentMantle = (byte)Math.log(percentMantle);
                elements.add(new Element(elementID, name, atomicWeight, percentCrust, percentExponentCrust, percentMantle, percentExponentMantle));
                elementID++;
            }
        }
        }catch (IOException e){
        }
    }
    
    //3) load up rocks
    public static void loadRocks(){
        try{
        int rockID = 0;
        for (String line : Files.readAllLines(Paths.get("Rocks.txt"))) {
            if (!line.isEmpty() && !line.startsWith("%")){
                String[] parts = line.split(",");
                short[] mineralShorts = new short[5];
                for (int m = 0; m < 5; m++){
                    Mineral mineral = minerals.get(m);
                    
                    int 
                }
                rocks.put(new Rock(rockID, parts[0], Byte.parseByte(parts[1]), parts[2], Short.parseShort));
                rockID++;
            }
        }
        }catch (IOException e){
        }
    }
    
    //4) create rock math, particularly averaging two rocks
}
