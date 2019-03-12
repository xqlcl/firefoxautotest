package com.demo.test.pageshelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.demo.test.pages.FramePage;
import com.demo.test.pages.LoginPage;
import com.demo.test.utils.SeleniumUtil;

/**
 * @description ��¼ҳ������ࣺ�ṩ�����ҳ�������Ĳ����ķ�����װ
 * */
public class LoginPageHelper {
    // �ṩ��������־�������
    public static Logger logger = Logger.getLogger(LoginPageHelper.class);

    /**
     * @description �ȴ���¼ҳ��Ԫ�ؼ���
     * @param seleniumUtil
     *            selenium api��װ���ö���
     * @param timeOut
     *            �ȴ�Ԫ�س�ʱ��ʱ��
     * */
    public static void waitLoginPageLoad(SeleniumUtil seleniumUtil, int timeOut) {
        seleniumUtil.pause(1000);
        // �Դ˴��Ľ��ͣ������¼ҳ����������frame��һ��headerһ��body ,
        // ����¼���û���������������Լ���¼��ť����body frame�µ�navbar frame��,
        // ������Ҫ����body frame�У�Ȼ���ڽ���navbar frame�У����ܲ��ҵ���¼��������Ԫ��
//        FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_BODY);// �Ƚ��뵽body
//                                                                                // frame��
//        FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_NAVBAR);// �ٽ���body
                                                                                // frame����frame:navbar
                                                                                // frame��
        logger.info("��ʼ����¼ҳ��Ԫ��");
        seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_INPUT_USERNAME);
        seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_INPUT_PASSWORD);
        seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_BUTTON_LOGIN);
        logger.info("����¼ҳ��Ԫ�����");
    }

    /**
     * @description ��¼������װ
     * @param seleniumUtil
     *            selenium api��װ���ö���
     * @param username
     *            �û���ֵ
     * @param password
     *            �û�����ֵ
     * @throws InterruptedException 
     * */
    public static void typeLoginInfo(SeleniumUtil seleniumUtil,
            String username, String password) throws InterruptedException {

        logger.info("��ʼ�����¼��Ϣ");
        // ����û��������
        seleniumUtil.clear(LoginPage.LP_INPUT_USERNAME);
        // �����û������û��������
        seleniumUtil.type(LoginPage.LP_INPUT_USERNAME,username);
        // ������������
        seleniumUtil.clear(LoginPage.LP_INPUT_PASSWORD);
        // �������뵽���������
        seleniumUtil.type(LoginPage.LP_INPUT_PASSWORD, password);
        logger.info("�����¼��Ϣ���");
        
        Thread.sleep(8000);
        // �����¼��ť
        seleniumUtil.click(LoginPage.LP_BUTTON_LOGIN);
        
       

    }

    /**
     * @description ��֤��¼������Ϣ
     * @param seleniumUtil
     *            selenium api��װ���ö���
     * @param error
     *            �����ı�
     * */
    public static void checkLoginErrorInfo(SeleniumUtil seleniumUtil,
            String error) {
//        FramePageHelper.jumpOut(seleniumUtil);
//        FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_TEXT);
//        FramePageHelper.jumpInToFrame(seleniumUtil, FramePage.FP_FRAME_INFO);
        seleniumUtil.isTextCorrect(
                seleniumUtil.getText(LoginPage.LP_TEXT_ERROR), "�û������������");
    }
}