package com.base.designpattern.abstractfactory;

/**
 * Created by gongp on 2018/8/22.
 */
public class ElfKing  implements  King{
    static  final String DESC =" THIS IS ELF KING";
    @Override
    public String getDescription() {
        return DESC;
    }
}
