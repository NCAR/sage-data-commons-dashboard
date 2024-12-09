
package edu.sage.datacommonsdashboard.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "timestamp",
    "pbs_version",
    "pbs_server",
    "Jobs"
})
@Generated("jsonschema2pojo")
public class JobData {

    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("pbs_version")
    private String pbsVersion;
    @JsonProperty("pbs_server")
    private String pbsServer;
    @JsonProperty("Jobs")
    //private Jobs jobs;
    private Map<String, Jobs> jobs;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("pbs_version")
    public String getPbsVersion() {
        return pbsVersion;
    }

    @JsonProperty("pbs_version")
    public void setPbsVersion(String pbsVersion) {
        this.pbsVersion = pbsVersion;
    }

    @JsonProperty("pbs_server")
    public String getPbsServer() {
        return pbsServer;
    }

    @JsonProperty("pbs_server")
    public void setPbsServer(String pbsServer) {
        this.pbsServer = pbsServer;
    }

//    @JsonProperty("Jobs")
//    public Jobs getJobs() {
//        return jobs;
//    }
//
//    @JsonProperty("Jobs")
//    public void setJobs(Jobs jobs) {
//        this.jobs = jobs;
//    }

    public Map<String, Jobs> getJobs() {
        return jobs;
    }

    public void setJobs(Map<String, Jobs> jobs) {
        this.jobs = jobs;
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
