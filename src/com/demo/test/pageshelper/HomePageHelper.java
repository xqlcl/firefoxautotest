package com.demo.test.pageshelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.demo.test.pages.FramePage;
import com.demo.test.pages.HomePage;
import com.demo.test.pages.SearchPage;

import com.demo.test.utils.SeleniumUtil;

/**
 * @desciption ��ҳ�����ࣺר���ṩ�����ҳ����в����ķ�����װ
 */
public class HomePageHelper {
    // �ṩ��������־�������
    public static Logger logger = Logger.getLogger(HomePageHelper.class);

    /**
     * @author Young
     * @description �ȴ���ҳԪ�ؼ���
     * @param seleniumUtil
     *            selenium api��װ���ö���
     * @param timeOut
     *            �ȴ�Ԫ�س�ʱ��ʱ��
     * @throws InterruptedException 
     * */
    public static void waitHomePageLoad(SeleniumUtil seleniumUtil, int timeOut) throws InterruptedException {
        FramePageHelper.jumpOut(seleniumUtil);
        // �ȴ�body frame��ʾ����
        seleniumUtil.waitForElementToLoad(timeOut, FramePage.FP_FRAME_BODY);
       // FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_BODY);// �Ƚ��뵽body
                                                                                // frame��
        // �ȴ�navbar frame��ʾ����
//        seleniumUtil.waitForElementToLoad(timeOut, FramePage.FP_FRAME_NAVBAR);
//        FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_NAVBAR);// �ٽ���body
                                                                                // frame����frame:navbar
                                                                                // frame��
        logger.info("��ʼ�ȴ���ҳԪ�ؼ���");
        seleniumUtil.waitForElementToLoad(timeOut, HomePage.znjc_text);
        seleniumUtil.waitForElementToLoad(timeOut, HomePage.hbjp_text);
//        seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_BUTTON_HOME);
//        seleniumUtil.waitForElementToLoad(timeOut, HomePage.HP_BUTTON_SIGNOFF);
        logger.info("��ҳԪ�ؼ������");
       // FramePageHelper.jumpOut(seleniumUtil);
         
    }

    /**
     * @throws InterruptedException 
     * @Description ��¼�ɹ�֮����֤�û����ǲ�����ȷ��
     * */
    public static void checkUserName(SeleniumUtil seleniumUtil, int timeOut,
            String username) throws InterruptedException {
       // FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_BODY);// �Ƚ��뵽body
                                                                                // frame��
        //FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_INFO);// �Ƚ��뵽body
                                                                                // frame�е���frame:info
                                                                                // frame��
        logger.info("��ʼ����û����ǲ��ǣ�" + username);
        seleniumUtil.isTextCorrect(
                seleniumUtil.getText(HomePage.HP_TEXT_USERNAME), username);
        logger.info("�û���������,�û����ǣ�" + username);
        
       // seleniumUtil.click(HomePage.tc);
        
       // seleniumUtil.get("http://47.96.98.213:9999/login");
        
//        seleniumUtil.mouseMoveToElement(HomePage.mv);
//        seleniumUtil.click(HomePage.tc); 
    	
//        searchTest st=new searchTest();
//    st.searchTest("20180614152114463");

        
        
       
    }

    /**
     * @description ��¼�ɹ�֮����֤��ҳ���ı�����
     * */
    public static void checkHomeText(SeleniumUtil seleniumUtil) {
        FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_BODY);// �Ƚ��뵽body
                                                                                // frame��
        FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_INFO);// �Ƚ��뵽body
                                                                                // frame�е���frame:info
                                                                                // frame��
        seleniumUtil
                .isTextCorrect(
                        seleniumUtil.getText(HomePage.HP_TEXT_HOME),
                        "Welcome, jojo, to the Web Tours reservation pages."
                                + "\n"
                                + "Using the menu to the left, you can search for new flights to book, or review/edit the flights already booked. Don't forget to sign off when you're done!");

    }

}