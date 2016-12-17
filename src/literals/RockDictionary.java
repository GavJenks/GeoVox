/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package literals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Gavin
 */
public class RockDictionary {
    public static ArrayList<Element> elements = new ArrayList<Element>();
    public static ArrayList<Mineral> minerals = new ArrayList<Mineral>();

    //1) load up elements
    public static void loadMinerals() {
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

    //2) load up minerals
    //3) load up rocks
    //4) create interpolated rock dictionary
}
