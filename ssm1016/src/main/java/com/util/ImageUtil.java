package com.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageUtil {
    //1.定义空字符串 用以保存生成的验证码
    static String code = "";
    //2.生成验证码
    public static String createcode(){
        code="";
        String a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdeghijklmnopqrstuvwxyz";
        for (int i = 0; i < 4; i++) {
            int index = (int)(Math.random()*62);
            char b = a.charAt(index);
            code+=b;
        }
        return code;
    }
    //查看生成的验证码
    public static String getCode(){
        return code;
    }

//3.生成图片
    public static BufferedImage createimage(){
        BufferedImage bi = new BufferedImage(100,30,BufferedImage.TYPE_INT_RGB);
        //1.生成画布
        Graphics g = bi.getGraphics();
        //2.添加背景颜色
        g.setColor(Color.magenta);
        g.fillRect(0,0,80,30);

        //3.添加干扰线
        for (int i = 0; i <30 ; i++) {
            Random r=  new Random();
            int red = r.nextInt(256);
            int green = r.nextInt(256);
            int blue = r.nextInt(256);
            Color c =new Color(red,green,blue);
            g.setColor(c);
            int x1 = r.nextInt(101);
            int y1=r.nextInt(31);
            int x2 = r.nextInt(101);
            int y2=r.nextInt(31);
            g.drawOval(x1,y1,x2,y2);

        }
        //4.添加文字
        g.setColor(Color.white);
        g.setFont(new Font("宋体",Font.BOLD,25));
        String str = getCode();

        //5.将文字填充到画板中
        g.drawString(str,10,25);

        //6.关闭画布
        g.dispose();
        return bi;
    }






}
