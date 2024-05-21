package edu.school21.printer.logic;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Process {
    private int width;
    private int height;
    private String symbEmpty;
    private String symbFill;

    public Process() {}

    public void Read(String filename, String symbFill, String symbEmpty) throws IOException {
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
                if(image.getRGB(j, i) != -1) System.out.print(symbEmpty);
                else System.out.print(symbFill);
            }
            System.out.println();
        } 
    }
}

