package com.wall.wallpaperapp.Model;

public class SlideShowModel {
    private String likes;
    private String  largeImageURL;
    private String webformatURL;
    private String tags;
    private String previewURL;

    public SlideShowModel() {
    }

    public SlideShowModel(String likes, String largeImageURL, String webformatURL, String tags, String previewURL) {

        this.likes = likes;
        this.largeImageURL = largeImageURL;
        this.webformatURL = webformatURL;
        this.tags = tags;
        this.previewURL = previewURL;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }
}
