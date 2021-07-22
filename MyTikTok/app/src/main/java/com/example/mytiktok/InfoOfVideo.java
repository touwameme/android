package com.example.mytiktok;

import com.google.gson.annotations.SerializedName;

import java.util.SplittableRandom;

public class InfoOfVideo {
    @SerializedName("_id")
    public String id;
    @SerializedName("feedurl")
    public String feedUrl;
    @SerializedName("nickname")
    public String nickName;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public int likeCount;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("thumbnails")
    public String thumbnails;

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", feedUrl='" + feedUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", description='" + description + '\'' +
                ", likeCount=" + likeCount +
                ", avatar='" + avatar + '\'' +
                ", avatar='" + avatar + '\'' +
                ", thumbnails='" + thumbnails + '\'' +
                '}';
    }
    public InfoOfVideo(String id, String feedUrl, String nickName, String description, int likeCount, String avatar, String thumbnails){
        this.id = id;
        this.avatar = avatar;
        this.description = description;
        this.nickName = nickName;
        this.likeCount = likeCount;
        this.feedUrl = feedUrl;
        this.thumbnails = thumbnails;
    }
}
