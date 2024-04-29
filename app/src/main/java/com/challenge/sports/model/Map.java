package com.challenge.sports.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Map implements Parcelable {
    String keyword,redirect_url,open_type;

    public Map(String keyword, String redirect_url, String open_type) {
        this.keyword = keyword;
        this.redirect_url = redirect_url;
        this.open_type = open_type;
    }

    protected Map(Parcel in) {
        keyword = in.readString();
        redirect_url = in.readString();
        open_type = in.readString();
    }

    public static final Creator<Map> CREATOR = new Creator<Map>() {
        @Override
        public Map createFromParcel(Parcel in) {
            return new Map(in);
        }

        @Override
        public Map[] newArray(int size) {
            return new Map[size];
        }
    };

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getOpen_type() {
        return open_type;
    }

    public void setOpen_type(String open_type) {
        this.open_type = open_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(keyword);
        dest.writeString(redirect_url);
        dest.writeString(open_type);
    }
}