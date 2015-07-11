package com.ikmr.banbara23.listfragmentsample;

import java.io.Serializable;

/**
 * Created by banbara23 on 15/07/11.
 */
public class Shop implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
