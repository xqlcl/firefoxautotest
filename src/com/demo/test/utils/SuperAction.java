package com.demo.test.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
/**
 * 
 * @Description ��Selenium�����ı�ɹؼ��ֲ���
 *
 */
public class SuperAction {
    public static Logger logger = Logger.getLogger(SuperAction.class.getName());
    //static  String pageFilePath = "res/page/PageElements.xlsx";
    static String pageFileDir = "res/page/";
    public static Alert a = null;
    /**
     * 
     * @param locateWay ��λ��ʽ
     * @param locateValue ��λ�ķ�ʽ�ľ���ֵ
     * @return ��λ�ķ�ʽ��By��
     */
    public static By getLocateWay(String locateWay,String locateValue){
         By elementLocator=null;
             if(locateWay.equalsIgnoreCase("xpath")){
                 elementLocator=By.xpath(locateValue);
                 
             }
             else if(locateWay.equalsIgnoreCase("className")){
                 elementLocator=By.className(locateValue);
             }
             else if(locateWay.equalsIgnoreCase("id")){
                 elementLocator=By.id(locateValue);
             }
             else    if(locateWay.equalsIgnoreCase("linktext")){
                 elementLocator=By.linkText(locateValue);
             }
             else    if(locateWay.equalsIgnoreCase("name")){
                 elementLocator=By.name(locateValue);
             }
             else    if(locateWay.equalsIgnoreCase("css")){
            	
                 elementLocator=By.cssSelector(locateValue);
             }
             else    if(locateWay.equalsIgnoreCase("tagname")){
                 elementLocator=By.tagName(locateValue);
             }
             else{
                 Assert.fail("��ѡ��Ķ�λ��ʽ��["+locateWay+"] ����֧��!");
             }
             return elementLocator;
         }
    

    /**
     * 
     * @param sheet - �����������е�sheet
     * @param rowIndex - �����������е���index
     * @param locateColumnIndex - �����������еĶ�λ�е�index
     * @return ��page���� ���ض�λ��ʽ�Ͷ�λֵ
     * @Description ����testcase�е�Ԫ�ض�λ�У�ȥȡ��pageҳ�е� ��λ��ʽ�Ͷ�λֵ
     */
    public static String[] getPageElementLocator(Sheet sheet,int rowIndex,int locateColumnIndex,String pageName){

            XSSFWorkbook pageBook = null;
            //��λ��ʽ
            String elementLocatorWay = null;
            //��λֵ
            String elementLocatorValue = null;
            //sheet��
            Sheet pageSheet = null;
            //page excel·��
            String pageFilePath = pageFileDir+pageName+".xlsx";
            //��ȡ��λ�е�ֵ
            String locator = sheet.getRow(rowIndex).getCell(locateColumnIndex).getStringCellValue();
            //��.�ָԪ�ض�λֵ
            String locatorSplit[] = locator.split("\\.");
        try {
            pageBook = new XSSFWorkbook(new FileInputStream(new File(pageFilePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 

          pageSheet =  pageBook.getSheetAt(0); //ȡ�õ�һ��sheet
         int pageRowNum =  pageSheet.getPhysicalNumberOfRows();//������sheet��ʵ����Ч����
         for (int j = 0; j < pageRowNum; j++) {
             //�����ȡ���ı�����ָ���ı�����ͬ���ʹ洢��ǰ�еĶ�λֵ�Ͷ�λ��ʽ
            if(pageSheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(locatorSplit[1])){
                 elementLocatorWay = pageSheet.getRow(j).getCell(1).getStringCellValue();
                 elementLocatorValue = pageSheet.getRow(j).getCell(2).getStringCellValue();
                break;
            }
        }
        return new String[]{elementLocatorWay,elementLocatorValue};
    
    }
    /**
     * @param founction
     *            excel�ļ�������
     * @param caseName
     *            excel��sheet������
     * @param seleniumUtil
     *            ����seleniumUtil
     * @throws InterruptedException 
     * @Description ��ȡexcel��ÿ��sheet�Ĳ������裬�������ɲ�������
     * */
    public static  void parseExcel(String founction, String caseName, SeleniumUtil seleniumUtil)  {
        FileInputStream filePath = null;
        XSSFWorkbook workbook = null;
        String locateSplit[]  = null;//ҳ��sheet�еĶ�λ��ʽ�Ͷ�λֵ���
        String locator = null;//����ҳ��Ķ�λ��
        String file = "res/testcase/" + founction + ".xlsx";
        try {
            filePath = new FileInputStream(file);// ��ȡ����ģ��
        } catch (FileNotFoundException e) {
            logger.error("�ļ���" + file + "������");
            Assert.fail("�ļ���" + file + "������");
        }
        try {
            workbook = new XSSFWorkbook(filePath);
        } catch (IOException e) {
            logger.error("IO�쳣");
            Assert.fail("IO�쳣");
        }
        /**ȡ��ָ����case����*/
        Sheet sheet = workbook.getSheet(caseName);
        /**��õ�ʵ������*/
        int rows = sheet.getPhysicalNumberOfRows(); 
        /** excel�еĲ�������*/
        String testData = null;
        //��ȡ���еĵ�Ԫ����
        int cellsNumInOneRow = sheet.getRow(0).getPhysicalNumberOfCells();
        //����һ������洢��ֵ�ĽǱ�
        String column[] = new String[cellsNumInOneRow];
        //����һ��������
        Iterator<Cell> cell = sheet.getRow(0).iterator();
        int ii =0;
        while(cell.hasNext()){
            column[ii]= String.valueOf(cell.next()); 
            ii++;
        }
        //���嶯���еĽǱ�
        int actionColumnIndex =0;
        //����Ԫ�ض�λ�еĽǱ�
        int locateColumnIndex = 0;
        //������������еĽǱ�
        int testDataColumnIndex = 0;
        //��̬��ȡ�⼸���ؼ�������λ��
         for (int i = 0; i < column.length; i++) {
             if(column[i].equals("����")){
                 actionColumnIndex = i;
                
             }
             if(column[i].equals("Ԫ�ض�λ")){
                 locateColumnIndex = i;
                 
             }
             if(column[i].equals("��������")){
                 testDataColumnIndex = i;
                
             }
            
        }

            // ѭ��ÿ�еĲ���������switch���ж�ÿ�еĲ�����ʲô��Ȼ��ת���ɾ���Ĵ��룬�ӵڶ��п�ʼѭ������Ϊ��һ�����е�˵�����ݡ�    
        for (int i = 1; i < rows; i++) {
            logger.info("���ڽ���excel:["+founction+".xlsx]�е�sheet(����)��["+caseName+"]�ĵ�"+i+"�в���...");
           
            String action = sheet.getRow(i).getCell(actionColumnIndex).getStringCellValue();
            System.out.println(action);
            Row row = sheet.getRow(i);
            
            if (row != null) {
                switch (action) {
                case "������":
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    seleniumUtil.get(testData);
                    break;
                    
                case "��������":
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    seleniumUtil.get(testData);
                    break;
                    
                case "����":
                	
                    //������Cell�����ͣ�Ȼ��Ϳ��԰Ѵ�������ΪString���Ͷ�������
                    sheet.getRow(i).getCell(testDataColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
                    
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue(); //��������
                   
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����е�Ԫ�ض�λ
                    
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]); //�ҵ���λ��ʽ����λֵ
                   
                    
                    seleniumUtil.type(getLocateWay(locateSplit[0], locateSplit[1]), testData);
                    break;
                    
                case "���":
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.click(getLocateWay(locateSplit[0], locateSplit[1]));
                    System.out.println(locateSplit[0]);
                    System.out.println(locateSplit[1]);
                    break;
                    
                case "��ͣ":
                    //������Cell�����ͣ�Ȼ��Ϳ��԰Ѵ�������ΪString���Ͷ�������
                    sheet.getRow(i).getCell(testDataColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    seleniumUtil.pause(Integer.parseInt(testData));
                    break;
                    
                case "�ȴ�Ԫ��":
                    //������Cell�����ͣ�Ȼ��Ϳ��԰Ѵ�������ΪString���Ͷ�������
                    sheet.getRow(i).getCell(testDataColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.waitForElementToLoad(Integer.parseInt(testData), getLocateWay(locateSplit[0], locateSplit[1]));
                   
                    break;
                    
                case "����Ԫ��(����3��)":
                    //������Cell�����ͣ�Ȼ��Ϳ��԰Ѵ�������ΪString���Ͷ�������
                    sheet.getRow(i).getCell(testDataColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.FindElementUtil3TimesTry(Integer.parseInt(testData), getLocateWay(locateSplit[0], locateSplit[1]));
                    break;
                    
                case "���":
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.clear(getLocateWay(locateSplit[0], locateSplit[1]));
                    break;
                    
                case "����iFrame":
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.switchFrame(getLocateWay(locateSplit[0], locateSplit[1]));
                    break;
                    
                case "����iFrame":
                    seleniumUtil.outFrame();
                    break;
                    
                case "ѡ�������б� - Text":
                    //������Cell�����ͣ�Ȼ��Ϳ��԰Ѵ�������ΪString���Ͷ�������
                    sheet.getRow(i).getCell(testDataColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                   
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.selectByText(getLocateWay(locateSplit[0], locateSplit[1]), testData);
                    break;
                    
                case "ѡ�������б� - Index":
                    //������Cell�����ͣ�Ȼ��Ϳ��԰Ѵ�������ΪString���Ͷ�������
                    sheet.getRow(i).getCell(testDataColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.selectByIndex(getLocateWay(locateSplit[0], locateSplit[1]), Integer.parseInt(testData));
                    break;
                    
                case "ѡ�������б� - Value":
                    //������Cell�����ͣ�Ȼ��Ϳ��԰Ѵ�������ΪString���Ͷ�������
                    sheet.getRow(i).getCell(testDataColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.selectByValue(getLocateWay(locateSplit[0], locateSplit[1]),testData );
                    break;                        
                    
                case "����ı� - ����":
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    String[] Datas = testData.split(",");
                    System.out.println(Datas[0]);
                    System.out.println(Datas[1]);
                    seleniumUtil.isTextCorrect(seleniumUtil.getAttributeText(getLocateWay(locateSplit[0], locateSplit[1]), Datas[0]),Datas[1]);
                    break;
                    
                case "�����ҳ����":
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    seleniumUtil.getTitle();
                    System.out.println(seleniumUtil.getTitle());
                    break;
                    
            
                case "ҳ���URL�Ƿ���ȷ":
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    seleniumUtil.isTextCorrect(seleniumUtil.getPageURL(), testData);
                    break;
                    
                case "����ı�":
                	System.out.println("������");
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    System.out.println(testData);
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    System.out.println(locator);
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    System.out.println(locateSplit[0]);
                    System.out.println(locateSplit[1]);
                    seleniumUtil.isTextCorrect(seleniumUtil.getText(getLocateWay(locateSplit[0], locateSplit[1])), testData);
                 
                    break;
                    
                case "����Tab":
                    //��Ҫ�Ľ����ڶ������ڵ�driver����
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.switchNewWindow(getLocateWay(locateSplit[0], locateSplit[1]));
                    break;
                    
                case "����Tab":
                    seleniumUtil.backToOriginalWindow();
                    break;
                    
                case "����alert����":
                    //������Cell�����ͣ�Ȼ��Ϳ��԰Ѵ�������ΪString���Ͷ�������
                    sheet.getRow(i).getCell(testDataColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    a = seleniumUtil.switchToPromptedAlertAfterWait(Long.parseLong(testData));
                    a.accept();
                    break;
                    
                case "ȡ��alert����":
                    //������Cell�����ͣ�Ȼ��Ϳ��԰Ѵ�������ΪString���Ͷ�������
                    sheet.getRow(i).getCell(testDataColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    a = seleniumUtil.switchToPromptedAlertAfterWait(Long.parseLong(testData));
                    a.dismiss();
                    break;    
                    
                case "ִ��JS���":
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    seleniumUtil.executeJS("arguments[0].click();", seleniumUtil.findElementBy(getLocateWay(locateSplit[0], locateSplit[1])));
                    break;    
                    
                case "ˢ��ҳ��":
                    seleniumUtil.refresh();
                    break;    
                    
                case "ǰ��ҳ��":
                    seleniumUtil.back();
                    break;
                    
                case "����ҳ��":
                    seleniumUtil.forward();
                    break;
                    
                case "�ϴ��ļ�":
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    String uploadValues[] = testData.split(",");
                    System.out.println("�������"+uploadValues[0]);
                    System.err.println("�ļ�·��1:"+uploadValues[1]);
//                    System.out.println("�ļ�·��2:"+uploadValues[2]);
//                    System.out.println("�ļ�·��3:"+uploadValues[3]);
//                    System.out.println("����:"+uploadValues.length);
//                    try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//                    int a=1;
//                   do{
//					 seleniumUtil.handleUpload(uploadValues[0], new File(uploadValues[a]));
//					 ++a;
//                   }
//                   while(a<uploadValues.length);
//                    try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
                    seleniumUtil.handleUpload(uploadValues[0], new File(uploadValues[1]));
                   
                    break;
                    
                case "Ԫ�ر�����":
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    if(seleniumUtil.isEnabled(getLocateWay(locateSplit[0], locateSplit[1]))){
                        logger.info(getLocateWay(locateSplit[0], locateSplit[1])+"Ԫ�ر�����");
                    }else{
                        Assert.fail(getLocateWay(locateSplit[0], locateSplit[1])+"��û�б�����");
                    }
                    break;
                    
                case "Ԫ��δ����":
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    if(seleniumUtil.isEnabled(getLocateWay(locateSplit[0], locateSplit[1]))){
                        Assert.fail(getLocateWay(locateSplit[0], locateSplit[1])+"Ԫ�ر�����");
                    }else{
                        logger.info(getLocateWay(locateSplit[0], locateSplit[1])+"��û�б�����");
                    }
                    break;
                    
                case "��֤��ҳ�˵����ı�":
                    locator = sheet.getRow(i).getCell(locateColumnIndex).getStringCellValue();//��ȡ�����еĶ�λ
                    //locator.split("\\.")[0]�����ҳ�����ƣ�����HomePage.�ҵĵ��ݣ���ȡ��HomePage
                    locateSplit = getPageElementLocator(sheet, i, locateColumnIndex,locator.split("\\.")[0]);
                    testData = sheet.getRow(i).getCell(testDataColumnIndex).getStringCellValue();
                    String menus[] = testData.split("��");
                    for (int i1 = 0; i1 < menus.length; i1++) {
                        seleniumUtil.isTextCorrect(seleniumUtil.findElementsBy(getLocateWay(locateSplit[0], locateSplit[1])).get(i1).getText().trim().toLowerCase(), menus[i1].toLowerCase());
                    }
                    
                    break;
                    
                    
                    default:
                        logger.error("������Ĳ�����["+action+"]����֧�֣����������");
                        Assert.fail("������Ĳ�����["+action+"]����֧�֣����������");
                    
                }
            }
        }
    }

}