package com.demo.test.pageshelper;

import org.apache.log4j.Logger;

import com.demo.test.pages.FramePage;
import com.demo.test.pages.LoginPage;
import com.demo.test.pages.SearchPage;
import com.demo.test.utils.SeleniumUtil;

public class searchHelper {
	
	 // 提供本类中日志输出对象
    public static Logger logger = Logger.getLogger(HomePageHelper.class);
    
    public static void searchPageReload(SeleniumUtil seleniumUtil, int timeOut) throws InterruptedException{
    	
    	seleniumUtil.click(SearchPage.jcxt);
    	
   	 Thread.sleep(3000);
    	
    	System.out.println("xiao草");
    	seleniumUtil.waitForElementToLoad(timeOut, FramePage.searchTest);
    	System.out.println("怎么回事");
		FramePageHelper.jumpInToFrame(seleniumUtil,FramePage.searchTest);
		
		logger.info("开始等待查询元素加载");
		
		seleniumUtil.waitForElementToLoad(timeOut, SearchPage.sjh);
		
		seleniumUtil.waitForElementToLoad(timeOut, SearchPage.kfzt);
		
		logger.info("查询界面加载完毕");
		
		

		       
		        
		       

		    
    	
    }
    public static void srcx(SeleniumUtil seleniumUtil,
            String jcbh,String sjhm) throws InterruptedException{
		 logger.info("开始输入查询信息");
	        
	        seleniumUtil.type(SearchPage.jcbh, jcbh);
	        
	        seleniumUtil.type(SearchPage.sjhm, sjhm);
//	        // 清空用户名输入框
//	        seleniumUtil.clear(LoginPage.LP_INPUT_USERNAME);
//	        // 输入用户名到用户名输入框
//	        seleniumUtil.type(LoginPage.LP_INPUT_USERNAME,username);
//	        // 清空密码输入框
//	        seleniumUtil.clear(LoginPage.LP_INPUT_PASSWORD);
//	        // 输入密码到密码输入框
//	        seleniumUtil.type(LoginPage.LP_INPUT_PASSWORD, password);
	        logger.info("输入决策信息完毕");
//	        
	        Thread.sleep(3000);
//	        // 点击登录按钮
	        seleniumUtil.click(SearchPage.cx);
		
	}

}
