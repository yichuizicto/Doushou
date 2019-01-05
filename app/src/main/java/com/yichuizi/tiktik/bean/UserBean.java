package com.yichuizi.tiktik.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/3 10:19
 * 描述：
 */
public class UserBean implements Parcelable {
    private long mUserId;
    private String mName;
    private String mAvatarLink;
    private List<VideoBean> mUserVideos;

    public UserBean(String mName, String mAvatarLink) {
        this.mName = mName;
        this.mAvatarLink = mAvatarLink;
    }

    public long getmUserId() {
        return mUserId;
    }

    public void setmUserId(long mUserId) {
        this.mUserId = mUserId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAvatarLink() {
        return mAvatarLink;
    }

    public void setmAvatarLink(String mAvatarLink) {
        this.mAvatarLink = mAvatarLink;
    }

    public List<VideoBean> getmUserVideos() {
        return mUserVideos;
    }

    public void setmUserVideos(List<VideoBean> mUserVideos) {
        this.mUserVideos = mUserVideos;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mUserId);
        dest.writeString(this.mName);
        dest.writeString(this.mAvatarLink);
        dest.writeTypedList(this.mUserVideos);
    }

    protected UserBean(Parcel in) {
        this.mUserId = in.readLong();
        this.mName = in.readString();
        this.mAvatarLink = in.readString();
        this.mUserVideos = in.createTypedArrayList(VideoBean.CREATOR);
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
