package com.base.designpattern.play.football;

/**
 * Created by gongp on 2018/8/24.
 */
abstract class AbstractFactory {
    public abstract Ball ball();
    public abstract Shoe createShoe();
}
