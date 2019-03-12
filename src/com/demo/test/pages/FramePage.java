package com.demo.test.pages;

import org.openqa.selenium.By;

/**
 * @description ������㲻��һ��pageҳ����Ϊ���WebToursվ���漰����frame�Ƚ϶࣬�������ǰ�frameץȡ������pageҳ���洢
 * */
public class FramePage {
    
    /**header frame���� */
    public static final By FP_FRAME_HEADER = By.cssSelector("head > title");
    /** body frame ���� */
    public static final By FP_FRAME_BODY = By.className("main_body");
    /**navbar frame����*/
    public static final By FP_FRAME_NAVBAR = By.name("navbar");
    /**info frame����*/
    public static final By FP_FRAME_INFO = By.name("info");
    
    public static final By FP_FRAME_TEXT=By.linkText("����ϵͳ");
    
    public static final By searchTest=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-body.layui-form > div > div > div.layui-tab-item.layui-show > iframe");
}