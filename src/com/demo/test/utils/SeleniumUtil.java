package com.demo.test.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;


/**
 * @Description ��װ����selenium�Ĳ����Լ�ͨ�÷������������д�����
 * */
public class SeleniumUtil {
    /** ʹ��Log4j����һ�����ǻ�ȡ��־��¼���������¼�������������־��Ϣ */
    public static Logger logger = Logger.getLogger(SeleniumUtil.class.getName());
    public ITestResult it = null;
    public WebDriver driver = null;
    public WebDriver window = null;
    public String current_handles="";



    /***
     * �������������ҳ��
     * */
    public void launchBrowser(String browserName, ITestContext context,String webUrl,int timeOut) {
        SelectBrowser select = new SelectBrowser();
        driver = select.selectExplorerByName(browserName, context);
        try {
            maxWindow(browserName);
            waitForPageLoading(timeOut);
            get(webUrl);
        } catch (TimeoutException e) {
            logger.warn("ע�⣺ҳ��û����ȫ���س�����ˢ�����ԣ���");
            refresh();
             JavascriptExecutor js = (JavascriptExecutor)driver;
            String status= (String)js.executeScript("return document.readyState");
        
            
            logger.info("��ӡ״̬��"+status);
        }

    }

    
    /**
     * ������������
     * */
    public void maxWindow(String browserName) {
        logger.info("��������:" + browserName);
        driver.manage().window().maximize();
    }

    /**
     * �趨��������ڴ�С�� ������������ڵĴ�С�����������Ƚϳ�������;��<br>
     * 1����ͳһ���������С���������������ԱȽ����׵ĸ�һЩ����ͼ��ȶԵĹ��߽��н��
     * ���������Ե�����Լ��ձ������ԡ�������Ը�sikuli��ϣ�ʹ��sikuli����flash��<br>
     * 2���ڲ�ͬ���������С�·��ʲ���վ�㣬�Բ���ҳ���ͼ�����棬Ȼ��۲��ʹ��ͼ��ȶԹ��߶Ա���ҳ���ǰ����ʽ�������⡣
     * ������Խ���������ó��ƶ��˴�С(320x480)��Ȼ������ƶ�վ�㣬������ʽ����������<br>
     * */
    public void setBrowserSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    /**
     * ��װ����Ԫ�صķ��� element
     * */
    public WebElement findElementBy(By by) {
        return driver.findElement(by);
    }

    /**
     * ��װ����Ԫ�صķ��� elements
     * */
    public List<WebElement> findElementsBy(By by) {
        return driver.findElements(by);
    }
    
    /**�������ӵ�url*/
    public void navigateTargetUrl(String url){
        driver.navigate().to(url);
        logger.info("��������"+url);
    }

    /**
     * ��װ�������- By
     * */
    public void click(By byElement) {

        try {
            clickTheClickable(byElement, System.currentTimeMillis(), 2500);
        } catch (StaleElementReferenceException e) {
            logger.error("The element you clicked:[" + byElement + "] is no longer exist!");
            Assert.fail("The element you clicked:[" + byElement + "] is no longer exist!");
        } catch (Exception e) {
            logger.error("Failed to click element [" + byElement + "]");
            Assert.fail("Failed to click element [" + byElement + "]",e);
        }
        logger.info("���Ԫ�� [" + byElement + "]");
    }
    
    public boolean isEnabled(By by){
        return driver.findElement(by).isEnabled();
    }
    
    /**�ύ*/
    public void submit(WebElement w){
        try{
        w.submit();
        
        }catch(Exception e){
            logger.error("��Ԫ�أ�"+w+"�����ύ����ʧ��");
            Assert.fail("��Ԫ�أ�"+w+"�����ύ����ʧ��");
        }
        logger.info("��Ԫ�أ�"+w+"�����ύ����");
    }

    /**
     * ��װ������� -webelment
     * */
    public void click(WebElement element) {

        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            logger.error("The element you clicked:[" + element.toString() + "] is no longer exist!");
            Assert.fail("The element you clicked:[" + element.toString() + "] is no longer exist!");
        } catch (Exception e) {
            logger.error("Failed to click element [" + element.toString() + "]");
            Assert.fail("Failed to click element [" + element.toString() + "]",e);
        }
        logger.info("���Ԫ�� [" + element.toString() + "]");
    }

    /** ���ܵ��ʱ�����Ե������ */
    public void clickTheClickable(By byElement, long startTime, int timeOut) throws Exception {
        try {
            findElementBy(byElement).click();
        } catch (Exception e) {
            if (System.currentTimeMillis() - startTime > timeOut) {
                logger.warn(byElement+ " is unclickable");
                throw new Exception(e);
            } else {
                Thread.sleep(500);
                logger.warn(byElement + " is unclickable, try again");
                clickTheClickable(byElement, startTime, timeOut);
            }
        }
    }

    /**
     * ���ҳ��ı���
     * */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * ���Ԫ�ص��ı�
     * */
    public String getText(By elementLocator) {
        return driver.findElement(elementLocator).getText().trim();
    }

    /**
     * ���Ԫ�� ���Ե��ı�
     * */
    public String getAttributeText(By elementLocator, String attribute) {
        return driver.findElement(elementLocator).getAttribute(attribute).trim();
    }

    /**
     * ��װ�������
     * */
    public void clear(By byElement) {
        try {
            findElementBy(byElement).clear();
        } catch (Exception e) {
            logger.error("���Ԫ�� [" + byElement + "] �ϵ�����ʧ��!");
        }
        logger.info("���Ԫ�� [" + byElement  + "]�ϵ�����");
    }

    /**
     * ���������������
     * */
    public void type(By byElement, String key) {
        try {
            findElementBy(byElement).sendKeys(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("���� [" + key + "] �� Ԫ��[" + byElement + "]ʧ��");
            Assert.fail("���� [" + key + "] �� Ԫ��[" + byElement + "]ʧ��",e);
        }
        logger.info("���룺[" + key + "] �� [" + byElement + "]");
    }

    /**
     * ģ����̲�����,����Ctrl+A,Ctrl+C�� ������⣺<br>
     * 1��WebElement element - Ҫ��������Ԫ�� <br>
     * 2��Keys key- �����ϵĹ��ܼ� ����ctrl ,alt�� <br>
     * 3��String keyword - �����ϵ���ĸ
     * */
    public void pressKeysOnKeyboard(WebElement element, Keys key, String keyword) {

        element.sendKeys(Keys.chord(key, keyword));
    }

    /**
     * �ڸ�����ʱ����ȥ����Ԫ�أ����û�ҵ���ʱ���׳��쳣
     * */
    public void waitForElementToLoad(int timeOut, final By By) {
        logger.info("��ʼ����Ԫ��[" + By + "]");
        try {
            (new WebDriverWait(driver, timeOut)).until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(By);
                    return element.isDisplayed();
                }
            });
        } catch (TimeoutException e) {
            logger.error("��ʱ!! " + timeOut + " ��֮��û�ҵ�Ԫ�� [" + By + "]");
            Assert.fail("��ʱ!! " + timeOut + " ��֮��û�ҵ�Ԫ�� [" + By + "]");

        }
        logger.info("�ҵ���Ԫ�� [" + By + "]");
    }

    /**
     * �ڸ�����ʱ���ڲ�ѯԪ�أ����������Σ���������λ����Ҳ����ͱ�����Ϳ�������ҳ���������ˣ���Ӧ�ٶȲ���
     * */
    public void FindElementUtil3TimesTry(int timeOut, final By By ) {
     int find=0;
        int notFindTimes = 0;
        boolean flag = true;
        while(flag){
            if(notFindTimes==3){
                logger.error("������3�β��Ҷ�δ���ҵ�Ԫ�أ�"+By+"�����ǲ������������վ�������⣨��Ӧ�ٶȲ�����");
                Assert.fail("������3�β��Ҷ�δ���ҵ�Ԫ�أ�"+By+"�����ǲ������������վ�������⣨��Ӧ�ٶȲ�����");
            }
        logger.info("��ʼ��"+(notFindTimes+1)+"�β���Ԫ��[" + By + "]");
        try {
            (new WebDriverWait(driver, timeOut)).until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(By);
                    return element.isDisplayed();
                }
            });
            find++;
        } catch (TimeoutException e) {
            logger.warn("��ʱ!! " + timeOut + " ��֮��û�ҵ�Ԫ�� [" + By + "],���ǵ�"+(notFindTimes+1)+"�β��ң�");
            notFindTimes++;
            if(notFindTimes<3){
            refresh();
            }
        }
        
        
        if(notFindTimes>0&find!=1){
            flag = true;
        }else{
            flag = false;
        }
        

        }
    
        logger.info("�ҵ���Ԫ�� [" + By + "]");
    }

    /**
     * �ж��ı��ǲ��Ǻ�����Ҫ����ı�һ��
     * **/
    public void isTextCorrect(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError e) {
            logger.error("������������ [" + expected + "] �����ҵ��� [" + actual + "]");
            Assert.fail("������������ [" + expected + "] �����ҵ��� [" + actual + "]");

        }
        logger.info("�ҵ�������������: [" + expected + "]");

    }

    /**
     * �жϱ༭���ǲ��ǿɱ༭
     * */
    public void isInputEdit(WebElement element) {

    }

    /**
     * �ȴ�alert����
     * */
    public Alert switchToPromptedAlertAfterWait(long waitMillisecondsForAlert) throws NoAlertPresentException {
        final int ONE_ROUND_WAIT = 200;
        NoAlertPresentException lastException = null;

        long endTime = System.currentTimeMillis() + waitMillisecondsForAlert;

        for (long i = 0; i < waitMillisecondsForAlert; i += ONE_ROUND_WAIT) {

            try {
                Alert alert = driver.switchTo().alert();
                return alert;
            } catch (NoAlertPresentException e) {
                lastException = e;
            }
                try {
                    Thread.sleep(ONE_ROUND_WAIT);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            if (System.currentTimeMillis() > endTime) {
                break;
            }
        }
        throw lastException;
    }

    /**
     * ��ͣ��ǰ������ִ�У���ͣ��ʱ��Ϊ��sleepTime
     * */
    public void pause(int sleepTime) {
        if (sleepTime <= 0) {
            return;
        }
        try {
            TimeUnit.SECONDS.sleep(sleepTime); 
            logger.info("��ͣ:"+sleepTime+"��");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * �˳�
     * */
    public void quit() {
        driver.quit();
    }

    /**
     * �л�frame - ����String���ͣ�frame���֣�
     * */
    public void inFrame(String frameId) {
        driver.switchTo().frame(frameId);
    }

    /**
     * �л�frame - ����frame�ڵ�ǰҳ���е�˳������λ
     * */
    public void inFrame(int frameNum) {
        driver.switchTo().frame(frameNum);
    }

    /**
     * �л�frame - ����ҳ��Ԫ�ض�λ
     * */
    public void switchFrame(By byElement) {
        try {
            logger.info("Start switching to frame [" + byElement + "]");
            driver.switchTo().frame(findElementBy(byElement));
        } catch (Exception e) {
            logger.info("Switch to frame [" + byElement + "] failed");
            Assert.fail("Switch to frame [" + byElement + "] failed");
        }
        logger.info("Switch to frame [" + byElement + "] successed");
    }

    /**
     * ѡ������ѡ�� -����value
     * */
    public void selectByValue(By by, String value) {
        Select s = new Select(driver.findElement(by));
        s.selectByValue(value);
    }

    /**
     * ѡ������ѡ�� -����index�Ǳ�
     * */
    public void selectByIndex(By by, int index) {
        Select s = new Select(driver.findElement(by));
        s.selectByIndex(index);
    }

    /** ���checkbox�ǲ��ǹ�ѡ */
    public boolean doesCheckboxSelected(By elementLocator) {
        if (findElementBy(elementLocator).isSelected() == true) {
            logger.info("CheckBox: " + getLocatorByElement(findElementBy(elementLocator), ">") + " ����ѡ");
            return true;
        } else
            logger.info("CheckBox: " + getLocatorByElement(findElementBy(elementLocator), ">") + " û�б���ѡ");
        return false;

    }

    /**
     * ѡ������ѡ�� -�����ı�����
     * */
    public void selectByText(By by, String text) {
        Select s = new Select(driver.findElement(by));
        s.selectByVisibleText(text);
        logger.info("��ѡ���ˣ�"+text);
    }
    
    /**
     * ��õ�ǰselectѡ���ֵ
     * */
    public String getCurrentSelectValue(By by){
        
        Select s = new Select(driver.findElement(by));
        WebElement e =  s.getFirstSelectedOption();
            return e.getText().trim();
    }
    
    /**
     * ��ȡ�����б������ѡ��
     * @param By��ByԪ�ض���
     * @return �������������б��е�ѡ���option1,option2,����
     * */
    public String getSelectOption(By by) {
        String value = null;
        Select s = new Select(driver.findElement(by));
        List<WebElement> options = s.getOptions();
        for(int i = 0 ; i< options.size() ; i++){
            value = value + "," + options.get(i).getText();        
        }        
        return value.replace("null,","");
    }

    /**
     * ִ��JavaScript ����
     * */
    public void executeJS(String js) {
        ((JavascriptExecutor) driver).executeScript(js);
        logger.info("ִ��JavaScript��䣺[" + js + "]");
    }
    
    /**
     * ���������ֵ ������� �����ĳЩinput����� û��value���ԣ���������ȡ��input�� ֵ�÷���
     * */
    public String getInputValue(String chose,String choseValue) {
        String value = null;
        switch(chose.toLowerCase()){
        case "name":
             String jsName = "return document.getElementsByName('"+choseValue+"')[0].value;"; //��JSִ�е�ֵ ���س�ȥ
             value = (String)((JavascriptExecutor) driver).executeScript(jsName);
             break;
            
        case "id":
             String jsId = "return document.getElementById('"+choseValue+"').value;"; //��JSִ�е�ֵ ���س�ȥ
             value = (String)((JavascriptExecutor) driver).executeScript(jsId);
             break;
        
            default:
                logger.error("δ�����chose:"+chose);
                Assert.fail("δ�����chose:"+chose);
        
        }
        return value;

    }

    /**
     * ִ��JavaScript �����Ͷ���
     * �÷���seleniumUtil.executeJS("arguments[0].click();", seleniumUtil.findElementBy(MyOrdersPage.MOP_TAB_ORDERCLOSE));
     * */
    public void executeJS(String js, Object... args) {
        ((JavascriptExecutor) driver).executeScript(js, args);
        logger.info("ִ��JavaScript��䣺[" + js + "]");
    }

    /**
     * get������װ
     * */
    public void get(String url) {
        driver.get(url);
        logger.info("�򿪲���ҳ��:[" + url + "]");
    }

    /**
     * close������װ
     * */
    public void close() {
        driver.close();
    }

    /**
     * ˢ�·�����װ
     * */
    public void refresh() {
        driver.navigate().refresh();
        logger.info("ҳ��ˢ�³ɹ���");
    }

    /**
     * ���˷�����װ
     * */
    public void back() {
        driver.navigate().back();
    }

    /**
     * ǰ��������װ
     * */
    public void forward() {
        driver.navigate().forward();
    }

    /**
     * ��װseleniumģ�������� - ����ƶ���ָ��Ԫ��
     * */
    public void mouseMoveToElement(By by) {
        Actions builder = new Actions(driver);
        Actions mouse = builder.moveToElement(driver.findElement(by));
        mouse.perform();
    }

    /**
     * ��װseleniumģ�������� - ����ƶ���ָ��Ԫ��
     * */
    public void mouseMoveToElement(WebElement element) {
        Actions builder = new Actions(driver);
        Actions mouse = builder.moveToElement(element);
        mouse.perform();
    }
    
    /**
     * ��װseleniumģ�������� - ����һ�
     * */
    public void mouseRightClick(By element) {
        Actions builder = new Actions(driver);
        Actions mouse = builder.contextClick(findElementBy(element));
        mouse.perform();
    }

    /**
     * ���cookies,���Զ���½�ı�Ҫ����
     * */
    public void addCookies(int sleepTime) {
        pause(sleepTime);
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie c : cookies) {
            System.out.println(c.getName() + "->" + c.getValue());
            if (c.getName().equals("logisticSessionid")) {
                Cookie cook = new Cookie(c.getName(), c.getValue());
                driver.manage().addCookie(cook);
                System.out.println(c.getName() + "->" + c.getValue());
                System.out.println("��ӳɹ�");
            } else {
                System.out.println("û���ҵ�logisticSessionid");
            }

        }

    }

    /** ���CSS value */
    public String getCSSValue(WebElement e, String key) {

        return e.getCssValue(key);
    }

    /** ʹ��testng��assetTrue���� */
    public void assertTrue(WebElement e, String content) {
        String str = e.getText();
        Assert.assertTrue(str.contains(content), "�ַ��������в����У�" + content);

    }

    /** ����frame */
    public void outFrame() {

        driver.switchTo().defaultContent();
    }

    // webdriver�п������úܶ�ĳ�ʱʱ��
    /** implicitlyWait��ʶ�����ʱ�ĳ�ʱʱ�䡣�������ʱ���������û�ҵ��Ļ��ͻ��׳�NoSuchElement�쳣 */
    public void implicitlyWait(int timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    /** setScriptTimeout���첽�ű��ĳ�ʱʱ�䡣webdriver�����첽ִ�нű�������������첽ִ�нű��ű����ؽ���ĳ�ʱʱ�� */
    public void setScriptTimeout(int timeOut) {
        driver.manage().timeouts().setScriptTimeout(timeOut, TimeUnit.SECONDS);
    }

    /**
     * pageLoadTimeout��ҳ�����ʱ�ĳ�ʱʱ�䡣��Ϊwebdriver���ҳ���������ڽ��к���Ĳ�����
     * �������ҳ���������ʱʱ����û�м�����ɣ���ôwebdriver�ͻ��׳��쳣
     */

    public void waitForPageLoading(int pageLoadTime) {
        driver.manage().timeouts().pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);

    }

    /** ����Ԫ������ȡ��Ԫ�صĶ�λֵ */
    public String getLocatorByElement(WebElement element, String expectText) {
        String text = element.toString();
        String expect = null;
        try {
            expect = text.substring(text.indexOf(expectText) + 1, text.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("failed to find the string [" + expectText + "]");

        }

        return expect;

    }
    /**
     * ��ȡ��ǰҳ���URL
     * */
    public String getPageURL(){
        return driver.getCurrentUrl();
        
    }
    /**
     * ����һ����ͬ��elements�� ѡ�� ���з��� һ�� Ȼ�������ѡ������ ������λ
     * */
    public WebElement getOneElement(By bys, By by, int index) {
        return findElementsBy(bys).get(index).findElement(by);
    }

    /**
     * �ϴ��ļ�����Ҫ��������ϴ���Ƭ�Ĵ��ڲ���
     * 
     * @param brower
     *            ʹ�õ����������
     * @param file
     *            ��Ҫ�ϴ����ļ����ļ���
     */
    public  void handleUpload(String browser, File file) {
    	
        String filePath = file.getAbsolutePath();
        System.out.println("filepath:"+filePath);
        String executeFile = "res/script/autoit/Upload.exe";
        
        
        String cmd = "\"" + executeFile + "\"" + " " + "\"" + browser + "\"" + " " + "\"" + filePath + "\"";
    
        System.out.println("cmd"+cmd);
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            
           
           // Thread.sleep(2000);
        	p.waitFor();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   


	/**
     * @Description ����windows GUI������Ҫ�������û���������ʱ��
     *              seleniumm����ֱ�Ӳ�������Ҫ����http://modifyusername:modifypassword@yoururl ���ַ���
     * 
     * */
    public void loginOnWinGUI(String username, String password, String url) {
        driver.get(username + ":" + password + "@" + url);
    }

    /** ���Ԫ���Ƿ���ʾ */
    public boolean isDisplayed(WebElement element) {
        boolean isDisplay = false;
        if (element.isDisplayed()) {
            logger.info("The element: [" + getLocatorByElement(element, ">") + "] is displayed");
            isDisplay = true;
        } else if (element.isDisplayed() == false) {
            logger.warn("The element: [" + getLocatorByElement(element, ">") + "] is not displayed");

            isDisplay = false;
        }
        return isDisplay;
    }
    
    /**���Ԫ���ǲ��Ǵ���*/
    public  boolean doesElementsExist(By byElement){
        try{
        findElementBy(byElement);
        return true;
        }catch(NoSuchElementException nee){
            
            return false;
        }
        
        
    }
    
    /** ���Ԫ���Ƿ񱻹�ѡ */
    public boolean isSelected(WebElement element) {
        boolean flag = false;
        if (element.isSelected() == true) {
            logger.info("The element: [" + getLocatorByElement(element, ">") + "] is selected");
            flag = true;
        } else if (element.isSelected() == false) {
            logger.info("The element: [" + getLocatorByElement(element, ">") + "] is not selected");
            flag = false;
        }
        return flag;
    }

    /**
     * �ж�ʵ���ı�ʱ����������ı�
     * 
     * @param actual
     *            ʵ���ı�
     * @param expect
     *            �����ı�
     */
    public void isContains(String actual, String expect) {
        try {
            Assert.assertTrue(actual.contains(expect));
        } catch (AssertionError e) {
            logger.error("The [" + actual + "] is not contains [" + expect + "]");
            Assert.fail("The [" + actual + "] is not contains [" + expect + "]");
        }
        logger.info("The [" + actual + "] is contains [" + expect + "]");
    }
    
    /**
     * �ж�ʵ���ı�,�����������ı�
     * 
     * @param actual
     *            ʵ���ı�
     * @param expect
     *            �����ı�
     */
    public void isNotContains(String actual, String expect) {
        try {
            Assert.assertFalse(actual.contains(expect));
        } catch (AssertionError e) {
            logger.error("The [" + actual + "] is  contains [" + expect + "]");
            Assert.fail("The [" + actual + "] is  contains [" + expect + "]");
        }
        logger.info("The [" + actual + "] is not contains [" + expect + "]");
    }

    /** �����Ļ�ķֱ��� - �� */
    public  double getScreenWidth() {
        return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    
    /**�����´���*/
    public void switchNewWindow(By byElement){
        //��ȡ��ǰҳ����
         current_handles = driver.getWindowHandle();
        //���ĳ�����ӻᵯ��һ���´���
        click(byElement);
        //�����������µĴ��ڴ򿪣���ȡ���д��ھ��
        Set<String> all_handles = driver.getWindowHandles();
        //ѭ���жϣ��ѵ�ǰ��������о�����Ƴ���ʣ�µľ�������Ҫ���´���
        Iterator<String> it = all_handles.iterator();
        while(it.hasNext()){
        if(current_handles == it.next()) continue;
        //�����´���,������´��ڵ�driver - newWindow
         window = driver.switchTo().window(it.next());
        }
        
    }
    
    /**�ص�ԭʼ����*/
    public void backToOriginalWindow(){
        window.close();
        driver.switchTo().window(current_handles);
        
    }
    
    /**ֹͣҳ�����*/
    public void stopLoad(){    
        pause(1);
        Robot r;
        try {
            r = new Robot();
            r.keyPress(KeyEvent.VK_ESCAPE);
            logger.info("������Esc��");
            r.keyRelease(KeyEvent.VK_ESCAPE);
            logger.info("�ɿ���Esc��");
        } catch (AWTException e) {
            e.printStackTrace();
        }

        logger.info("����ֹͣҳ�����...");
    }

    /**��ȡϵͳʱ��*/
    public int getDate(String getOption){
         Calendar a=Calendar.getInstance();
         int result=0;
        switch(getOption){
        
        case "��":
            result = a.get(Calendar.YEAR);
            break;
        case "��":
            result = a.get(Calendar.MONTH)+1;
            break;
        case "��":
            result = a.get(Calendar.DATE);
            break;
            default:
                Assert.fail("ֻ֧�������ꡢ�¡��ա��������ˣ�"+getOption);
        
        }
        
        return result;
    }
    /**�ж�alert�Ƿ����*/
    public boolean isAlertPresent(){
        try
        {
            driver.switchTo().alert();
            logger.info("alert����");
            return true;
        }   
        catch (NoAlertPresentException Ex)
        {
            logger.warn("alertû�г���");
            return false;
        }   
}

    
    
    /**CMP�ɲ���Ч����ϵͳ��¼����*/
    public void loginCMP(String username,String password){    
        FindElementUtil3TimesTry(30, By.id("account"));
        FindElementUtil3TimesTry(30, By.id("password"));
        FindElementUtil3TimesTry(30, By.id("bLogin"));    
        type(By.id("account"),username);
        type(By.id("password"),password);
        click(By.id("bLogin"));
    }
    
    /**
     * �ڶ����ͬԪ���У���λ��ָ����Ԫ��
     * @param by
     * @param index
     * @return
     */
    public WebElement getOneElement(By by, int index) {
         List<WebElement> element = driver.findElements(by);
         return element.get(index);
    }
    
    /**
     * ��ȡָ��tableĳһ���е�ֵ
     */
    public String getColumnText(By by){
        String values = null;
        List<WebElement> elements = findElementsBy(by);
        for(WebElement e: elements){
            String value = e.getText();
            if(value.length() > 0){
                values = values + "," + value;
            }            
        }
        return values.replace("null,", "");
    }
    
    /**
     * ��ȡָ��tableĳһ�е�ֵ
     * @param index���кţ��кŴ�1��ʼ��0����table�ı�ͷ��
     */
    public String getRowText(By by, int index){
        String values = null;
        List<WebElement> rows = findElementsBy(by);  //tr����
        WebElement row = rows.get(index); 
        if(row.findElements(By.tagName("td")).size()>0){
            List<WebElement> cells = row.findElements(By.tagName("td"));  //td����
            for(WebElement cell:cells){
                String value = cell.getText();
                if(value.length() > 0){
                    values = values + "," + value;
                }
                
            }
        }
        return values.replace("null,", "");
    }
    
    /**
     * ��ȡָ��table����Ԫ���ֵ
     * @param index���кţ��кŴ�1��ʼ��0����table�ı�ͷ��
     */
    public String getCellText(By by, int RowID, int ColID){
        String value = null;
        //�õ�tableԪ�ض���
        WebElement table = driver.findElement(by);
        //�õ�table���������ж��󣬲��õ���Ҫ��ѯ���ж���
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        WebElement theRow = rows.get(RowID);
        //����getCell�����õ���Ӧ���ж���Ȼ��õ�Ҫ��ѯ���ı���
        value = getCell(theRow, ColID).getText();    
        return value.replace("null,", "");
    }
    

    /**
     * 
     * @param Row: һ�еĶ���
     * @param ColID����Ӧ��
     * @return
     */
    private WebElement getCell(WebElement Row,int ColID){
        List<WebElement> cells;
        WebElement target = null;
        //��������"<th>"��"<td>"���ֱ�ǩ�����Էֿ�����
        if(Row.findElements(By.tagName("th")).size()>0){
        cells = Row.findElements(By.tagName("th"));        
        target = cells.get(ColID);
        }
        if(Row.findElements(By.tagName("td")).size()>0){
        cells = Row.findElements(By.tagName("td"));
        target = cells.get(ColID);
        }
        return target;
    }

    /**
     * �ڸ�����ʱ����ȥ����Ԫ�أ����û�ҵ���ʱ���׳��쳣
     * */
    public boolean isShown(int timeOut, final By By) {
        boolean flag = true;
        logger.info("��ʼ����Ԫ��[" + By + "]");
        try {
            (new WebDriverWait(driver, timeOut)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(By);
                    return element.isDisplayed();
                }
            });
        } catch (TimeoutException e) {
            flag = false;

        }    
        return flag;
    }
    
    
    /**ҳ�����ʱ�򻬶�ҳ�� window.scrollTo(��߾�,�ϱ߾�); */
    public void scrollPage(int x,int y){
        String js ="window.scrollTo("+x+","+y+");";
        ((JavascriptExecutor)driver).executeScript(js);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}