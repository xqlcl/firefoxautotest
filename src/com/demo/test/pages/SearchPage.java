package com.demo.test.pages;

import org.openqa.selenium.By;

public class SearchPage {
	
    public static final By jcbh = By.cssSelector("#decisionCode");
    
    public static final By sjhm=By.cssSelector("#contactPhone");
    
    public static final By cx=By.cssSelector("#decision_form > div > div:nth-child(3) > div:nth-child(3) > a.layui-btn.search_btn");

    public static final By sjh=By.cssSelector("#decision_form > div > div:nth-child(1) > div:nth-child(3) > label");
    
    public static final By kfzt=By.cssSelector("#decision_form > div > div:nth-child(3) > div:nth-child(2) > div > div > div > input");
    
    public static final By ykf=By.cssSelector("#decision_form > div > div:nth-child(3) > div:nth-child(2) > div > div > dl > dd:nth-child(4)");

    public static final By jcxt=By.cssSelector("body > div.layui-layout.layui-layout-admin > div.layui-header.header > div > ul > li:nth-child(2) > a > cite");
                                                
}
