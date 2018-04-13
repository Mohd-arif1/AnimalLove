package com.dev.arif.ModelResponse;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mohdarif on 12/04/18.
 */

public class ImageResponse {

        @SerializedName("result_count")
        @Expose
        private Integer resultCount;
        @SerializedName("images")
        @Expose
        private List<Images> images = null;

        public Integer getResultCount() {
            return resultCount;
        }

        public void setResultCount(Integer resultCount) {
            this.resultCount = resultCount;
        }

        public List<Images> getImages() {
            return images;
        }

        public void setImages(List<Images> images) {
            this.images = images;
        }

    }

