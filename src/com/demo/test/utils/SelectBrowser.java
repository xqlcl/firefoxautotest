package com.demo.test.utils;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

/**
 * @author young
 * @decription �ڲ�ͬ��ƽ̨��ѡ���Ӧ�������,ϵͳƽ̨�����Զ��ж���ʲôƽ̨
 * */

public class SelectBrowser {
    static Logger logger = Logger.getLogger(SelectBrowser.class.getName());
    
    public WebDriver selectExplorerByName(String browser, ITestContext context) {
        Properties props = System.getProperties(); // ���ϵͳ���Լ�
        String currentPlatform = props.getProperty("os.name"); // ����ϵͳ����
        logger.info("��ǰ����ϵͳ��:[" + currentPlatform + "]");
        logger.info("���������������[" + browser + "]");
        //��testNG�������ļ���ȡ����driverConfgFilePath��ֵ
        String driverConfgFilePath = context.getCurrentXmlTest().getParameter("driverConfgFilePath");
        /** ������������·�� */
        String chromedriver_win = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_win");
        String chromedriver_linux = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_linux");
        String chromedriver_mac = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_mac");
        String ghostdriver_win = PropertiesDataProvider.getTestData(driverConfgFilePath, "ghostdriver_win");
        String iedriver = PropertiesDataProvider.getTestData(driverConfgFilePath, "iedriver");
        String firefoxdriver_win = PropertiesDataProvider.getTestData(driverConfgFilePath, "firefoxdriver_win");

        if (currentPlatform.toLowerCase().contains("win")) { //�����windowsƽ̨

            if (browser.equalsIgnoreCase("ie")) {
                System.setProperty("webdriver.ie.driver", iedriver);
                //IE�ĳ������ã�����ִ���Զ�������
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                //����ie���������
                return new InternetExplorerDriver(ieCapabilities);
            } else if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", chromedriver_win);
                //���عȸ����������
                
                 return new ChromeDriver();
                 
            } else if (browser.equalsIgnoreCase("firefox")) {
                //���ػ�����������
              // System.setProperty("webdriver.firefox.driver", firefoxdriver_win);
            	//System.setProperty("webdriver.firefox.driver", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
            	//selenium 2.53.1 ���� 2.51.0  ��Ӧ���45,46
              
            	return new FirefoxDriver();

            } else if(browser.equalsIgnoreCase("ghost")){
                DesiredCapabilities ghostCapabilities = new DesiredCapabilities();
                ghostCapabilities.setJavascriptEnabled(true);                       
                ghostCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, ghostdriver_win);
                //����ghost����
                return new PhantomJSDriver(ghostCapabilities);
                
            }else {

                logger.error("The [" + browser + "]" + " explorer is not applicable  for  [" + currentPlatform + "] OS");
                Assert.fail("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");

            }

        } else if (currentPlatform.toLowerCase().contains("linux")) { //�����linuxƽ̨

            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", chromedriver_linux);
                return new ChromeDriver();

            } else if (browser.equalsIgnoreCase("firefox")) {
                return new FirefoxDriver();
            } else {
                logger.error("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
                Assert.fail("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
            }

        } else if (currentPlatform.toLowerCase().contains("mac")) { //�����macƽ̨
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", chromedriver_mac);
                return new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
               // return new FirefoxDriver();
            } else {
                logger.error("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
                Assert.fail("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
            }

        } else
            logger.error("The [" + currentPlatform + "] is not supported for this automation frame,please change the OS(Windows,MAC or LINUX)");
        
        Assert.fail("The [" + currentPlatform + "] is not supported for this automation frame,please change the OS(Windows,MAC or LINUX)");
        return null;
    }
}