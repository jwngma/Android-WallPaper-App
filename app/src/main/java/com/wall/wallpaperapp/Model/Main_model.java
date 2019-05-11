package com.wall.wallpaperapp.Model;

public class Main_model {

    private String mImageurls;
    private String mCreators;
    private String mLikes;

    public Main_model() {
    }

    public Main_model(String mImageurls, String mCreators, String mLikes) {
        this.mImageurls = mImageurls;
        this.mCreators = mCreators;
        this.mLikes = mLikes;
    }

    public String getmImageurls() {
        return mImageurls;
    }

    public void setmImageurls(String mImageurls) {
        this.mImageurls = mImageurls;
    }

    public String getmCreators() {
        return mCreators;
    }

    public void setmCreators(String mCreators) {
        this.mCreators = mCreators;
    }

    public String getmLikes() {
        return mLikes;
    }

    public void setmLikes(String mLikes) {
        this.mLikes = mLikes;
    }
}
