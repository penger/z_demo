package com.base.designpattern.play.football;

/**
 * Created by gongp on 2018/8/24.
 */
public class Ball {


    private String logo;
    private int wight;

    public Ball(String logo, int wight) {
        this.logo = logo;
        this.wight = wight;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "logo='" + logo + '\'' +
                ", wight=" + wight +
                '}';
    }
}
