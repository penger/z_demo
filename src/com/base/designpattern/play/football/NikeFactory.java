package com.base.designpattern.play.football;

import java.util.Random;

/**
 * Created by gongp on 2018/8/24.
 */
public class NikeFactory extends BallFactory {
    private Random generator = new Random();
    @Override
    public Ball createBall() {
        return new Ball("nike",generator.nextInt(10000)+1);
    }
}
