
package edu.sage.datacommonsdashboard.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mem",
    "ncpus",
    "walltime"
})
@Generated("jsonschema2pojo")
public class ResourceList {

    @JsonProperty("mem")
    private String mem;
    @JsonProperty("ncpus")
    private Integer ncpus;
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

    @JsonProperty("ncpus")
    public Integer getNcpus() {
        return ncpus;
    }

    @JsonProperty("ncpus")
    public void setNcpus(Integer ncpus) {
        this.ncpus = ncpus;
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
