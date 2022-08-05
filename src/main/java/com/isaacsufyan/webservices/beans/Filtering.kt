package com.isaacsufyan.webservices.beans

import com.fasterxml.jackson.annotation.JsonFilter

//@JsonIgnoreProperties(value = {"field1", "field3"})
@JsonFilter(value = "filter")
data class Filtering(
    val field1: String,
    //    @JsonIgnore
    val field2: String,
    val field3: String
)