
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
    "10",
    "5w",
    "4w",
    "SR",
    "Econ",
    "Ave",
    "BBM",
    "BBI",
    "Wkts",
    "Runs",
    "Balls",
    "Inns",
    "Mat"
})
public class Tests {

    @JsonProperty("10")
    private String _10;
    @JsonProperty("5w")
    private String _5w;
    @JsonProperty("4w")
    private String _4w;
    @JsonProperty("SR")
    private String sR;
    @JsonProperty("Econ")
    private String econ;
    @JsonProperty("Ave")
    private String ave;
    @JsonProperty("BBM")
    private String bBM;
    @JsonProperty("BBI")
    private String bBI;
    @JsonProperty("Wkts")
    private String wkts;
    @JsonProperty("Runs")
    private String runs;
    @JsonProperty("Balls")
    private String balls;
    @JsonProperty("Inns")
    private String inns;
    @JsonProperty("Mat")
    private String mat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("10")
    public String get10() {
        return _10;
    }

    @JsonProperty("10")
    public void set10(String _10) {
        this._10 = _10;
    }

    @JsonProperty("5w")
    public String get5w() {
        return _5w;
    }

    @JsonProperty("5w")
    public void set5w(String _5w) {
        this._5w = _5w;
    }

    @JsonProperty("4w")
    public String get4w() {
        return _4w;
    }

    @JsonProperty("4w")
    public void set4w(String _4w) {
        this._4w = _4w;
    }

    @JsonProperty("SR")
    public String getSR() {
        return sR;
    }

    @JsonProperty("SR")
    public void setSR(String sR) {
        this.sR = sR;
    }

    @JsonProperty("Econ")
    public String getEcon() {
        return econ;
    }

    @JsonProperty("Econ")
    public void setEcon(String econ) {
        this.econ = econ;
    }

    @JsonProperty("Ave")
    public String getAve() {
        return ave;
    }

    @JsonProperty("Ave")
    public void setAve(String ave) {
        this.ave = ave;
    }

    @JsonProperty("BBM")
    public String getBBM() {
        return bBM;
    }

    @JsonProperty("BBM")
    public void setBBM(String bBM) {
        this.bBM = bBM;
    }

    @JsonProperty("BBI")
    public String getBBI() {
        return bBI;
    }

    @JsonProperty("BBI")
    public void setBBI(String bBI) {
        this.bBI = bBI;
    }

    @JsonProperty("Wkts")
    public String getWkts() {
        return wkts;
    }

    @JsonProperty("Wkts")
    public void setWkts(String wkts) {
        this.wkts = wkts;
    }

    @JsonProperty("Runs")
    public String getRuns() {
        return runs;
    }

    @JsonProperty("Runs")
    public void setRuns(String runs) {
        this.runs = runs;
    }

    @JsonProperty("Balls")
    public String getBalls() {
        return balls;
    }

    @JsonProperty("Balls")
    public void setBalls(String balls) {
        this.balls = balls;
    }

    @JsonProperty("Inns")
    public String getInns() {
        return inns;
    }

    @JsonProperty("Inns")
    public void setInns(String inns) {
        this.inns = inns;
    }

    @JsonProperty("Mat")
    public String getMat() {
        return mat;
    }

    @JsonProperty("Mat")
    public void setMat(String mat) {
        this.mat = mat;
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
