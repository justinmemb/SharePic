package com.deme.sharepic.models;

import com.deme.sharepic.FilterActivity;
import com.deme.sharepic.utils.FilterUtil;

/**
 * Created by Dima on 9/16/2018.
 */

public class Filter {
    String title;
    FilterUtil.FilterType type;

    public Filter () {

    }
    public Filter (String title, FilterUtil.FilterType type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public FilterUtil.FilterType getType() {
        return type;
    }
}
