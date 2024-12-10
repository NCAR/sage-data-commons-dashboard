
package edu.sage.datacommonsdashboard.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PBS_O_HOME",
    "PBS_O_LANG",
    "PBS_O_LOGNAME",
    "PBS_O_PATH",
    "PBS_O_MAIL",
    "PBS_O_SHELL",
    "PBS_O_HOST",
    "PBS_O_WORKDIR",
    "PBS_O_SYSTEM",
    "PBS_O_QUEUE"
})
@Generated("jsonschema2pojo")
public class VariableList {

    @JsonProperty("PBS_O_HOME")
    private String pbsOHome;
    @JsonProperty("PBS_O_LANG")
    private String pbsOLang;
    @JsonProperty("PBS_O_LOGNAME")
    private String pbsOLogname;
    @JsonProperty("PBS_O_PATH")
    private String pbsOPath;
    @JsonProperty("PBS_O_MAIL")
    private String pbsOMail;
    @JsonProperty("PBS_O_SHELL")
    private String pbsOShell;
    @JsonProperty("PBS_O_HOST")
    private String pbsOHost;
    @JsonProperty("PBS_O_WORKDIR")
    private String pbsOWorkdir;
    @JsonProperty("PBS_O_SYSTEM")
    private String pbsOSystem;
    @JsonProperty("PBS_O_QUEUE")
    private String pbsOQueue;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("PBS_O_HOME")
    public String getPbsOHome() {
        return pbsOHome;
    }

    @JsonProperty("PBS_O_HOME")
    public void setPbsOHome(String pbsOHome) {
        this.pbsOHome = pbsOHome;
    }

    @JsonProperty("PBS_O_LANG")
    public String getPbsOLang() {
        return pbsOLang;
    }

    @JsonProperty("PBS_O_LANG")
    public void setPbsOLang(String pbsOLang) {
        this.pbsOLang = pbsOLang;
    }

    @JsonProperty("PBS_O_LOGNAME")
    public String getPbsOLogname() {
        return pbsOLogname;
    }

    @JsonProperty("PBS_O_LOGNAME")
    public void setPbsOLogname(String pbsOLogname) {
        this.pbsOLogname = pbsOLogname;
    }

    @JsonProperty("PBS_O_PATH")
    public String getPbsOPath() {
        return pbsOPath;
    }

    @JsonProperty("PBS_O_PATH")
    public void setPbsOPath(String pbsOPath) {
        this.pbsOPath = pbsOPath;
    }

    @JsonProperty("PBS_O_MAIL")
    public String getPbsOMail() {
        return pbsOMail;
    }

    @JsonProperty("PBS_O_MAIL")
    public void setPbsOMail(String pbsOMail) {
        this.pbsOMail = pbsOMail;
    }

    @JsonProperty("PBS_O_SHELL")
    public String getPbsOShell() {
        return pbsOShell;
    }

    @JsonProperty("PBS_O_SHELL")
    public void setPbsOShell(String pbsOShell) {
        this.pbsOShell = pbsOShell;
    }

    @JsonProperty("PBS_O_HOST")
    public String getPbsOHost() {
        return pbsOHost;
    }

    @JsonProperty("PBS_O_HOST")
    public void setPbsOHost(String pbsOHost) {
        this.pbsOHost = pbsOHost;
    }

    @JsonProperty("PBS_O_WORKDIR")
    public String getPbsOWorkdir() {
        return pbsOWorkdir;
    }

    @JsonProperty("PBS_O_WORKDIR")
    public void setPbsOWorkdir(String pbsOWorkdir) {
        this.pbsOWorkdir = pbsOWorkdir;
    }

    @JsonProperty("PBS_O_SYSTEM")
    public String getPbsOSystem() {
        return pbsOSystem;
    }

    @JsonProperty("PBS_O_SYSTEM")
    public void setPbsOSystem(String pbsOSystem) {
        this.pbsOSystem = pbsOSystem;
    }

    @JsonProperty("PBS_O_QUEUE")
    public String getPbsOQueue() {
        return pbsOQueue;
    }

    @JsonProperty("PBS_O_QUEUE")
    public void setPbsOQueue(String pbsOQueue) {
        this.pbsOQueue = pbsOQueue;
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
