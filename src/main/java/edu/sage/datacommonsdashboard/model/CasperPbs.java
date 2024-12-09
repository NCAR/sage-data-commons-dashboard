
package edu.sage.datacommonsdashboard.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Job_Name",
    "Job_Owner",
    "job_state",
    "queue",
    "Priority",
    "comment",
    "Exit_status",
    "Resource_List",
    "Variable_List_summary"
})
@Generated("jsonschema2pojo")
public class CasperPbs {

    @JsonProperty("Job_Name")
    private String jobName;
    @JsonProperty("Job_Owner")
    private String jobOwner;
    @JsonProperty("job_state")
    private String jobState;
    @JsonProperty("queue")
    private String queue;
    @JsonProperty("Priority")
    private Integer priority;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("Exit_status")
    private Integer exitStatus;
    @JsonProperty("Resource_List")
    private ResourceList resourceList;
    @JsonProperty("Variable_List_summary")
    private String variableListSummary;
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

    @JsonProperty("job_state")
    public String getJobState() {
        return jobState;
    }

    @JsonProperty("job_state")
    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    @JsonProperty("queue")
    public String getQueue() {
        return queue;
    }

    @JsonProperty("queue")
    public void setQueue(String queue) {
        this.queue = queue;
    }

    @JsonProperty("Priority")
    public Integer getPriority() {
        return priority;
    }

    @JsonProperty("Priority")
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("Exit_status")
    public Integer getExitStatus() {
        return exitStatus;
    }

    @JsonProperty("Exit_status")
    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    @JsonProperty("Resource_List")
    public ResourceList getResourceList() {
        return resourceList;
    }

    @JsonProperty("Resource_List")
    public void setResourceList(ResourceList resourceList) {
        this.resourceList = resourceList;
    }

    @JsonProperty("Variable_List_summary")
    public String getVariableListSummary() {
        return variableListSummary;
    }

    @JsonProperty("Variable_List_summary")
    public void setVariableListSummary(String variableListSummary) {
        this.variableListSummary = variableListSummary;
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
