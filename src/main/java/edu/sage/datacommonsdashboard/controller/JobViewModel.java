package edu.sage.datacommonsdashboard.controller;

import java.util.Objects;

public class JobViewModel {

    private Integer timestamp;
    private String pbsVersion;
    private String pbsServer;

    // Fields from Job
    private String jobId;
    private String jobName;
    private String jobOwner;
    private String jobState;
    private String queue;
    private String server;
    private String accountName;
    private String checkpoint;
     private Long ctime;
    private String holdTypes;
    private String joinPath;
    private String keepFiles;
    private String mailPoints;
    private Long mtime;
    private Integer priority;
    private Long qtime;
    private String rerunable;
    private Long stime;
    private Long obittime;
    private String shellPathList;
    private String jobdir;
    private Integer substate;
    private String comment;
    private Long etime;
    private Integer umask;
    private Integer runCount;
    private String eligibleTime;
    private Integer exitStatus;
    private String submitArguments;
    private String project;
    private String submitHost;
    private String serverInstanceId;

    // Fields from ResourceList
    private String mem;
    private Double memoryBytes;
    private Integer mpiprocs;
    private Integer mps;
    private Integer ncpus;
    private Integer ngpus;
    private Integer nodect;
    private Integer nvpus;
    private String place;
    private String select;
    private String walltime;


    // Constructors, Getters, and Setters
    public JobViewModel() {}

    // Constructor
    public JobViewModel(String jobId, String jobName, String jobOwner, String jobState, String queue, String server,
                        String accountName, String checkpoint, Long ctime, String holdTypes, String joinPath,
                        String keepFiles, String mailPoints, Long mtime, Integer priority, Long qtime, String rerunable,
                        Long stime, Long obittime, String shellPathList, String jobdir, Integer substate, String comment,
                        Long etime, Integer umask, Integer runCount, String eligibleTime, Integer exitStatus,
                        String submitArguments, String project, String submitHost, String serverInstanceId,
                        String mem, Double memoryBytes, Integer mpiprocs, Integer mps, Integer ncpus, Integer ngpus, Integer nodect,
                        Integer nvpus, String place, String select, String walltime) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobOwner = jobOwner;
        this.jobState = jobState;
        this.queue = queue;
        this.server = server;
        this.accountName = accountName;
        this.checkpoint = checkpoint;
        this.ctime = ctime;
        this.holdTypes = holdTypes;
        this.joinPath = joinPath;
        this.keepFiles = keepFiles;
        this.mailPoints = mailPoints;
        this.mtime = mtime;
        this.priority = priority;
        this.qtime = qtime;
        this.rerunable = rerunable;

        this.stime = stime;
        this.obittime = obittime;
        this.shellPathList = shellPathList;
        this.jobdir = jobdir;
        this.substate = substate;
        this.comment = comment;
        this.etime = etime;
        this.umask = umask;
        this.runCount = runCount;
        this.eligibleTime = eligibleTime;
        this.exitStatus = exitStatus;
        this.submitArguments = submitArguments;
        this.project = project;
        this.submitHost = submitHost;
        this.serverInstanceId = serverInstanceId;

        // ResourceList fields
        this.mem = mem;
        this.memoryBytes = memoryBytes;
        this.mpiprocs = mpiprocs;
        this.mps = mps;
        this.ncpus = ncpus;
        this.ngpus = ngpus;
        this.nodect = nodect;
        this.nvpus = nvpus;
        this.place = place;
        this.select = select;
        this.walltime = walltime;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getPbsVersion() {
        return pbsVersion;
    }

    public void setPbsVersion(String pbsVersion) {
        this.pbsVersion = pbsVersion;
    }

    public String getPbsServer() {
        return pbsServer;
    }

    public void setPbsServer(String pbsServer) {
        this.pbsServer = pbsServer;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobOwner() {
        return jobOwner;
    }

    public void setJobOwner(String jobOwner) {
        this.jobOwner = jobOwner;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public String getHoldTypes() {
        return holdTypes;
    }

    public void setHoldTypes(String holdTypes) {
        this.holdTypes = holdTypes;
    }

    public String getJoinPath() {
        return joinPath;
    }

    public void setJoinPath(String joinPath) {
        this.joinPath = joinPath;
    }

    public String getKeepFiles() {
        return keepFiles;
    }

    public void setKeepFiles(String keepFiles) {
        this.keepFiles = keepFiles;
    }

    public String getMailPoints() {
        return mailPoints;
    }

    public void setMailPoints(String mailPoints) {
        this.mailPoints = mailPoints;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getQtime() {
        return qtime;
    }

    public void setQtime(Long qtime) {
        this.qtime = qtime;
    }

    public String getRerunable() {
        return rerunable;
    }

    public void setRerunable(String rerunable) {
        this.rerunable = rerunable;
    }

    public Long getStime() {
        return stime;
    }

    public void setStime(Long stime) {
        this.stime = stime;
    }

    public Long getObittime() {
        return obittime;
    }

    public void setObittime(Long obittime) {
        this.obittime = obittime;
    }

    public String getShellPathList() {
        return shellPathList;
    }

    public void setShellPathList(String shellPathList) {
        this.shellPathList = shellPathList;
    }

    public String getJobdir() {
        return jobdir;
    }

    public void setJobdir(String jobdir) {
        this.jobdir = jobdir;
    }

    public Integer getSubstate() {
        return substate;
    }

    public void setSubstate(Integer substate) {
        this.substate = substate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getEtime() {
        return etime;
    }

    public void setEtime(Long etime) {
        this.etime = etime;
    }

    public Integer getUmask() {
        return umask;
    }

    public void setUmask(Integer umask) {
        this.umask = umask;
    }

    public Integer getRunCount() {
        return runCount;
    }

    public void setRunCount(Integer runCount) {
        this.runCount = runCount;
    }

    public String getEligibleTime() {
        return eligibleTime;
    }

    public void setEligibleTime(String eligibleTime) {
        this.eligibleTime = eligibleTime;
    }

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    public String getSubmitArguments() {
        return submitArguments;
    }

    public void setSubmitArguments(String submitArguments) {
        this.submitArguments = submitArguments;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSubmitHost() {
        return submitHost;
    }

    public void setSubmitHost(String submitHost) {
        this.submitHost = submitHost;
    }

    public String getServerInstanceId() {
        return serverInstanceId;
    }

    public void setServerInstanceId(String serverInstanceId) {
        this.serverInstanceId = serverInstanceId;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public Double getMemoryBytes() {
        return memoryBytes;
    }

    public void setMemoryBytes(Double memoryBytes) {
        this.memoryBytes = memoryBytes;
    }

    public Integer getMpiprocs() {
        return mpiprocs;
    }

    public void setMpiprocs(Integer mpiprocs) {
        this.mpiprocs = mpiprocs;
    }

    public Integer getMps() {
        return mps;
    }

    public void setMps(Integer mps) {
        this.mps = mps;
    }

    public Integer getNcpus() {
        return ncpus;
    }

    public void setNcpus(Integer ncpus) {
        this.ncpus = ncpus;
    }

    public Integer getNgpus() {
        return ngpus;
    }

    public void setNgpus(Integer ngpus) {
        this.ngpus = ngpus;
    }

    public Integer getNodect() {
        return nodect;
    }

    public void setNodect(Integer nodect) {
        this.nodect = nodect;
    }

    public Integer getNvpus() {
        return nvpus;
    }

    public void setNvpus(Integer nvpus) {
        this.nvpus = nvpus;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getWalltime() {
        return walltime;
    }

    public void setWalltime(String walltime) {
        this.walltime = walltime;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JobViewModel that)) return false;
        return Objects.equals(timestamp, that.timestamp) && Objects.equals(pbsVersion, that.pbsVersion) && Objects.equals(pbsServer, that.pbsServer) && Objects.equals(jobId, that.jobId) && Objects.equals(jobName, that.jobName) && Objects.equals(jobOwner, that.jobOwner) && Objects.equals(jobState, that.jobState) && Objects.equals(queue, that.queue) && Objects.equals(server, that.server) && Objects.equals(accountName, that.accountName) && Objects.equals(checkpoint, that.checkpoint) && Objects.equals(ctime, that.ctime) && Objects.equals(holdTypes, that.holdTypes) && Objects.equals(joinPath, that.joinPath) && Objects.equals(keepFiles, that.keepFiles) && Objects.equals(mailPoints, that.mailPoints) && Objects.equals(mtime, that.mtime) && Objects.equals(priority, that.priority) && Objects.equals(qtime, that.qtime) && Objects.equals(rerunable, that.rerunable) && Objects.equals(stime, that.stime) && Objects.equals(obittime, that.obittime) && Objects.equals(shellPathList, that.shellPathList) && Objects.equals(jobdir, that.jobdir) && Objects.equals(substate, that.substate) && Objects.equals(comment, that.comment) && Objects.equals(etime, that.etime) && Objects.equals(umask, that.umask) && Objects.equals(runCount, that.runCount) && Objects.equals(eligibleTime, that.eligibleTime) && Objects.equals(exitStatus, that.exitStatus) && Objects.equals(submitArguments, that.submitArguments) && Objects.equals(project, that.project) && Objects.equals(submitHost, that.submitHost) && Objects.equals(serverInstanceId, that.serverInstanceId) && Objects.equals(mem, that.mem) && Objects.equals(mpiprocs, that.mpiprocs) && Objects.equals(mps, that.mps) && Objects.equals(ncpus, that.ncpus) && Objects.equals(ngpus, that.ngpus) && Objects.equals(nodect, that.nodect) && Objects.equals(nvpus, that.nvpus) && Objects.equals(place, that.place) && Objects.equals(select, that.select) && Objects.equals(walltime, that.walltime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, pbsVersion, pbsServer, jobId, jobName, jobOwner, jobState, queue, server, accountName, checkpoint, ctime, holdTypes, joinPath, keepFiles, mailPoints, mtime, priority, qtime, rerunable, stime, obittime, shellPathList, jobdir, substate, comment, etime, umask, runCount, eligibleTime, exitStatus, submitArguments, project, submitHost, serverInstanceId, mem, mpiprocs, mps, ncpus, ngpus, nodect, nvpus, place, select, walltime);
    }
}
