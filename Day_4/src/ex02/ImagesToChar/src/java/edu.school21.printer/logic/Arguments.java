package edu.school21.printer.logic;

import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Arguments {
    @Parameter(
        names = "--white" , 
        description = "paramater number 1", 
        required = true)
    private String white;
    
    @Parameter(
        names = "--black", 
        description = "paramater number 2", 
        required = true)
    private String black;

    public String GetWhite() {
        return white;
    }
    public String GetBlack() {
        return black;
    }
}
