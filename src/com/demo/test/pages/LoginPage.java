package com.demo.test.pages;

import org.openqa.selenium.By;
/**
 * @description ��¼ҳ��Ԫ�ض�λ����
 * */
public class LoginPage  {
    /**�û��������*/
    public static final By LP_INPUT_USERNAME = By.cssSelector("body > div.layui-container > div > form > div:nth-child(1) > div > input");
    
    /**���������*/
    public static final By LP_INPUT_PASSWORD = By.cssSelector("body > div.layui-container > div > form > div:nth-child(2) > div > input");
    
    
    /**��¼��ť*/
    public static final By LP_BUTTON_LOGIN = By.cssSelector("body > div.layui-container > div > form > div.layui-form-item.cc-login-btn > button");
    
    
    /**��¼������Ϣ*/
    public static final By LP_TEXT_ERROR= By.cssSelector("body > div.layui-layer-move");
    
    public static final By tsxx=By.cssSelector("body > div.layui-layer-move");
    
}