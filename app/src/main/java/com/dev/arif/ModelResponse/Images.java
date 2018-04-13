package com.dev.arif.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mohdarif on 12/04/18.
 */

public class Images {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("asset_family")
    @Expose
    private String assetFamily;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("collection_code")
    @Expose
    private String collectionCode;
    @SerializedName("collection_id")
    @Expose
    private Integer collectionId;
    @SerializedName("collection_name")
    @Expose
    private String collectionName;
    @SerializedName("display_sizes")
    @Expose
    private List<DisplaySize> displaySizes = null;
    @SerializedName("license_model")
    @Expose
    private String licenseModel;
    @SerializedName("max_dimensions")
    @Expose
    private MaxDimensions maxDimensions;
    @SerializedName("title")
    @Expose
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetFamily() {
        return assetFamily;
    }

    public void setAssetFamily(String assetFamily) {
        this.assetFamily = assetFamily;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCollectionCode() {
        return collectionCode;
    }

    public void setCollectionCode(String collectionCode) {
        this.collectionCode = collectionCode;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public List<DisplaySize> getDisplaySizes() {
        return displaySizes;
    }

    public void setDisplaySizes(List<DisplaySize> displaySizes) {
        this.displaySizes = displaySizes;
    }

    public String getLicenseModel() {
        return licenseModel;
    }

    public void setLicenseModel(String licenseModel) {
        this.licenseModel = licenseModel;
    }

    public MaxDimensions getMaxDimensions() {
        return maxDimensions;
    }

    public void setMaxDimensions(MaxDimensions maxDimensions) {
        this.maxDimensions = maxDimensions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}





