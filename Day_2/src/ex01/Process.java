package ex01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Process {
    private String dataFromFile01 = new String();
    private String dataFromFile02 = new String();

    public Process() {}     
    public void ReadAll(String fileName01, String fileName02) throws FileNotFoundException, IOException {
        dataFromFile01 = Read(fileName01);
        dataFromFile02 = Read(fileName02);
        Calculate();
	}

    private String Read(String fileName) throws FileNotFoundException, IOException {
        String data = new String();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data += line + "\n";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return data;
   }

    private void Calculate() {
        String[] dataArr01 = ParseStr(dataFromFile01);
        String[] dataArr02 = ParseStr(dataFromFile02);
        Map<String, String> mapDictionary = new HashMap<>();
        mapDictionary = ParseMap(mapDictionary, dataArr01);
        mapDictionary = ParseMap(mapDictionary, dataArr02);
        int[] vector01 = ParseData(mapDictionary, dataArr01);
        int[] vector02 = ParseData(mapDictionary, dataArr02);

        int numerator = 0;
        for(int j = 0; j < vector01.length; ++j) {
            numerator += vector01[j] * vector02[j];
        }

        double denominator = 0;
        int tmp01 = 0;
        int tmp02 = 0;
        for(int j = 0; j < vector01.length; ++j) {
            tmp01 += vector01[j] * vector01[j];
            tmp02 += vector02[j] * vector02[j];
        }
        denominator = Math.sqrt(tmp01) * Math.sqrt(tmp02);
        double similarity = numerator / denominator;
        
        System.out.println("Similarity = " + Math.floor(similarity * 100) / 100);
    }

    private String[] ParseStr(String dataFromFile) {
        return dataFromFile.replaceAll("\\n", "").split(" ");
    }

    private Map<String, String> ParseMap(Map<String, String> mapDictionary, String[] dataArr) {
        for(String s : dataArr) {
            mapDictionary.put(s.replaceAll("\\s", ""), "0");
        }
        return mapDictionary;
    }

    private int[] ParseData(Map<String, String> mapDictionary, String[] dataArr) {
        int[] vector = new int[mapDictionary.size()];
        int i = 0;
        for(Map.Entry<String, String> entry : mapDictionary.entrySet()) {
            for(String s : dataArr) {
                if(s.length() == entry.getKey().length() && s.contains(entry.getKey())) {
                    vector[i] += 1;
                }
            }
            i++;
        }
        return vector;
    }
}
