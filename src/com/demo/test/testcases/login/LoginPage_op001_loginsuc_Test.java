package com.demo.test.testcases.login; 
import org.testng.annotations.Test; 
import com.demo.test.base.BaseParpare; 
 import com.demo.test.utils.SuperAction; 
public class LoginPage_op001_loginsuc_Test extends BaseParpare{ 
@Test 
 public void loginsuc() { 
SuperAction.parseExcel("Login","op001_loginsuc",seleniumUtil);
 }
}