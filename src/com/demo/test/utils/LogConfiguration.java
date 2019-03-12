package com.demo.test.utils;


import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
/**
 * @author young
 * @decription ��̬���ɸ���ģ���е�ÿ����������־�������������֮���뵽result/logĿ¼�²鿴
 * */
public class LogConfiguration {
    
        public static void initLog(String fileName){
            //��ȡ��ģ������
            String founctionName = getFunctionName(fileName);
            //������־�ļ��洢·���Լ��ļ�������ʽ
            final String logFilePath  = "./result/log/"+founctionName+"/"+fileName+".log";  
            Properties prop = new Properties();
            //������־����ĸ�ʽ
            prop.setProperty("log4j.rootLogger","info, toConsole, toFile");
            prop.setProperty("log4j.appender.file.encoding","UTF-8" );
            prop.setProperty("log4j.appender.toConsole","org.apache.log4j.ConsoleAppender");
            prop.setProperty("log4j.appender.toConsole.Target","System.out");
            prop.setProperty("log4j.appender.toConsole.layout","org.apache.log4j.PatternLayout ");
            prop.setProperty("log4j.appender.toConsole.layout.ConversionPattern","[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");        
            prop.setProperty("log4j.appender.toFile", "org.apache.log4j.DailyRollingFileAppender");
            prop.setProperty("log4j.appender.toFile.file", logFilePath);
            prop.setProperty("log4j.appender.toFile.append", "false");
            prop.setProperty("log4j.appender.toFile.Threshold", "info");
            prop.setProperty("log4j.appender.toFile.layout", "org.apache.log4j.PatternLayout");
            prop.setProperty("log4j.appender.toFile.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");
            //ʹ������Ч
            PropertyConfigurator.configure(prop);

        }
        
        
        /**ȡ��ģ������*/
        public static String getFunctionName(String fileName){
            String functionName = null; 
            int firstUndelineIndex = fileName.indexOf("_"); 
            functionName = fileName.substring(0, firstUndelineIndex-4);
            return functionName;
        
    }
    

}