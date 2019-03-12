package com.demo.test.pages;

import org.openqa.selenium.By;

/**
 * @description ��ҳ��Ԫ�ض�λ����
 * */
public class HomePage {
    
    /**�û�����ʾ����*/
    public static final By HP_TEXT_USERNAME= By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-side.layui-bg-black > div.user-photo > p > span");
    /**Flights��ť*/
    public static final By HP_BUTTON_FLIGHTS = By.xpath("//*[@src='/WebTours/images/flights.gif']");
    /**Itinerary��ť*/
    public static final By HP_BUTTON_ITINERARY = By.xpath("//*[@src='/WebTours/images/itinerary.gif']");
    /**Home��ť*/
    public static final By HP_BUTTON_HOME = By.xpath("//*[@src='/WebTours/images/in_home.gif']");
    /**Sign Off��ť*/
    public static final By HP_BUTTON_SIGNOFF = By.xpath("//*[@src='/WebTours/images/signoff.gif']");
    /**��ҳ�����ı�*/
    public static final By HP_TEXT_HOME= By.xpath("//blockquote"); 
    
    public static final By znjc_text=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-header.header > div > ul > li:nth-child(4) > a > cite");
    public static final By hbjp_text=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-header.header > div > ul > li:nth-child(5) > a > cite");
    public static final By tc=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-header.header > div > ul > li.layui-nav-item.cc-avatar > dl > dd:nth-child(2) > a > cite");
    public static final By mv=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-header.header > div > ul > li.layui-nav-item.cc-avatar > a > cite");
   
}