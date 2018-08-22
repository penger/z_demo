package com.base.designpattern.abstractfactory;

/**
 * Created by gongp on 2018/8/22.
 */
public class ElfCastle implements Castle {
    static final String DESC = " THIS IS ELE CASTLE";

    @Override
    public String getDescription() {
        return DESC;
    }
}
