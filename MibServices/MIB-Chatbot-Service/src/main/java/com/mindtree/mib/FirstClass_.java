
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
    "50",
    "100",
    "St",
    "Ct",
    "6s",
    "4s",
    "SR",
    "BF",
    "Ave",
    "HS",
    "Runs",
    "NO",
    "Inns",
    "Mat"
})
public class FirstClass_ {

    @JsonProperty("50")
    private String _50;
    @JsonProperty("100")
    private String _100;
    @JsonProperty("St")
    private String st;
    @JsonProperty("Ct")
    private String ct;
    @JsonProperty("6s")
    private String _6s;
    @JsonProperty("4s")
    private String _4s;
    @JsonProperty("SR")
    private String sR;
    @JsonProperty("BF")
    private String bF;
    @JsonProperty("Ave")
    private String ave;
    @JsonProperty("HS")
    private String hS;
    @JsonProperty("Runs")
    private String runs;
    @JsonProperty("NO")
    private String nO;
    @JsonProperty("Inns")
    private String inns;
    @JsonProperty("Mat")
    private String mat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("50")
    public String get50() {
        return _50;
    }

    @JsonProperty("50")
    public void set50(String _50) {
        this._50 = _50;
    }

    @JsonProperty("100")
    public String get100() {
        return _100;
    }

    @JsonProperty("100")
    public void set100(String _100) {
        this._100 = _100;
    }

    @JsonProperty("St")
    public String getSt() {
        return st;
    }

    @JsonProperty("St")
    public void setSt(String st) {
        this.st = st;
    }

    @JsonProperty("Ct")
    public String getCt() {
        return ct;
    }

    @JsonProperty("Ct")
    public void setCt(String ct) {
        this.ct = ct;
    }

    @JsonProperty("6s")
    public String get6s() {
        return _6s;
    }

    @JsonProperty("6s")
    public void set6s(String _6s) {
        this._6s = _6s;
    }

    @JsonProperty("4s")
    public String get4s() {
        return _4s;
    }

    @JsonProperty("4s")
    public void set4s(String _4s) {
        this._4s = _4s;
    }

    @JsonProperty("SR")
    public String getSR() {
        return sR;
    }

    @JsonProperty("SR")
    public void setSR(String sR) {
        this.sR = sR;
    }

    @JsonProperty("BF")
    public String getBF() {
        return bF;
    }

    @JsonProperty("BF")
    public void setBF(String bF) {
        this.bF = bF;
    }

    @JsonProperty("Ave")
    public String getAve() {
        return ave;
    }

    @JsonProperty("Ave")
    public void setAve(String ave) {
        this.ave = ave;
    }

    @JsonProperty("HS")
    public String getHS() {
        return hS;
    }

    @JsonProperty("HS")
    public void setHS(String hS) {
        this.hS = hS;
    }

    @JsonProperty("Runs")
    public String getRuns() {
        return runs;
    }

    @JsonProperty("Runs")
    public void setRuns(String runs) {
        this.runs = runs;
    }

    @JsonProperty("NO")
    public String getNO() {
        return nO;
    }

    @JsonProperty("NO")
    public void setNO(String nO) {
        this.nO = nO;
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
