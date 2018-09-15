package com.jpmorgan.csv;

import com.jpmorgan.csv.metadata.MetaData;
import com.jpmorgan.csv.metadata.MetaDataComparator;
import com.jpmorgan.csv.util.CSVUtil;
import com.jpmorgan.csv.util.XmlUtil;

import java.util.List;
import java.util.Map;

public class MainApplication {

    /**
     * Argument 1 filename of csv
     * Argument 2 filename of xml
     * Sorted file will be saved on testFile.csv
     * @param args
     */
    public static void main(String[] args) {
        String fileName = args[0];
        List<Map<String,String>> dataObj = CSVUtil.getDataObj(fileName);
        if(dataObj!=null) {
            List<MetaData> list = XmlUtil.getMetaList(args[1]);
            for (MetaData metaData: list) {
                dataObj.sort(new MetaDataComparator(metaData));
            }
        }
        CSVUtil.writeToFile("testFile.csv",dataObj);

    }
}
