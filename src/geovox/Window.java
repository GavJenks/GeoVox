package geovox;
        
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Window extends JPanel {

    private BufferedImage canvas;

    public Window(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillCanvas(Color.BLUE);
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
    
    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        this.setVisible(true);
        repaint();
    }
    
    public void setPixel(Color c, short x, short y){
        //does not repaint, do separately.
        canvas.setRGB(x, y, c.getRGB());
    }
    
    public void setPixelHeat(short heat, short x, short y){
        //heat 0-255
        //does not repaint, do separately.
        Color c = new Color(heat,0,255-heat);
        canvas.setRGB(x, y, c.getRGB());
    }


    //public void drawLine(Color c, int x1, int y1, int x2, int y2) {
    //    // Implement line drawing
    //    repaint();
    //}

    //public void drawOval(Color c, int x1, int y1, int width, int height) {
    //    // Implement oval drawing
    //    repaint();
    //}
}