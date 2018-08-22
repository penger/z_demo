package com.base.designpattern.abstractfactory;

/**
 * Created by gongp on 2018/8/22.
 */
public interface KindomFactory {
    Castle getCastle();
    King getKing();
    Army getArmy();
}
