package simplestream.pageObjects.ios;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.testng.Assert;


public class LoginPageIos    {
	IOSDriver driver;
	String appUrl;

	@iOSXCUITFindBy(id="login-form-username")
	private WebElement userNameTextBox;

	@iOSXCUITFindBy(id="login-form-password")
	private WebElement passwordTextBox;  

	@iOSXCUITFindBy(xpath="//input[@name='remember'])[1]")
	private WebElement rememberMeCheckbox;

	@iOSXCUITFindBy(xpath="//input[@type='submit'])[1]")
	private WebElement signInButton;

	@iOSXCUITFindBy(className="mb-3-mobile")
	private WebElement cookiesGotIt;
	
	@iOSXCUITFindBy(xpath="//button[@data-test-ref='account-dropdown-toggler']")
	private WebElement accountDropdown;
	
	@iOSXCUITFindBy(xpath="//div[@data-test-ref='account-dropdown-avatar-full-name']/div")
	private WebElement accountName;
	
	@iOSXCUITFindBy(xpath="//div[@type='danger']/ul")
	private List<WebElement> loginErrorMessage;



	public LoginPageIos(IOSDriver driver,String url) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		appUrl=url;

	}

	public void openUrl() {
		driver.get(appUrl); 
	}

	public void loginWithValidCredentials(String username,String password) {

		String loginPageUrl=driver.getCurrentUrl();

		//if cookies pop up appears then perform click otherwise ignore 
		boolean res=cookiesGotIt.isDisplayed();
		if(res)
			cookiesGotIt.click();

		userNameTextBox.sendKeys(username);
		passwordTextBox.sendKeys(password);
		rememberMeCheckbox.click();
		signInButton.click();

		String homePageUrl=driver.getCurrentUrl();
		
		// Validate home page and login url are not same, if successful login
		Assert.assertNotEquals(homePageUrl,loginPageUrl);
		
		// Validate on account profile, if successful login
		accountDropdown.click();
		String accName=accountName.getText();
		boolean flag;
		if(flag=username.contains(accName)) {
			Assert.assertTrue(flag, "Valid credentials login is successfull");
		}else {
			Assert.assertTrue(flag, "Valid credentials login returning error, verification failed");
		}	
		
	}

	public void loginWithInvalidCredentails(String username,String password) {

		String loginPageUrl=driver.getCurrentUrl();

		// if cookies pop up appears then perform click otherwise ignore 
		boolean res=cookiesGotIt.isDisplayed();
		if(res)
			cookiesGotIt.click();

		userNameTextBox.sendKeys(username);
		passwordTextBox.sendKeys(password);

		boolean checkEmpty=(username.isEmpty())||(password.isEmpty());
		if(checkEmpty) {
			verifyEmptyCredentials(username,password);
		}else{
			rememberMeCheckbox.click();
			signInButton.click();

			String loginPageUrlAfterAttempt=driver.getCurrentUrl();
			
			//1.Test case will fail as both before and after login attempt, URL will be same
			Assert.assertNotEquals(loginPageUrlAfterAttempt,loginPageUrl);
			
			//2.This particular attribute value with 'danger' appears only with login error, Hence if this element count is not zero
			//  then there will be login error instances.
			boolean flag=loginErrorMessage.size()>0;
			if(flag) {
				Assert.assertTrue(flag, "SignIn should fail, as user entered invalid credentials, Verfication passed");
			}else {
				Assert.assertTrue(flag, "User signed in to the App with wrong credentials, Verification failed");
			}

		}
	}

	// Validate if there are no texts populated on username/password field, then sign in button should not enable
	public void verifyEmptyCredentials(String username,String password) {
		boolean signInButtonEnabled=signInButton.isEnabled();
		if(!signInButtonEnabled) {
			Assert.assertTrue(!signInButtonEnabled, "Sign In button should not be enabled for empty username/password, Verification Failed");
		}else {
			Assert.assertFalse(signInButtonEnabled, "Sign In button should be disabled for empty username/password, Verification Passed");
		}
	}

}
