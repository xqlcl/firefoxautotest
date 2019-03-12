package com.demo.test.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import jxl.read.biff.BiffException;

/**
 * 
 * @author xy-incito-wy
 * @Description �Զ����ɲ��Դ���Ĺ����࣬����ָ��ģ�������
 *
 */
public class TestCaseFactoryForSingle {
    public static void main(String[] args) {
        //���Դ����·���������Ĳ��Դ���Ŀ¼��һ�����������޸�
        final String caseFolder = "src/com/demo/test/testcases/";
        //Դ�ļ�
        File sourceFile = null;
        //sheet������
        String sheetName = null;
        //����ģ������
        String functionName = null;
        //sheet�ĺ���
        int sheetNum = 0;

            try {
                @SuppressWarnings("resource")
                //�ӿ���̨��������
                Scanner s = new Scanner(System.in); 
                System.out.println("������ģ�����ƣ���Ҫ���س������������֮�����ٰ��س�������"); 
                functionName = s.nextLine();// ����ģ������

                functionName = functionName.replaceFirst(
                        functionName.substring(0, 1),
                        functionName.substring(0, 1).toLowerCase());
                // ������������ڣ����½�
                File functionPackage = new File(caseFolder + "/" + functionName);
                if (functionPackage.exists()) {
                    System.out.println(functionName + "���Ѿ����ڣ��Զ�������");
                    System.out.println("��������������" + functionName + "���£����Ե�...");
                } else {
                    functionPackage.mkdir();
                    System.out.println(functionName + "���Ѵ�����");
                    System.out.println("��������������" + functionName + "���£����Ե�...");
                }

                for (int j = 0; j < getSheetNum(getExcelRelativePath(functionName)); j++) { // ���ݴ����ģ���ļ�·�����ģ����sheet���� Ҳ������������

                    if (j == getSheetNum(getExcelRelativePath(functionName)) - 1) {
                        //���ֻ��һ��sheet��ʱ��ֻ��Value������£�������ѭ���������Զ����ɴ����������Ϊû�п������ɵġ�
                        break;
                    }
                    try {
                        sheetName = getSheetName(j + 1, getExcelRelativePath(functionName)); // ȡ��sheetName�����ڵ�һ��sheet��values�����Դ�j+1��ʼ
                                                                                
                        sheetNum = getSheetNum(getExcelRelativePath(functionName));
                    } catch (BiffException e1) {
                        e1.printStackTrace();
                    }
                    sourceFile = new File(caseFolder
                            + functionName.toLowerCase()
                            + File.separator
                            + functionName.replaceFirst(functionName.substring(
                                    0, 1), functionName.substring(0, 1)
                                    .toUpperCase()) + "Page_" + sheetName
                            + "_Test.java");// ������������Դ�룬ָ�����·��
                    FileWriter writer = new FileWriter(sourceFile);

                    // ���ɲ������������ͷ�ļ�
                    writer.write("package com.demo.test.testcases."
                            + functionName
                            + "; \n"
                            + "import org.testng.annotations.Test; \n"
                            + "import com.demo.test.base.BaseParpare; \n "
                            + "import com.demo.test.utils.SuperAction; \n"
                            + "public class "
                            + functionName.replaceFirst(functionName.substring(
                                    0, 1), functionName.substring(0, 1)
                                    .toUpperCase()) + "Page_" + sheetName
                            + "_Test extends BaseParpare{ \n");

                    // @Test�����岿�֣�Ҳ���ǲ��������ķ���
                    String firstLetter = sheetName.substring(
                            sheetName.indexOf("_") + 1).substring(0, 1);
                    String others = sheetName.substring(
                            sheetName.indexOf("_") + 1).substring(1);
                    String function = firstLetter.toLowerCase() + others;
                    writer.write("@Test \n"
                            + " public void"
                            + " "
                            + function
                            + "() { \n"
                            + "SuperAction.parseExcel(\""
                            + functionName.replaceFirst(functionName.substring(
                                    0, 1), functionName.substring(0, 1)
                                    .toUpperCase()) + "\",\"" + sheetName
                            + "\",seleniumUtil);\n" + " }\n");

                    // �����β������
                    writer.write("}");
                    writer.close();
                }
            } catch (IOException e) {
                Assert.fail("IO�쳣", e);
            }
            System.out.println("ģ��[" + functionName + "] �������Ѿ�������ϣ����ƣ�"
                    + (sheetNum - 1) + "�����뵽" + caseFolder
                    + functionName.toLowerCase() + "·���²��ģ�");
    

    }


    /**
     * ���excel�����·��
     * 
     * @param ѭ��ģ�����ƵĽǱ�
     * @return �õ���Ӧindex��ģ������
     */
    public static String getExcelRelativePath(String functionName) {
        String dir = "res/testcase";
        String path = "";
        // get file list where the path has
        File file = new File(dir+File.separator+functionName+".xlsx");
        // get the folder list
        path = file.getPath();
        return path;
    }

    /**
     * ��õ�ǰexcel��sheet���� - ÿ��ģ���������
     * 
     * @param filePath
     *            �ļ�·��
     * @return ���excel��sheet����
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static int getSheetNum(String filePath)
            throws FileNotFoundException, IOException {
        int casesNum = 0;
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(
                filePath)));
        casesNum = workbook.getNumberOfSheets();

        return casesNum;
    }

    /**
     * 
     * @param sheetIndex
     *            sheet��λ��
     * @param filePath
     *            excel�ļ�·����Ե�
     * @return ����sheet������
     * @throws BiffException
     * @throws IOException
     */
    public static String getSheetName(int sheetIndex, String filePath)
            throws BiffException, IOException {
        String casesName = "";
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filePath));
        casesName = workbook.getSheetName(sheetIndex);

        return casesName;

    }

}