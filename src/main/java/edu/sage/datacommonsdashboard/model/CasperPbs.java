
package edu.sage.datacommonsdashboard.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Job_Name",
    "Job_Owner"
})
@Generated("jsonschema2pojo")
public class CasperPbs {

    @JsonProperty("Job_Name")
    private String jobName;
    @JsonProperty("Job_Owner")
    private String jobOwner;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("Job_Name")
    public String getJobName() {
        return jobName;
    }

    @JsonProperty("Job_Name")
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @JsonProperty("Job_Owner")
    public String getJobOwner() {
        return jobOwner;
    }

    @JsonProperty("Job_Owner")
    public void setJobOwner(String jobOwner) {
        this.jobOwner = jobOwner;
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
