package mobile.browser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ios.testUtils.IOSBaseTest;

public class MobileBrowserTest_IOS extends IOSBaseTest {
	
	@Test(dataProvider="getValidData")
	public void loginWithValidData(HashMap<String,String> input) {
		loginPage.openUrl();
		loginPage.loginWithValidCredentials(input.get("username"), input.get("password"));
	}
	
	@Test(dataProvider="getInvalidData")
	public void loginWithInValidData(HashMap<String,String> input) {
		loginPage.openUrl();
		loginPage.loginWithInvalidCredentails(input.get("username"), input.get("password"));
	}
	
	@DataProvider
	public Object[][] getValidData() throws IOException{
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir")+"//src//test//java//framework//testData//validCredentials.json");
		return new Object[][] {{data.get(0)}};
		
	}
	
	@DataProvider
	public Object[][] getInvalidData() throws IOException{
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir")+"//src//test//java//framework//testData//invalidCredentials.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)}};
		
	}
}
