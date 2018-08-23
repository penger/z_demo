package com.base.designpattern.abstractfactory;

/**
 * Created by gongp on 2018/8/22.
 */
public class EleKingFactory implements KindomFactory {
    @Override
    public Castle getCastle() {
        return new ElfCastle();
    }

    @Override
    public King getKing() {
        return new ElfKing();
    }

    @Override
    public Army getArmy() {
        return new ElfArmy();
    }
}
