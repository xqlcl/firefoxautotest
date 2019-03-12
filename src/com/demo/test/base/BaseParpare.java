package com.demo.test.base;
/**
 * @Description ���Կ�ʼ �� ���Խ��� �Ĳ���
 * 
 * */
import java.io.IOException;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.demo.test.utils.ExcelDataProvider;
import com.demo.test.utils.LogConfiguration;
import com.demo.test.utils.SeleniumUtil;
public class BaseParpare {
    //�����ҳ����־ ��ʼ��
    static Logger logger = Logger.getLogger(BaseParpare.class.getName());
    protected static SeleniumUtil seleniumUtil = null;
    
    // ��ӳ�Ա��������ȡbeforeClass�����context����
    protected ITestContext testContext = null;
    protected String webUrl="";
    protected int timeOut = 0;
    
  // @BeforeClass
    @BeforeSuite
    /**������������򿪲���ҳ��*/
    public void startTest(ITestContext context) {
        LogConfiguration.initLog(this.getClass().getSimpleName());
        seleniumUtil = new SeleniumUtil();
        // ����õ���contextֵ
        this.testContext = context;
        //��testng.xml�ļ��л�ȡ�����������ֵ
        String browserName = context.getCurrentXmlTest().getParameter("browserName");
        timeOut = Integer.valueOf(context.getCurrentXmlTest().getParameter("timeOut"));
        webUrl = context.getCurrentXmlTest().getParameter("testurl");
        
        

        try {
            //���������launchBrowser���������Լ���������Ҫ�Ǵ��������������Ե�ַ������󻯴���
            seleniumUtil.launchBrowser(browserName, context,webUrl,timeOut);
        } catch (Exception e) {
            logger.error("������������������������ǲ��Ǳ��ֶ��رջ�������ԭ��",e);
        }
        //����һ��testng���������ԣ���driver��������֮�����ʹ��context��ʱȡ������Ҫ���ṩarrow ��ȡdriver����ʹ�õģ���Ϊarrow��ͼ������Ҫһ��driver����
        testContext.setAttribute("SELENIUM_DRIVER", seleniumUtil.driver);
    }
    
    /**
     * ���������ṩ�� - ����
     * */
    @DataProvider(name = "testData")
    public Iterator<Object[]> dataFortestMethod() throws IOException {
        String moduleName = null; // ģ�������
        String caseNum = null; // �������
        String className = this.getClass().getName();
        System.out.println(className);
        int dotIndexNum = className.indexOf("."); // ȡ�õ�һ��.��index
        System.out.println(dotIndexNum);
        int underlineIndexNum = className.indexOf("_"); // ȡ�õ�һ��_��index
        System.out.println(underlineIndexNum);
        if (dotIndexNum > 0) {    
            moduleName = className.substring(24, className.lastIndexOf(".")); // ȡ��ģ�������
            
            System.out.println("ģ������"+moduleName);
        }

        if (underlineIndexNum > 0) {
            caseNum = className.substring(underlineIndexNum + 1, underlineIndexNum + 4); // ȡ���������
            System.out.println(caseNum);
        }
        //��ģ�����ƺ������ı�Ŵ��� ExcelDataProvider ��Ȼ����ж�ȡexcel����
        return new ExcelDataProvider(moduleName, caseNum);
    }

//    @AfterClass
//    /**�������Թر������*/
//    public void endTest() {
//        if (seleniumUtil.driver != null) {
//            //�˳������
//            seleniumUtil.quit();
//        } else {
//            logger.error("�����driverû�л�ö���,�˳�����ʧ��");
//            Assert.fail("�����driverû�л�ö���,�˳�����ʧ��");
//        }
//    }
	
}