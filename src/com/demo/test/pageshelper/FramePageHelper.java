package com.demo.test.pageshelper;

import org.openqa.selenium.By;

import com.demo.test.utils.SeleniumUtil;

/**
 * @description �����������Ҫ�ǽ���frame�������������Ĳ���
 * */
public class FramePageHelper {

    /** ����frame-����frame��Ԫ�ض�λ���� */
    public static void jumpInToFrame(SeleniumUtil seleniumUtil, By by) {
      //  seleniumUtil.switchFrame(seleniumUtil.findElementBy(by));
    }

    /** �ص�Ĭ�ϵ�frame */
    public static void jumpOut(SeleniumUtil seleniumUtil) {
        seleniumUtil.outFrame();
    }
}