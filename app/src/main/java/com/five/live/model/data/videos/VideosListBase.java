
package com.five.live.model.data.videos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideosListBase {

    @SerializedName("list")
    @Expose
    private java.util.List<com.five.live.model.data.videos.List> list = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public java.util.List<com.five.live.model.data.videos.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.five.live.model.data.videos.List> list) {
        this.list = list;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
