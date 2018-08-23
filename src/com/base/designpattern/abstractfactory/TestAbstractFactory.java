package com.base.designpattern.abstractfactory;

/**
 * Created by gongp on 2018/8/22.
 */
public class TestAbstractFactory {
    public static void main(String[] args) {
        //jdk里此模式使用场景
        /*
        DocumentBuilderFactory
        TransformerFactory
        XPathFactory
         */

        EleKingFactory eleKingFactory = new EleKingFactory();
        System.out.println(eleKingFactory.getArmy().getDescription());
        System.out.println(eleKingFactory.getCastle().getDescription());
        System.out.println(eleKingFactory.getKing().getDescription());
    }
}
