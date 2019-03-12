package com.demo.test.pages;

import org.openqa.selenium.By;
/**
 * @description 登录页面元素定位声明
 * */
public class LoginPage  {
    /**用户名输入框*/
    public static final By LP_INPUT_USERNAME = By.cssSelector("body > div.layui-container > div > form > div:nth-child(1) > div > input");
    
    /**密码输入框*/
    public static final By LP_INPUT_PASSWORD = By.cssSelector("body > div.layui-container > div > form > div:nth-child(2) > div > input");
    
    
    /**登录按钮*/
    public static final By LP_BUTTON_LOGIN = By.cssSelector("body > div.layui-container > div > form > div.layui-form-item.cc-login-btn > button");
    
    
    /**登录错误信息*/
    public static final By LP_TEXT_ERROR= By.cssSelector("body > div.layui-layer-move");
    
    public static final By tsxx=By.cssSelector("body > div.layui-layer-move");
    
}