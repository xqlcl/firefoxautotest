package com.demo.test.testcases.openurl; 
import org.testng.annotations.Test; 
import com.demo.test.base.BaseParpare; 
 import com.demo.test.utils.SuperAction; 
public class OpenurlPage_op004_openurlfail_Test extends BaseParpare{ 
@Test 
 public void openurlfail() { 
SuperAction.parseExcel("Openurl","op004_openurlfail",seleniumUtil);
 }
}