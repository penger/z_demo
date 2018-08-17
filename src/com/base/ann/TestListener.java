package com.base.ann;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TestListener {
	 JFrame jf = new JFrame("测试");
	    @ActionListenerAnno(listener=OkListener.class)
	    private JButton ok = new JButton("确认");
	    @ActionListenerAnno(listener=OkListener.class)
	    private JButton cancel = new JButton("取消");
	    public void init() throws IllegalArgumentException, IllegalAccessException, InstantiationException{
	        JPanel jp = new JPanel();
	        jp.add(ok);
	        jp.add(cancel);
	        jf.add(jp);
	        ButtonActionListener.process(this);
	        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        jf.pack();
	        jf.setLocationRelativeTo(null);
	        jf.setVisible(true);
	    }
	    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
	        new TestListener().init();
	    }
}


class OkListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("确认按钮被点击");
        JOptionPane.showMessageDialog(null, "确认按钮被点击");
    }
}

class CancelListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("取消按钮被点击");
        JOptionPane.showMessageDialog(null, "取消按钮被点击");
    }
    
}

class ButtonActionListener{
    public static void process(Object obj) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
        Class<? extends Object> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field f : fields){
            //将指定Field设置成可自由访问的，避免private的Field不能访问
            f.setAccessible(true);
            //获取指定Field的ActionListenerAnno类型的注解
            ActionListenerAnno a = f.getAnnotation(ActionListenerAnno.class);
            // 获取成员变量f的值
            Object fObj = f.get(obj);
            if(a != null && fObj instanceof AbstractButton){
                // 获取a注解里的listner元数据（它是一个监听器类）
                Class<? extends ActionListener> listenerClazz = a.listener();
                // 使用反射来创建listner类的对象
                ActionListener al = listenerClazz.newInstance();
                AbstractButton ab = (AbstractButton)fObj;
                // 为ab按钮添加事件监听器
                ab.addActionListener(al);
            }
        }
    }
}