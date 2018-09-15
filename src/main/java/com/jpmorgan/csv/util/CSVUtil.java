package com.jpmorgan.csv.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CSVUtil {

    /**
     * Get the list of objects from the csv
     * @param fileName
     * @return
     */
    public static List<Map<String,String>> getDataObj(String fileName) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(fileName));
            String[] line;
            Boolean isFoundHeader = false;
            List<Map<String,String>> dataMap = new LinkedList<>();
            List<String> keys = new LinkedList<>();
            while ((line = reader.readNext()) != null) {
                if (!isFoundHeader) {
                    for (String l : line) {
                        keys.add(l);
                    }
                    isFoundHeader = true;
                } else {
                    Map<String, String> rowData = new HashMap<>();
                    for (int i = 0; i < line.length; i++) {
                        rowData.put(keys.get(i), line[i]);
                    }
                    dataMap.add(rowData);
                }

            }
            return dataMap;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Write the rows back to file.
     * @param fileName
     * @param dataObj
     */
    public static void writeToFile(String fileName, List<Map<String,String>> dataObj) {
        Boolean isWriteHeader = false;

        File file = new File(fileName);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            for (Map<String,String> row:dataObj) {
                if(!isWriteHeader) {
                    writer.writeNext(row.keySet().toArray(new String[0]));
                    isWriteHeader = true;
                }
                writer.writeNext(row.values().toArray(new String[0]));
            }
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
