package com.dev.arif.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohdarif on 12/04/18.
 */

public class DisplaySize {

    @SerializedName("is_watermarked")
    @Expose
    private Boolean isWatermarked;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uri")
    @Expose
    private String uri;

    public Boolean getIsWatermarked() {
        return isWatermarked;
    }

    public void setIsWatermarked(Boolean isWatermarked) {
        this.isWatermarked = isWatermarked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
