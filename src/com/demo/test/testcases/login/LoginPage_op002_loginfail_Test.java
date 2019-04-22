package com.demo.test.testcases.login; 
import org.testng.annotations.Test; 
import com.demo.test.base.BaseParpare; 
 import com.demo.test.utils.SuperAction; 
public class LoginPage_op002_loginfail_Test extends BaseParpare{ 
@Test 
 public void loginfail() { 
SuperAction.parseExcel("Login","op002_loginfail",seleniumUtil);
 }
}