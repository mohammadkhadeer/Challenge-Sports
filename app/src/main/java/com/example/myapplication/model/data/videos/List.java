
package com.example.myapplication.model.data.videos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cf_hls_url")
    @Expose
    private String cfHlsUrl;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("create_time")
    @Expose
    private String createTime;
    @SerializedName("thumbnail_path")
    @Expose
    private String thumbnailPath;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("cf_resp")
    @Expose
    private String cfResp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCfHlsUrl() {
        return cfHlsUrl;
    }

    public void setCfHlsUrl(String cfHlsUrl) {
        this.cfHlsUrl = cfHlsUrl;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCfResp() {
        return cfResp;
    }

    public void setCfResp(String cfResp) {
        this.cfResp = cfResp;
    }

}
