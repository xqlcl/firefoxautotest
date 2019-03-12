package com.demo.test.pages;

import org.openqa.selenium.By;

/**
 * @description 首页面元素定位声明
 * */
public class HomePage {
    
    /**用户名显示区域*/
    public static final By HP_TEXT_USERNAME= By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-side.layui-bg-black > div.user-photo > p > span");
    /**Flights按钮*/
    public static final By HP_BUTTON_FLIGHTS = By.xpath("//*[@src='/WebTours/images/flights.gif']");
    /**Itinerary按钮*/
    public static final By HP_BUTTON_ITINERARY = By.xpath("//*[@src='/WebTours/images/itinerary.gif']");
    /**Home按钮*/
    public static final By HP_BUTTON_HOME = By.xpath("//*[@src='/WebTours/images/in_home.gif']");
    /**Sign Off按钮*/
    public static final By HP_BUTTON_SIGNOFF = By.xpath("//*[@src='/WebTours/images/signoff.gif']");
    /**首页完整文本*/
    public static final By HP_TEXT_HOME= By.xpath("//blockquote"); 
    
    public static final By znjc_text=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-header.header > div > ul > li:nth-child(4) > a > cite");
    public static final By hbjp_text=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-header.header > div > ul > li:nth-child(5) > a > cite");
    public static final By tc=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-header.header > div > ul > li.layui-nav-item.cc-avatar > dl > dd:nth-child(2) > a > cite");
    public static final By mv=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-header.header > div > ul > li.layui-nav-item.cc-avatar > a > cite");
   
}