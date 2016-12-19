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

/**
 *
 * @author Gavin
 */
public class RockDictionary {
    public static ArrayList<Element> elements = new ArrayList<Element>();
    public static ArrayList<Mineral> minerals = new ArrayList<Mineral>();
    public static ArrayList<Integer> mineralGroupSizes = new ArrayList<Integer>(); //TODO:Implement. Include "group" for EVERY mineral. lone ones just have group size = 1;
    public static HashMap<String,Rock> rocks = new HashMap<String,Rock>(); //TODO: implement, also should have ID based map probably.
    
    //1) load up minerals
    public static void loadMinerals() {
        //########### MAKE MINERAL ZERO HAVE ZERO MASS
        try{
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
                minerals.add(new Mineral(Integer.parseInt(parts[1]),Integer.parseInt(parts[0]),parts[2],Float.parseFloat(parts[3]),Short.parseShort(parts[4]),Byte.parseByte(parts[5]),Float.parseFloat(parts[6]),commonMinerals,rareMinerals));
            }
        }
        }catch (IOException e){
        }
    }

    //2) load up elements, including calcing specific gravity
    //3) load up rocks
    //4) create interpolated rock dictionary
}
