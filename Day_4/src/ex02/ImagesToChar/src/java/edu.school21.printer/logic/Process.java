package edu.school21.printer.logic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class Process {
    private int width;
    private int height;
    private String symbEmpty;
    private String symbFill;
    private String filename = "target/resources/it.bmp";
    private Map<String, String> colorsMap = new HashMap<>();
    
    public Process() {
        colorsMap.put("RED"    , colorize("  ", RED_BACK()));
        colorsMap.put("GREEN"  , colorize("  ", GREEN_BACK()));
        colorsMap.put("YELLOW" , colorize("  ", YELLOW_BACK()));
        colorsMap.put("BLUE"   , colorize("  ", BLUE_BACK()));
        colorsMap.put("MAGENTA", colorize("  ", MAGENTA_BACK()));
        colorsMap.put("CYAN"   , colorize("  ", CYAN_BACK()));   
    }

    public void Read(String symbFill, String symbEmpty) throws IOException {
        this.symbEmpty = symbEmpty;
        this.symbFill = symbFill;
        try {
            File f = new File(filename);
            BufferedImage image = ImageIO.read(f);
            this.width  = image.getWidth();
            this.height = image.getHeight();
            Print(image);
         } catch (IOException ex) {
            System.out.println("Interrupted: " + ex.getMessage());
        }
    }

    private void Print(BufferedImage image) {
        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {
                if(image.getRGB(j, i) != -1) {
                    String colorW = colorsMap.get(symbEmpty);
                    if(colorW == null) {
                        System.out.println("Please enter correct informations!");
                        System.exit(-1);
                    }
                    System.out.print(colorW);
                } else {
                    String colorB = colorsMap.get(symbFill);
                    if(colorB == null) {
                        System.out.println("Please enter correct informations!");
                        System.exit(-1);
                    }
                    System.out.print(colorB);
                }
            }
            System.out.println();
        } 
    }
}
