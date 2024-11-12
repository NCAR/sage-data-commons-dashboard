package edu.sage.datacommonsdashboard;

public class QueueData {

    private String jobId;
    private String name;
    private String user;
    private String timeUse;
    private String status;
    private String queue;

    public QueueData() {
    }

    public QueueData(String jobId,
                     String name,
                     String user,
                     String timeUse,
                     String status,
                     String queue) {
        this.jobId = jobId;
        this.name = name;
        this.user = user;
        this.timeUse = timeUse;
        this.status = status;
        this.queue = queue;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTimeUse() {
        return timeUse;
    }

    public void setTimeUse(String timeUse) {
        this.timeUse = timeUse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}
