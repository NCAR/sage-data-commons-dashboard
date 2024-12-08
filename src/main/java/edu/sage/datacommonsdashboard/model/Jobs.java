
package edu.sage.datacommonsdashboard.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "casper-pbs"
})
@Generated("jsonschema2pojo")
public class Jobs {

    @JsonProperty("casper-pbs")
    private CasperPbs casperPbs;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("casper-pbs")
    public CasperPbs getCasperPbs() {
        return casperPbs;
    }

    @JsonProperty("casper-pbs")
    public void setCasperPbs(CasperPbs casperPbs) {
        this.casperPbs = casperPbs;
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
