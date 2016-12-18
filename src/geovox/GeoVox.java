/*
 * Copyright Gavin Jenkins
 * All Rights Reserved.
 */
package geovox;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import utility.B;
import worldStorage.World;

/**
 *
 * @author Gavin
 */
public class GeoVox {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //0 to 9  size 10
        B.ug(-1%10); //+10 since negative
        B.ug(-11%10); //+10 since negative
        B.ug(11%10);
        B.ug(0%10);
        B.ug(-20%10);
        
        //Memory feedback
        Runtime rt = Runtime.getRuntime();
        long maxMem = rt.maxMemory();
        double megs = 1048576.0;
        System.out.println ("Max Memory:   " + maxMem + " (" + (maxMem/megs) + " MiB)");
    
        //Hardcoded paramsfor now. 
        short xSize = 1600; //Sizes must be multiples of 8
        short ySize = 800;
        B.ug(System.currentTimeMillis());
        World world = new World(xSize, ySize, 2, 5, 20);
        B.ug(System.currentTimeMillis());
        
        //Graphics
        JFrame frame = new JFrame("Direct draw demo");
        Window panel = new Window(World.xSize, World.ySize);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //threads
        
        //draw plumes
        
        
        
    /*
        Window window = new Window();
        BufferedImage canvas = new BufferedImage(World.xSize, World.ySize, BufferedImage.TYPE_INT_ARGB);
    
        window.fill(canvas);
        try{
        Thread.sleep(5);
        }catch (Exception e){
        
        }
*/
        //init swing GUI
        //make hotspots
        //voronoi those hotspots
        //store flow vectors in every column.
        //Determine 2+ cracks
        //reassign plates
        
        //LOOP:
        
            //MULTITHREAD:
                //determine plate overall vectors
                //do local plate stuff:
                //do local temperature flow, radiation
                //do local erosion
                //do local volcanism
                //calculate column averages to get ready for worldwide calcs:
            //determine plate interactions (pushing force and contact heat) in one one thread
            //subduction choices
            //new pillow basalts added to plates.
            //between plate orogenesis: new volcanoes, crumple zones
            //Back to MULTITHREAD various petrogenesis:
                //do metamorphism now that we have all pressure and temp
                //do sediment formation
                //do crystallization igneous\
                //[this carries over to top of list still in threads]
                
    }
    
}
