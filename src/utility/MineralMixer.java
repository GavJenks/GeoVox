/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gavin
 */
public class MineralMixer {
    // A at bottom left of triangle, B at top, C at bottom right, points counter from bottom left toward right then up one row, etc.
        private static final byte[][] triangle = new byte[][]{{100,0,0},{75,0,25},{50,0,50},{25,0,75},{0,0,100},{84,8,8},{58,8,33},{33,8,58},{8,8,84},
            {67,17,17},{42,17,42},{17,17,67},{75,25,0},{50,25,25},{25,25,50},{0,25,75},{58,33,8},{33,33,33},{8,33,58},{42,42,17},{17,42,42},{50,50,0},
            {25,50,25},{0,50,50},{33,58,8},{8,58,33},{17,67,17},{25,75,0},{0,75,25},{8,84,8},{0,100,0}};
        
        private static final Map<Integer, Byte> reverseTriangle;
        static
        {
            Map<Integer, Byte>tempMap = new HashMap<Integer, Byte>();
            tempMap.put(100<<16+  0<<8+   0,  (byte)0);
            tempMap.put(75<<16+   0<<8+   25, (byte)1);
            tempMap.put(50<<16+   0<<8+   50, (byte)2);
            tempMap.put(25<<16+   0<<8+   75, (byte)3);
            tempMap.put(0<<16+    0<<8+   100,(byte)4);
            tempMap.put(84<<16+   8<<8+   8,  (byte)5);
            tempMap.put(58<<16+   8<<8+   33, (byte)6);
            tempMap.put(33<<16+   8<<8+   58, (byte)7);
            tempMap.put(8<<16+    8<<8+   84, (byte)8);
            tempMap.put(67<<16+   17<<8+  17, (byte)9);
            tempMap.put(42<<16+   17<<8+  42, (byte)10);
            tempMap.put(17<<16+   17<<8+  67, (byte)11);
            tempMap.put(75<<16+   25<<8+  0,  (byte)12);
            tempMap.put(50<<16+   25<<8+  25, (byte)13);
            tempMap.put(25<<16+   25<<8+  50, (byte)14);
            tempMap.put(0<<16+    25<<8+  75, (byte)15);
            tempMap.put(58<<16+   33<<8+  8,  (byte)16);
            tempMap.put(33<<16+   33<<8+  33, (byte)17);
            tempMap.put(8<<16+    33<<8+  58, (byte)18);
            tempMap.put(42<<16+   42<<8+  17, (byte)19);
            tempMap.put(17<<16+   42<<8+  42, (byte)20);
            tempMap.put(50<<16+   50<<8+  0,  (byte)21);
            tempMap.put(25<<16+   50<<8+  25, (byte)22);
            tempMap.put(0<<16+    50<<8+  50, (byte)23);
            tempMap.put(33<<16+   58<<8+  8,  (byte)24);
            tempMap.put(8<<16+    58<<8+  33, (byte)25);
            tempMap.put(17<<16+   67<<8+  17, (byte)26);
            tempMap.put(25<<16+   75<<8+  0,  (byte)27);
            tempMap.put(0<<16+    75<<8+  25, (byte)28);
            tempMap.put(8<<16+    84<<8+  8,  (byte)29);
            tempMap.put(0<<16+    100<<8+ 0,  (byte)30);
            reverseTriangle = Collections.unmodifiableMap(tempMap);
        }
              
        public static byte toTriangle(byte a, byte b, byte c){ // a,b,c, in any units, ratios are what matter
            int sum = a+b+c;
            //divide into nearest increments of: 0,8,17,25,33,42,50,58,67,75,84,100 by dividing by 8.35 and rounding
            int ap = (int)Math.round(((a/((float)sum))*100)/8.35);
            if (ap == 92){
                ap = 100;
                b = 0;
                c = 0;
            }
            int bp = (int)Math.round(((b/((float)sum))*100)/8.35);
            if (bp == 92){
                bp = 100;
                a = 0;
                c = 0;
            }
            int cp = (int)Math.round(((c/((float)sum))*100)/8.35);
            if (cp == 92){
                cp = 100;
                a = 0;
                b = 0;
            }
            int key = ap<<16+    bp<<8+  cp;
            return reverseTriangle.get(key);
        }
    
        public static byte[] fromTriangle(byte t){ 
            byte a = triangle[t][0];
            byte b = triangle[t][1];
            byte c = triangle[t][2];
            return new byte[]{a,b,c};
        }
        
        public static byte toLine(byte a, byte b){
            return (byte)((a/((float)b))*3.2258);
        }
        
        public static byte[] fromLine(byte l){
            return new byte[]{(byte)(l*3.2258),(byte)(100-(byte)(l*3.2258))};
        }
    }

