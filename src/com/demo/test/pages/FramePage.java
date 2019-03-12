package com.demo.test.pages;

import org.openqa.selenium.By;

/**
 * @description 这个类算不上一个page页，因为这个WebTours站点涉及到的frame比较多，所以我们把frame抓取出来用page页来存储
 * */
public class FramePage {
    
    /**header frame名字 */
    public static final By FP_FRAME_HEADER = By.cssSelector("head > title");
    /** body frame 名字 */
    public static final By FP_FRAME_BODY = By.className("main_body");
    /**navbar frame名字*/
    public static final By FP_FRAME_NAVBAR = By.name("navbar");
    /**info frame名字*/
    public static final By FP_FRAME_INFO = By.name("info");
    
    public static final By FP_FRAME_TEXT=By.linkText("决策系统");
    
    public static final By searchTest=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-body.layui-form > div > div > div.layui-tab-item.layui-show > iframe");
}