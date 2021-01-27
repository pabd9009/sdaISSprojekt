package ISS.functionality.isspasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = {"message","request"})
public class ISSPassesResponse {


    @JsonProperty("response")
    private ISSPass[] ISSPasses;

    public ISSPassesResponse() {
    }

    public ISSPassesResponse(ISSPass[] ISSPasses) {
        this.ISSPasses = ISSPasses;
    }

    public ISSPass[] getPasses() {
        return ISSPasses;
    }

    public void setPasses(ISSPass[] ISSPasses) {
        this.ISSPasses = ISSPasses;
    }
}
