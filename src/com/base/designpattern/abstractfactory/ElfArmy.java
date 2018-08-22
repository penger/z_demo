package com.base.designpattern.abstractfactory;

/**
 * Created by gongp on 2018/8/22.
 */
public class ElfArmy implements Army{
    static final String DESC="THIS IS ELF ARMY";
    @Override
    public String getDescription() {
        return DESC;
    }
}
