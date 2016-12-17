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
        
        
        Runtime rt = Runtime.getRuntime();
        long totalMem = rt.totalMemory();
        long maxMem = rt.maxMemory();
        long freeMem = rt.freeMemory();
        double megs = 1048576.0;

        System.out.println ("Total Memory: " + totalMem + " (" + (totalMem/megs) + " MiB)");
        System.out.println ("Max Memory:   " + maxMem + " (" + (maxMem/megs) + " MiB)");
        System.out.println ("Free Memory:  " + freeMem + " (" + (freeMem/megs) + " MiB)");
    
        //sizes must be multiples of 8
        short xSize = 1000; //should be specified eventually in a GUI by user, etc., hence just hardcoding for now. This is in goxel sized units.
        short ySize = 1000;
        B.ug(System.currentTimeMillis());
        World world = new World(xSize, ySize, 5, 2, 17);
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
