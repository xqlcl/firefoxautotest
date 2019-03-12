package com.demo.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.testng.Assert;

/**
 * @author young
 * @description: ��ȡExcel����<br>
 *               ˵����<br>
 *               Excel����Data�ļ�����<br>
 *               Excel������ʽ����������.xls<br>
 *               Excel��sheet������ʽ�����Է�����<br>
 *               Excel��һ��ΪMap��ֵ<br>
 */
public class ExcelDataProvider implements Iterator<Object[]> {

    private Workbook book = null;
    private Sheet sheet = null;
    private int rowNum = 0;
    private int currentRowNo = 0;
    private int columnNum = 0;
    private String[] columnnName;
    private String path = null;
    private InputStream inputStream = null;
    public static Logger logger = Logger.getLogger(ExcelDataProvider.class.getName());

    /*
     * @description 
     * 2��������<br>
     * moduleName - ģ�������
     * caseNum - �����������
     **/
    public ExcelDataProvider(String moduleName, String caseNum) {

        try {
            //�ļ�·��
            path = "data/" + moduleName + ".xls";
             inputStream = new FileInputStream(path);

            book = Workbook.getWorkbook(inputStream);
            // sheet = book.getSheet(methodname);
            sheet = book.getSheet(caseNum); // ��ȡ��һ��sheet
            rowNum = sheet.getRows(); // ��ø�sheet�� ������
            Cell[] cell = sheet.getRow(0);// ��õ�һ�е����е�Ԫ��
            columnNum = cell.length; // ��Ԫ��ĸ��� ֵ ���� ����
            columnnName = new String[cell.length];// ���� �����Ĵ�С

            for (int i = 0; i < cell.length; i++) {
                columnnName[i] = cell[i].getContents().toString(); // ��һ�е�ֵ
                                                                    // ������Ϊ����
            }
            this.currentRowNo++;

        } catch (FileNotFoundException e) {
            logger.error("û���ҵ�ָ�����ļ���" + "[" + path + "]");
            Assert.fail("û���ҵ�ָ�����ļ���" + "[" + path + "]");
        } catch (Exception e) {
            logger.error("���ܶ�ȡ�ļ��� [" + path + "]",e);
            Assert.fail("���ܶ�ȡ�ļ��� [" + path + "]");
        }
    }
    /**�Ƿ����¸�����*/

    public boolean hasNext() {

        if (this.rowNum == 0 || this.currentRowNo >= this.rowNum) {

            try {
                inputStream.close();
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            // sheet��һ������Ϊ���ж�����
            if ((sheet.getRow(currentRowNo))[0].getContents().equals(""))
                return false;
            return true;
        }
    }
    /**��������*/
    public Object[] next() {

        Cell[] c = sheet.getRow(this.currentRowNo);

        Map<String, String> data = new HashMap<String, String>();

        for (int i = 0; i < this.columnNum; i++) {

            String temp = "";

            try {
                temp = c[i].getContents().toString();
            } catch (ArrayIndexOutOfBoundsException ex) {
                temp = "";
            }

            data.put(this.columnnName[i], temp);
        }
        Object object[] = new Object[1];
        object[0] = data;
        this.currentRowNo++;
        return object;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove unsupported.");
    }
}