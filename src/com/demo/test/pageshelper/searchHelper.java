package com.demo.test.pageshelper;

import org.apache.log4j.Logger;

import com.demo.test.pages.FramePage;
import com.demo.test.pages.LoginPage;
import com.demo.test.pages.SearchPage;
import com.demo.test.utils.SeleniumUtil;

public class searchHelper {
	
	 // �ṩ��������־�������
    public static Logger logger = Logger.getLogger(HomePageHelper.class);
    
    public static void searchPageReload(SeleniumUtil seleniumUtil, int timeOut) throws InterruptedException{
    	
    	seleniumUtil.click(SearchPage.jcxt);
    	
   	 Thread.sleep(3000);
    	
    	System.out.println("xiao��");
    	seleniumUtil.waitForElementToLoad(timeOut, FramePage.searchTest);
    	System.out.println("��ô����");
		FramePageHelper.jumpInToFrame(seleniumUtil,FramePage.searchTest);
		
		logger.info("��ʼ�ȴ���ѯԪ�ؼ���");
		
		seleniumUtil.waitForElementToLoad(timeOut, SearchPage.sjh);
		
		seleniumUtil.waitForElementToLoad(timeOut, SearchPage.kfzt);
		
		logger.info("��ѯ����������");
		
		

		       
		        
		       

		    
    	
    }
    public static void srcx(SeleniumUtil seleniumUtil,
            String jcbh,String sjhm) throws InterruptedException{
		 logger.info("��ʼ�����ѯ��Ϣ");
	        
	        seleniumUtil.type(SearchPage.jcbh, jcbh);
	        
	        seleniumUtil.type(SearchPage.sjhm, sjhm);
//	        // ����û��������
//	        seleniumUtil.clear(LoginPage.LP_INPUT_USERNAME);
//	        // �����û������û��������
//	        seleniumUtil.type(LoginPage.LP_INPUT_USERNAME,username);
//	        // ������������
//	        seleniumUtil.clear(LoginPage.LP_INPUT_PASSWORD);
//	        // �������뵽���������
//	        seleniumUtil.type(LoginPage.LP_INPUT_PASSWORD, password);
	        logger.info("���������Ϣ���");
//	        
	        Thread.sleep(3000);
//	        // �����¼��ť
	        seleniumUtil.click(SearchPage.cx);
		
	}

}
