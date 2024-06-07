package android.testUtilis;

import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import simplestream.pageObjects.android.LoginPage;
import simplestream.utils.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils {
	
	AppiumDriverLocalService service;
	UiAutomator2Options options;
	public AndroidDriver driver;
	public LoginPage loginPage;
	
	@BeforeClass(alwaysRun=true)
	public void configureAppium() throws IOException {
		
	    //code to start server
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/simplestream/resources/data.properties");
		prop.load(fis);
		
		service=startAppiumServer(prop.getProperty("ipAddress"), Integer.parseInt(prop.getProperty("port")));
		service.start();
		
		//Desired capabilities
		options=new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		options.setChromedriverExecutable("//Users//shashi//Documents//cd//121//chromedriver");		
		options.setCapability("browserName",prop.getProperty("AndroidBrowser"));
		
		driver=new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		loginPage=new LoginPage(driver,prop.getProperty("AppUrl"));
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.stop();	
	}

}
