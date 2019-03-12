package com.demo.test.testcases.search;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demo.test.base.BaseParpare;
import com.demo.test.pageshelper.searchHelper;

public class SearchPage_001_SearchSuccessFunction_Test extends BaseParpare{
	
	

	@Test(dataProvider="testData")
	public void cxTest(Map<String,String> data) throws InterruptedException{
      System.out.println("开始查询测试");
		
		searchHelper.searchPageReload(seleniumUtil, timeOut);
		
		System.out.println("听了");
		//searchHelper.srcx(seleniumUtil, "20180614152114463","13764645501");
		searchHelper.srcx(seleniumUtil, data.get("JCBH"),data.get("SJHM"));
		
		
	}

}
