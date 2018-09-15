package com.jpmorgan.csv.metadata;

import java.util.Comparator;
import java.util.Map;

public class MetaDataComparator implements Comparator<Map<String, String>> {

    private MetaData metaData;

    public MetaDataComparator() {

    }

    public MetaDataComparator(MetaData metaData) {
        this.metaData = metaData;
    }

    @Override
    public int compare(Map<String, String> o1, Map<String, String> o2) {
        return o1.get(metaData.getColumn()).compareTo(o2.get(metaData.getColumn()));
    }
}
