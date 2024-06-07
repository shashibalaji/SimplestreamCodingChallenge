# SimplestreamCodingChallenge
Appium Framework for Android and IOS

Framework Explanation:

- Framework implemented with Java Maven project.
- Pom.xml manages and builds all project dependencies or libraries for mobile test automation.
- appium java client,testng,exten reports, jackson-databind and commons-io are added.
- Android driver initilization, UiAutomator2Options desired capabilities, appium server start and stop , driver quit code wriiten in 'AndroidBaseTest' under src/test/java/android/testUtils.
- IOS driver initilization, XCUITestOptions desired capabilities, appium server start and stop code wriiten in 'IOSBaseTest' under src/test/java/ios/testUtils.
- 'AppiumUtils' has common methods for both android and ios tests such as json data converter,screen shot,extent reports code.
- 'MobileBrowserTest_Android' and 'MobileBrowserTest_IOS' under src/java/test/mobile/browser are test cases for web app login.

  Page objects:
  - locators,valid/invalid methods and assertions written in 'LoginPage' under src/main/java/simplestream/pageObjects/android for android tests.
  - locators,valid/invalid methods and assertions written in 'LoginPageIos' under src/main/java/simplestream/pageObjects/ios for ios tests.
 
  Test data:
   - 'invalidCredentials' and 'validCredentials' json files has test data(username and password).
   - TestNG dataproviders are used to supply data to test methods by converting json into 2 dimensional object array, it runs same method for each set of data.
  
  Environment data:
   - Url, desired capabilities for android and ios, browser, ip address details are stored in 'data.properties' under src/main/java/simplestream/resources.

  Reports
   - Extent report and testng listerners code placed under src/test/java/framework/testUtils.

  Test execution
   - Run 'testng.xml' file to execute mobile browser test cases.

Test scenarios:
 - Sign in should return error for invalid credetials, empty strings either in username/password field ,special characters.
 - User should able to login to app with valid credentials.

Test cases automated: 
 - Negative flow
 1.  Username and password is invalid.
 1.  Username is empty and password is not empty.
 1.  Password is empty and username is not empty.
 1.  Username is empty and password is empty.
 1.  Username and password contains only special characters.
 1.  Username with space and password with space.

 - Positive flow
 1.  Valid username and password.


 Future improvements:
 - Desired capabilities should have implemented in separate class files as this will grow as more versions of android and ios needs to be tested with multiple browsers.
 - Test log results should have been implemented at any method level like call extent test object in main test files or anywhere in the framework, this provides test results log reporting flexibility.
 - Still more login credentials scenarios can be added and automated.

 Known issues
 * locator strategy with xpath is inconsistent with framework run, it is working fine without page factory.







