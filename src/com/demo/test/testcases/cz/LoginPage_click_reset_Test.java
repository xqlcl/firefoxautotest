package com.demo.test.testcases.cz; 
import org.testng.annotations.Test; 
import com.demo.test.base.BaseParpare; 
 import com.demo.test.utils.SuperAction; 
public class LoginPage_click_reset_Test extends BaseParpare{ 
@Test 
 public void reset() { 
SuperAction.parseExcel("Cz","click_reset",seleniumUtil);
 }
}