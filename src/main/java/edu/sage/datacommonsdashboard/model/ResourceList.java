
package edu.sage.datacommonsdashboard.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mem",
    "mpiprocs",
    "mps",
    "ncpus",
    "ngpus",
    "nodect",
    "nvpus",
    "place",
    "select",
    "walltime"
})
@Generated("jsonschema2pojo")
public class ResourceList {

    @JsonProperty("mem")
    private String mem;
    @JsonProperty("mpiprocs")
    private Integer mpiprocs;
    @JsonProperty("mps")
    private Integer mps;
    @JsonProperty("ncpus")
    private Integer ncpus;
    @JsonProperty("ngpus")
    private Integer ngpus;
    @JsonProperty("nodect")
    private Integer nodect;
    @JsonProperty("nvpus")
    private Integer nvpus;
    @JsonProperty("place")
    private String place;
    @JsonProperty("select")
    private String select;
    @JsonProperty("walltime")
    private String walltime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("mem")
    public String getMem() {
        return mem;
    }

    @JsonProperty("mem")
    public void setMem(String mem) {
        this.mem = mem;
    }

    @JsonProperty("mpiprocs")
    public Integer getMpiprocs() {
        return mpiprocs;
    }

    @JsonProperty("mpiprocs")
    public void setMpiprocs(Integer mpiprocs) {
        this.mpiprocs = mpiprocs;
    }

    @JsonProperty("mps")
    public Integer getMps() {
        return mps;
    }

    @JsonProperty("mps")
    public void setMps(Integer mps) {
        this.mps = mps;
    }

    @JsonProperty("ncpus")
    public Integer getNcpus() {
        return ncpus;
    }

    @JsonProperty("ncpus")
    public void setNcpus(Integer ncpus) {
        this.ncpus = ncpus;
    }

    @JsonProperty("ngpus")
    public Integer getNgpus() {
        return ngpus;
    }

    @JsonProperty("ngpus")
    public void setNgpus(Integer ngpus) {
        this.ngpus = ngpus;
    }

    @JsonProperty("nodect")
    public Integer getNodect() {
        return nodect;
    }

    @JsonProperty("nodect")
    public void setNodect(Integer nodect) {
        this.nodect = nodect;
    }

    @JsonProperty("nvpus")
    public Integer getNvpus() {
        return nvpus;
    }

    @JsonProperty("nvpus")
    public void setNvpus(Integer nvpus) {
        this.nvpus = nvpus;
    }

    @JsonProperty("place")
    public String getPlace() {
        return place;
    }

    @JsonProperty("place")
    public void setPlace(String place) {
        this.place = place;
    }

    @JsonProperty("select")
    public String getSelect() {
        return select;
    }

    @JsonProperty("select")
    public void setSelect(String select) {
        this.select = select;
    }

    @JsonProperty("walltime")
    public String getWalltime() {
        return walltime;
    }

    @JsonProperty("walltime")
    public void setWalltime(String walltime) {
        this.walltime = walltime;
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
