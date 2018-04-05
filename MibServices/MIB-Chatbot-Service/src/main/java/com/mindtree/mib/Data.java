
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
    "bowling",
    "batting"
})
public class Data {

    @JsonProperty("bowling")
    private Bowling bowling;
    @JsonProperty("batting")
    private Batting batting;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bowling")
    public Bowling getBowling() {
        return bowling;
    }

    @JsonProperty("bowling")
    public void setBowling(Bowling bowling) {
        this.bowling = bowling;
    }

    @JsonProperty("batting")
    public Batting getBatting() {
        return batting;
    }

    @JsonProperty("batting")
    public void setBatting(Batting batting) {
        this.batting = batting;
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
