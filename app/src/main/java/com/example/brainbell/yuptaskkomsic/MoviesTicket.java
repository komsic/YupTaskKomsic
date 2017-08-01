package com.example.brainbell.yuptaskkomsic;

/**
 * Created by BRAINBELL on 7/15/2017.
 */
public class MoviesTicket {

    private int mImageResourceId;
    private String mTitle;
    private String mDirector;
    private double mRating;
    private int mDuration;
    private String mPlot;
    private int mAudioResourceId;

    public MoviesTicket(String mTitle) {
        this.mTitle = mTitle;
    }

    public MoviesTicket(int imageResourceId, String title, String director, double rating,
                        int duration, String plot, int audioResourceId) {
        mImageResourceId = imageResourceId;
        mTitle = title;
        mDirector = director;
        mRating = rating;
        mDuration = duration;
        mPlot = plot;
        mAudioResourceId = audioResourceId;

    }


    public int getImageResourceId() {
        return mImageResourceId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDirector() {
        return mDirector;
    }

    public double getRating() {
        return mRating;
    }

    public int getDuration() {
        return mDuration;
    }

    public String getPlot() {
        return mPlot;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
