package com.isaacsufyan.webservices.beans;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties(value = {"field1", "field3"})
@JsonFilter(value = "filter")
public class Filtering {
    private String field1;

//    @JsonIgnore
    private String field2;
    private String field3;

    public Filtering(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }
}
