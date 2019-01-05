package com.yichuizi.tiktik.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/3 10:19
 * 描述：
 */
public class VideoBean implements Parcelable {
    private long mVideoId;
    private String mLink;
    private String mCoverUrl;
    private String mTitle;
    private String mMusicName;
    private String mLocal;
    private int mPraise;
    private int mComment;
    private int mShare;
    private UserBean mUserBean;

    public long getmVideoId() {
        return mVideoId;
    }

    public void setmVideoId(long mVideoId) {
        this.mVideoId = mVideoId;
    }

    public String getmLink() {
        return mLink;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }

    public String getmCoverUrl() {
        return mCoverUrl;
    }

    public void setmCoverUrl(String mCoverUrl) {
        this.mCoverUrl = mCoverUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmMusicName() {
        return mMusicName;
    }

    public void setmMusicName(String mMusicName) {
        this.mMusicName = mMusicName;
    }

    public String getmLocal() {
        return mLocal;
    }

    public void setmLocal(String mLocal) {
        this.mLocal = mLocal;
    }

    public int getmPraise() {
        return mPraise;
    }

    public void setmPraise(int mPraise) {
        this.mPraise = mPraise;
    }

    public int getmComment() {
        return mComment;
    }

    public void setmComment(int mComment) {
        this.mComment = mComment;
    }

    public int getmShare() {
        return mShare;
    }

    public void setmShare(int mShare) {
        this.mShare = mShare;
    }

    public UserBean getmUserBean() {
        return mUserBean;
    }

    public void setmUserBean(UserBean mUserBean) {
        this.mUserBean = mUserBean;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mVideoId);
        dest.writeString(this.mLink);
        dest.writeString(this.mCoverUrl);
        dest.writeString(this.mTitle);
        dest.writeString(this.mMusicName);
        dest.writeString(this.mLocal);
        dest.writeInt(this.mPraise);
        dest.writeInt(this.mComment);
        dest.writeInt(this.mShare);
        dest.writeParcelable(this.mUserBean, flags);
    }

    public VideoBean() {
    }

    protected VideoBean(Parcel in) {
        this.mVideoId = in.readLong();
        this.mLink = in.readString();
        this.mCoverUrl = in.readString();
        this.mTitle = in.readString();
        this.mMusicName = in.readString();
        this.mLocal = in.readString();
        this.mPraise = in.readInt();
        this.mComment = in.readInt();
        this.mShare = in.readInt();
        this.mUserBean = in.readParcelable(UserBean.class.getClassLoader());
    }

    public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel source) {
            return new VideoBean(source);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };
}
