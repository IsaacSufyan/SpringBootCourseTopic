package com.isaacsufyan.webservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.isaacsufyan.webservices.beans.Filtering;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        Filtering filtering = new Filtering("value1", "value2", "value3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filtering);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping("/filteringList")
    public MappingJacksonValue filteringList() {
        List<Filtering> filteringList = Arrays.asList(
                new Filtering("value1", "value2", "value3"),
                new Filtering("value2", "value22", "value32"),
                new Filtering("value3", "value23", "value33")
        );

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filteringList);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
