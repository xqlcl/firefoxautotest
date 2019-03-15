package com.demo.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

/**
 * 
 * @author xy-incito-wy
 * @Description �Զ����ɲ��Դ���Ĺ�����,��������ģ��Ĳ�����
 *
 */
public class TestCaseFactoryForAll {
	public static void main(String[] args) {

		final String caseFolder = "src/com/demo/test/testcases/";
		File sourceFile = null;
		String sheetName = null;
		int sheetNum = 0;
		for (int i = 0; i < getFunctionNum(); i++) { // ��һ��ѭ�� ȡ��ģ��ĸ���
			try {

				String functionName = getFunctionName(i);// ���ÿ��ѭ���� ģ����

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

				for (int j = 0; j < getSheetNum(getExcelRelativePath(i)); j++) { // �ڶ���ѭ�������ݴ����ģ���ļ�·�����
																					// ģ����sheet����
																					// Ҳ������������
					if (j == getSheetNum(getExcelRelativePath(i)) - 1) {

						break;
					}
					try {
						sheetName = getSheetName(j + 1, getExcelRelativePath(i)); // ȡ��sheetName
																					// ֻ����һ��ȫ���쳣����
						sheetNum = getSheetNum(getExcelRelativePath(i));
					} catch (BiffException e1) {
						e1.printStackTrace();
					}
					sourceFile = new File(caseFolder
							+ functionName.replaceFirst(functionName.substring(
									0, 1), functionName.substring(0, 1)
									.toLowerCase())
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
			System.out.println("ģ��[" + getFunctionName(i) + "] �������Ѿ�������ϣ����ƣ�"
					+ (sheetNum - 1) + "�����뵽" + caseFolder
					+ getFunctionName(i).toLowerCase() + "·���²��ģ�");
		}

	}

	/**
	 * ��õ�ǰ·����ģ�����
	 * 
	 * @return �õ�ģ��ĸ���
	 */
	public static int getFunctionNum() {
		String path = "res/testcase";
		File file = new File(path);
		File[] array = file.listFiles();
		return array.length;
	}

	/**
	 * ���ģ������ Ҳ����excel ����
	 * 
	 * @param ѭ��ģ�����ƵĽǱ�
	 * @return �õ���Ӧindex��ģ������
	 */
	public static String getFunctionName(int index) {
		String excelCasePath = "res/testcase";
		String functionName = "";
		// get file list where the path has
		File file = new File(excelCasePath);

		// get the folder list
		File[] array = file.listFiles();
		if (array[index].isFile()) {
			functionName = array[index].getName().substring(0,
					array[index].getName().lastIndexOf("."));
		}

		return functionName;
	}

	/**
	 * ���excel�����·��
	 * 
	 * @param ѭ��ģ�����ƵĽǱ�
	 * @return �õ���Ӧindex��ģ������
	 */
	public static String getExcelRelativePath(int index) {
		String path = "res/testcase";
		String functionName = "";
		// get file list where the path has
		File file = new File(path);
		// get the folder list
		File[] array = file.listFiles();
		functionName = array[index].getPath();
		return functionName;
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
