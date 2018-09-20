package com.deme.sharepic.models;

/**
 * Created by Dima on 9/16/2018.
 */

public class FilterData {
    public int filterType;
    public String filterTitle;
    public FilterData(int type, String title) {
        this.filterTitle = title;
        this.filterType = type;
    }
}
