
package com.mindtree.mib;

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
public class Bowling {

    @JsonProperty("listA")
    private ListA listA;
    @JsonProperty("firstClass")
    private FirstClass firstClass;
    @JsonProperty("T20Is")
    private T20Is t20Is;
    @JsonProperty("ODIs")
    private ODIs oDIs;
    @JsonProperty("tests")
    private Tests tests;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("listA")
    public ListA getListA() {
        return listA;
    }

    @JsonProperty("listA")
    public void setListA(ListA listA) {
        this.listA = listA;
    }

    @JsonProperty("firstClass")
    public FirstClass getFirstClass() {
        return firstClass;
    }

    @JsonProperty("firstClass")
    public void setFirstClass(FirstClass firstClass) {
        this.firstClass = firstClass;
    }

    @JsonProperty("T20Is")
    public T20Is getT20Is() {
        return t20Is;
    }

    @JsonProperty("T20Is")
    public void setT20Is(T20Is t20Is) {
        this.t20Is = t20Is;
    }

    @JsonProperty("ODIs")
    public ODIs getODIs() {
        return oDIs;
    }

    @JsonProperty("ODIs")
    public void setODIs(ODIs oDIs) {
        this.oDIs = oDIs;
    }

    @JsonProperty("tests")
    public Tests getTests() {
        return tests;
    }

    @JsonProperty("tests")
    public void setTests(Tests tests) {
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
