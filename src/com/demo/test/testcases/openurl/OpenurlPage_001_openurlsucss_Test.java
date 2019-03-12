package com.demo.test.testcases.openurl; 
import org.testng.annotations.Test; 
import com.demo.test.base.BaseParpare; 
 import com.demo.test.utils.SuperAction; 
public class OpenurlPage_001_openurlsucss_Test extends BaseParpare{ 
@Test 
 public void openurlsucss() { 
SuperAction.parseExcel("Openurl","001_openurlsucss",seleniumUtil);
 }
}