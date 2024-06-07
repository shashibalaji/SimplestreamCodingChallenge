package ios.testUtils;

import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import simplestream.pageObjects.ios.LoginPageIos;
import simplestream.utils.AppiumUtils;

public class IOSBaseTest extends AppiumUtils {
	
	public AppiumDriverLocalService service;
	public IOSDriver driver;
	public LoginPageIos loginPage;
	
	@BeforeClass
	public void configureAppium() throws IOException {
		
	    //code to start server
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/simplestream/resources/data.properties");
		prop.load(fis);
		
		service=startAppiumServer(prop.getProperty("ipAddress"), Integer.parseInt(prop.getProperty("port")));
		service.start();
		
		//Desired capabilities
		XCUITestOptions options=new XCUITestOptions();
		options.setDeviceName(prop.getProperty("IOSDeviceName"));
		options.setPlatformVersion(prop.getProperty("IOSPlatformVersion"));
		options.setCapability("browserName",prop.getProperty("IOSBrowser"));
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));
		
		driver=new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		loginPage=new LoginPageIos(driver,prop.getProperty("AppUrl"));
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
		
	}

}
