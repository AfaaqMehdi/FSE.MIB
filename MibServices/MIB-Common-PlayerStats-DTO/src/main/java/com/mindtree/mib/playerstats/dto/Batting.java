
package com.mindtree.mib.playerstats.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "listA",
    "firstClass",
    "T20Is",
    "ODIs",
    "tests"
})
public class Batting {

    @JsonProperty("listA")
    private ListA_ listA;
    @JsonProperty("firstClass")
    private FirstClass_ firstClass;
    @JsonProperty("T20Is")
    private T20Is_ t20Is;
    @JsonProperty("ODIs")
    private ODIs_ oDIs;
    @JsonProperty("tests")
    private Tests_ tests;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("listA")
    public ListA_ getListA() {
        return listA;
    }

    @JsonProperty("listA")
    public void setListA(ListA_ listA) {
        this.listA = listA;
    }

    @JsonProperty("firstClass")
    public FirstClass_ getFirstClass() {
        return firstClass;
    }

    @JsonProperty("firstClass")
    public void setFirstClass(FirstClass_ firstClass) {
        this.firstClass = firstClass;
    }

    @JsonProperty("T20Is")
    public T20Is_ getT20Is() {
        return t20Is;
    }

    @JsonProperty("T20Is")
    public void setT20Is(T20Is_ t20Is) {
        this.t20Is = t20Is;
    }

    @JsonProperty("ODIs")
    public ODIs_ getODIs() {
        return oDIs;
    }

    @JsonProperty("ODIs")
    public void setODIs(ODIs_ oDIs) {
        this.oDIs = oDIs;
    }

    @JsonProperty("tests")
    public Tests_ getTests() {
        return tests;
    }

    @JsonProperty("tests")
    public void setTests(Tests_ tests) {
        this.tests = tests;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
