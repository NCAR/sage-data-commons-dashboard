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
    "server",
    "Account_Name",
    "Checkpoint",
    "ctime",
    "Error_Path",
    "Hold_Types",
    "Join_Path",
    "Keep_Files",
    "Mail_Points",
    "mtime",
    "Output_Path",
    "Priority",
    "qtime",
    "Rerunable",
    "Resource_List",
    "stime",
    "obittime",
    "Shell_Path_List",
    "jobdir",
    "substate",
    "Variable_List",
    "comment",
    "etime",
    "umask",
    "run_count",
    "eligible_time",
    "Exit_status",
    "Submit_arguments",
    "project",
    "Submit_Host",
    "server_instance_id"
})
@Generated("jsonschema2pojo")
public class Job {

    @JsonProperty("Job_Name")
    private String jobName;
    @JsonProperty("Job_Owner")
    private String jobOwner;
    @JsonProperty("job_state")
    private String jobState;
    @JsonProperty("queue")
    private String queue;
    @JsonProperty("server")
    private String server;
    @JsonProperty("Account_Name")
    private String accountName;
    @JsonProperty("Checkpoint")
    private String checkpoint;
    @JsonProperty("ctime")
    private String ctime;
    @JsonProperty("Error_Path")
    private String errorPath;
    @JsonProperty("Hold_Types")
    private String holdTypes;
    @JsonProperty("Join_Path")
    private String joinPath;
    @JsonProperty("Keep_Files")
    private String keepFiles;
    @JsonProperty("Mail_Points")
    private String mailPoints;
    @JsonProperty("mtime")
    private String mtime;
    @JsonProperty("Output_Path")
    private String outputPath;
    @JsonProperty("Priority")
    private Integer priority;
    @JsonProperty("qtime")
    private String qtime;
    @JsonProperty("Rerunable")
    private String rerunable;
    @JsonProperty("Resource_List")
    private ResourceList resourceList;
    @JsonProperty("stime")
    private String stime;
    @JsonProperty("obittime")
    private String obittime;
    @JsonProperty("Shell_Path_List")
    private String shellPathList;
    @JsonProperty("jobdir")
    private String jobdir;
    @JsonProperty("substate")
    private Integer substate;
    @JsonProperty("Variable_List")
    private VariableList variableList;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("etime")
    private String etime;
    @JsonProperty("umask")
    private Integer umask;
    @JsonProperty("run_count")
    private Integer runCount;
    @JsonProperty("eligible_time")
    private String eligibleTime;
    @JsonProperty("Exit_status")
    private Integer exitStatus;
    @JsonProperty("Submit_arguments")
    private String submitArguments;
    @JsonProperty("project")
    private String project;
    @JsonProperty("Submit_Host")
    private String submitHost;
    @JsonProperty("server_instance_id")
    private String serverInstanceId;
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

    @JsonProperty("server")
    public String getServer() {
        return server;
    }

    @JsonProperty("server")
    public void setServer(String server) {
        this.server = server;
    }

    @JsonProperty("Account_Name")
    public String getAccountName() {
        return accountName;
    }

    @JsonProperty("Account_Name")
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @JsonProperty("Checkpoint")
    public String getCheckpoint() {
        return checkpoint;
    }

    @JsonProperty("Checkpoint")
    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint;
    }

    @JsonProperty("ctime")
    public String getCtime() {
        return ctime;
    }

    @JsonProperty("ctime")
    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    @JsonProperty("Error_Path")
    public String getErrorPath() {
        return errorPath;
    }

    @JsonProperty("Error_Path")
    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }

    @JsonProperty("Hold_Types")
    public String getHoldTypes() {
        return holdTypes;
    }

    @JsonProperty("Hold_Types")
    public void setHoldTypes(String holdTypes) {
        this.holdTypes = holdTypes;
    }

    @JsonProperty("Join_Path")
    public String getJoinPath() {
        return joinPath;
    }

    @JsonProperty("Join_Path")
    public void setJoinPath(String joinPath) {
        this.joinPath = joinPath;
    }

    @JsonProperty("Keep_Files")
    public String getKeepFiles() {
        return keepFiles;
    }

    @JsonProperty("Keep_Files")
    public void setKeepFiles(String keepFiles) {
        this.keepFiles = keepFiles;
    }

    @JsonProperty("Mail_Points")
    public String getMailPoints() {
        return mailPoints;
    }

    @JsonProperty("Mail_Points")
    public void setMailPoints(String mailPoints) {
        this.mailPoints = mailPoints;
    }

    @JsonProperty("mtime")
    public String getMtime() {
        return mtime;
    }

    @JsonProperty("mtime")
    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    @JsonProperty("Output_Path")
    public String getOutputPath() {
        return outputPath;
    }

    @JsonProperty("Output_Path")
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    @JsonProperty("Priority")
    public Integer getPriority() {
        return priority;
    }

    @JsonProperty("Priority")
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("qtime")
    public String getQtime() {
        return qtime;
    }

    @JsonProperty("qtime")
    public void setQtime(String qtime) {
        this.qtime = qtime;
    }

    @JsonProperty("Rerunable")
    public String getRerunable() {
        return rerunable;
    }

    @JsonProperty("Rerunable")
    public void setRerunable(String rerunable) {
        this.rerunable = rerunable;
    }

    @JsonProperty("Resource_List")
    public ResourceList getResourceList() {
        return resourceList;
    }

    @JsonProperty("Resource_List")
    public void setResourceList(ResourceList resourceList) {
        this.resourceList = resourceList;
    }

    @JsonProperty("stime")
    public String getStime() {
        return stime;
    }

    @JsonProperty("stime")
    public void setStime(String stime) {
        this.stime = stime;
    }

    @JsonProperty("obittime")
    public String getObittime() {
        return obittime;
    }

    @JsonProperty("obittime")
    public void setObittime(String obittime) {
        this.obittime = obittime;
    }

    @JsonProperty("Shell_Path_List")
    public String getShellPathList() {
        return shellPathList;
    }

    @JsonProperty("Shell_Path_List")
    public void setShellPathList(String shellPathList) {
        this.shellPathList = shellPathList;
    }

    @JsonProperty("jobdir")
    public String getJobdir() {
        return jobdir;
    }

    @JsonProperty("jobdir")
    public void setJobdir(String jobdir) {
        this.jobdir = jobdir;
    }

    @JsonProperty("substate")
    public Integer getSubstate() {
        return substate;
    }

    @JsonProperty("substate")
    public void setSubstate(Integer substate) {
        this.substate = substate;
    }

    @JsonProperty("Variable_List")
    public VariableList getVariableList() {
        return variableList;
    }

    @JsonProperty("Variable_List")
    public void setVariableList(VariableList variableList) {
        this.variableList = variableList;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("etime")
    public String getEtime() {
        return etime;
    }

    @JsonProperty("etime")
    public void setEtime(String etime) {
        this.etime = etime;
    }

    @JsonProperty("umask")
    public Integer getUmask() {
        return umask;
    }

    @JsonProperty("umask")
    public void setUmask(Integer umask) {
        this.umask = umask;
    }

    @JsonProperty("run_count")
    public Integer getRunCount() {
        return runCount;
    }

    @JsonProperty("run_count")
    public void setRunCount(Integer runCount) {
        this.runCount = runCount;
    }

    @JsonProperty("eligible_time")
    public String getEligibleTime() {
        return eligibleTime;
    }

    @JsonProperty("eligible_time")
    public void setEligibleTime(String eligibleTime) {
        this.eligibleTime = eligibleTime;
    }

    @JsonProperty("Exit_status")
    public Integer getExitStatus() {
        return exitStatus;
    }

    @JsonProperty("Exit_status")
    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    @JsonProperty("Submit_arguments")
    public String getSubmitArguments() {
        return submitArguments;
    }

    @JsonProperty("Submit_arguments")
    public void setSubmitArguments(String submitArguments) {
        this.submitArguments = submitArguments;
    }

    @JsonProperty("project")
    public String getProject() {
        return project;
    }

    @JsonProperty("project")
    public void setProject(String project) {
        this.project = project;
    }

    @JsonProperty("Submit_Host")
    public String getSubmitHost() {
        return submitHost;
    }

    @JsonProperty("Submit_Host")
    public void setSubmitHost(String submitHost) {
        this.submitHost = submitHost;
    }

    @JsonProperty("server_instance_id")
    public String getServerInstanceId() {
        return serverInstanceId;
    }

    @JsonProperty("server_instance_id")
    public void setServerInstanceId(String serverInstanceId) {
        this.serverInstanceId = serverInstanceId;
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
