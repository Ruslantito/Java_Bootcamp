package ex00;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Process {
    private static final int SIZE = 150;
    private static final String SIGNATURES = "ex00/signatures.txt";
    private static final String OUTPUT = "ex00/result.txt";
    private String dataFromFile = new String();
    private Map<String, String> mapSignatures = new HashMap<>();

    public Process() {
        SetDictionary(SIGNATURES);
    }

    private void SetDictionary(String filePath) {
        try (InputStream streamInput = new FileInputStream(filePath)) {
            Scanner buffer = new Scanner(streamInput);
            while(buffer.hasNext()) {
                String tmp = buffer.nextLine();
                if(tmp.contains(",")) {
                    String[] pairTmp = tmp.split(",");
                    mapSignatures.put(pairTmp[1].replaceAll("\\s", ""), pairTmp[0]);
                }
            }            
            buffer.close();
            streamInput.close();
        }
        catch(IOException ex){              
            System.out.println(ex.getMessage());
        }
    }
     
    public void Read(String fileName) throws FileNotFoundException, IOException {
        try (InputStream streamInput = new FileInputStream(fileName)) {
            byte[] buffer = new byte[SIZE];
            streamInput.read(buffer);
            dataFromFile = "";
            for(byte aByte : buffer) {
                dataFromFile += String.format("%02x", aByte);
            }
            Compare();
            streamInput.close();
        }
        catch(IOException ex){              
            System.out.println(ex.getMessage());
        }        
    }

    private void Save(String dataForSave) {          
        try(FileOutputStream streamOut = new FileOutputStream(OUTPUT, true)) {
            streamOut.write(dataForSave.getBytes());
            streamOut.write("\n".getBytes());
            streamOut.close();
        }
        catch(IOException ex){              
            System.out.println(ex.getMessage());
        }        
    }

    private void Compare() {
        boolean isPreesent = false;
        for (Map.Entry<String, String> entry : mapSignatures.entrySet()) {
            String key = entry.getKey().toLowerCase();
            String dataFromFileTmp = dataFromFile.substring(0, key.length()).toLowerCase();
            if(key.equals(dataFromFileTmp)) {
                Save(entry.getValue());
                System.out.println("PROCESSED");
                isPreesent = true;
                break;
            }  
        }
        if(!isPreesent) {
            System.out.println("UNDEFINED");
        }
    }
}
