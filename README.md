# Playwright_framework
====================================================

[Overview](#overview)

[Page Objects](#page-objects)


- [Writing page Objects](#how-to-write-page-objects)


[Testdata Maintenance](#maintaining-testdata)

- [Sample test data in Excel](#sample-test-data-creation-in-excel-sheet)

[How to write Reporting](#how-to-write-reporting)

- [How to write the Pass and Fail statements in extent reports](#how-to-write-the-pass-and-fail-statements-in-extent-reports)

- [Allure Reporting](#allure-report)

- [Allure Report Annotations](#allure-report-annotations)

- [How to write Pass and Fail statements in allure report](#how-to-write-pass-and-fail-statements-in-allure-report)

- [Report generation in Allure](#report-generation-in-allure)

- [Report Generation Methods in Allure](#report-generation-methods-in-allure)

[How to write scripts](#how-to-write-scripts)

[Library Functions](#library-functions)

[Tracing in Playwright](#tracing)

[Util Functions](#util-functions)

[Tools Installation by Script](#tools-installation-by-script)


# How to get start with this framework

[Installation & Setup:](#installation--setup)

-	[Cloning the Repository](#cloning-the-repository)

-	[Tools Installation](#tools-installation)
-	[Importing the cloned repository](#importing-the-cloned-repository)

[Project folders Explanation](#project-folders-explanation)

[Writing Page Object pages](#writing-page-object-pages)

[Writing Scripts](#writing-scripts)

[Explaining the Sample class](#explaining-the-above-class)


[How to run as TestNG](#how-to-run-as-testng)

[How to run failed Test scripts](#how-to-run-failed-test-scripts)

# Overview:

 Automation Framework involves designing structured system of tools, guidelines, best practices, reusable system and protocols to streamline the creation, execution, and maintenance of automated test scripts. It defines how tests are organized, how data is managed, how failures are handled, and how results are reported, ensuring consistency and efficiency across test suites

**Technical Prerequisite:**

Automation engineer need to have basic knowledge on Java, Xpath. 
Please note that, This pre requisite is specifically to this framework


**DataDrivenFramework:**

Data-Driven Framework is a design pattern where test data is separated from test scripts and stored externally like Excel,Json, CSV,database. Using this pattern, user can execute same logic with different sets of test data, improving reusability and maintainability.

**PageObjectModel:** 

The Page Object Model (POM) is a design pattern in test automation that enhances test maintenance and reduces code duplication by separating the test logic from the page-specific code (like locators and actions).

Each webpage in the application has a corresponding class in the repository that holds elements of that webpage. It is maintenance of objects in a class and naming them as corresponding Pages

**Language :** 

In our Playwright Project we are using Java as scripting language.

**Config.properties** - Global configurations that can be used across the project are placed in this file like 
URL,Testdata file, browser, Headless mode etc.

```
 url = https://mpwpayment.duc.com.kw/DhamanFrontEnd/
# url = https://mpwpayment.duc.com.kw/qol/
adminportal = https://dspmobileservice.diyarme.com:8165/AdminPortal
===========================================================================
testdatafile = ck.xlsx
waitSeconds_BeforeException = 30
PageLoadTimeout = 55
============================================================================
 browser = chrome
# browser = firefox
# browser = edge
============================================================================
```

**BaseTest**
- This class is responsible for reading the properties file, assigning the values to defined class variables

- page object initializes in this class and traverse through framework classes and then scripts.

- Each and every script class should extend BaseTest

**Packages:** 

We are maintaining different packages for pages and scripts in **src/test/java**

For ex:
``` 
- com.Diyar.Playwright.pages
- com.Diyar.Playwright.Scripts
```

Please refer the **src/main/java** for sample scripts and pages.



# Page Objects

## How to write page Objects
Page Object is a Design Pattern which has become popular in test automation for enhancing test maintenance and reducing code duplication. A page object is an object-oriented classes

- Each webpage in the application should have a page object class.
- create a class with reasonable name, For example DSP_HomePage.java, DSP_LoginPage.java
-  Xpath is the default option and not required to specify. so if the element is not Xpath, it should be declared like type.ID where Type is the Enum.
- All elements should be declared static, Sample below
```
public static Elements UserName = new Elements("Username");
public static Elements Password = new Elements("Password");
public static Elements LoginButton = new  Elements("//button[@id = \"btnLogin\"]");
public static Elements PageinArabic = new Elements("//span[@id='langChange']/i/span[contains(text(), 'English')]");
public static Elements Heading = new Elements("//h3[contains(text(), 'DSP Document Signer')]");
```

# How to write scripts

- Naming convention of script class : **modulename_TS_01_Functionality**
- Each method in the script class is single script that represents a manual testcase
- Common functionalities of script methods or script classes can be created as reusable methods and placed in class file as **Modulename_Library**
- Every script should extend the library class **Modulename_Library**
- Library class should extend **BaseTest.java**
- Variables declaration as sEnglishTitle, where s is the String.
- Class should start with Capital Letter 
- Methods, variables should start with small Letter
Reusable methods of Application can be created with naming **Modulename_Library** and make this as superclass for the script


#  Maintaining Testdata
------------------------------------------------------
## Test Data File Requirements

- Test data must be maintained in **Excel sheets**.
- The Excel file containing the test data should be placed in the **`Testdata`** folder within the project directory.
- The file name must be specified in the `config.properties` file using the key **`testdatafile`**.

Example below:
```
    testdatafile = DSP_Testdata.xlsx 
```
# Test Data Management Guidelines

To ensure consistency and reliability in our test automation framework, please follow the guidelines below when maintaining test data using Excel.

## 1. Single Workbook Usage

- All test data must be maintained within a **single Excel workbook**.
- You may use **multiple sheets** within the workbook to organize data by module, feature, or test scenario as needed.

## 2. Data Format Requirements

- The framework interprets **all cell values as strings**.
- To enforce this behavior, **prefix each cell value with an apostrophe (`'`)**.
  - Example: `'123` will be treated as a string, not a numeric value.

## 3. Sheet Structure and Integrity

- Ensure that **no empty rows** exist between populated rows in any sheet.
- The framework reads data **sequentially** and stops processing upon encountering an empty row.
- The **first column** in each sheet is critical for identifying and indexing test data.
  - Make sure it is consistently populated and free of gaps.

## 4. Best Practices

- Use clear and consistent naming conventions for sheets and columns.
- Avoid merging cells or using formulas that may alter the raw string value.
- Periodically validate the workbook to ensure compliance with these standards.


## sample test data creation in excel sheet:

| TestScriptName                        | Username | Password   |
|---------------------------------------|----------|------------|
| dsp_TS01_Login_with_Valid_Credentials | DspUser1 | Gl0bl3@d$P |
| dsp_TS02_Login_with_Invalid_UserName  | Invalid  | Gl0bl3@d$P |
| dsp_TS03_Login_with_Invalid_Password  | DspUser1 | Invalid    |

dsp_TS01_Login_with_Valid_Credentials - this is the test script name that should be given in the first column
Username - this is the field used to get the value. Username should be given in the first row


**Test data retrieval process explained**


The `getdata` method is used to retrieve test data from the configured Excel test data file.


1. The getdata method accepts a single parameter, which represents the column name in the Excel sheet.
2. It dynamically identifies the calling method name and uses it to locate the corresponding row in the test data file.
3. Once the row is identified, it retrieves the value from the specified column and returns the matching content as a string.

```
System.out.println("User value: " + getdata("Username"));
```

In this example, the method will:

1. Determine the name of the calling method.
2. Locate the row in the Excel sheet that matches the method name.
3. Fetch the value under the column "Username" from that row.
4. Return the value as a string and print it to the console.





# Reporting
## How to write Reporting

## Report Generation in the Automation Framework

Our automation framework includes built-in support for **Extent Report** generation to track and visualize test execution results.

### Report Naming Convention

- The name of each report is dynamically generated using the **class name** appended with the **execution date and time**.
- Example: `DiyarSaas_25-08-2025_02.03.51`

### Script-Level Reporting

- Each method within a test class is treated as an individual **test script**.
- The **method name** is used as the **script name** in both the test data sheet and the generated report.
- This ensures consistency and traceability between the test logic, test data, and the report output.

### Engineer's Responsibility

As a test engineer, your primary responsibility is to insert appropriate **reporting statements** (e.g., pass/fail logs) within your test methods wherever necessary.

For implementation guidance, refer to the section:  


## How to write the Pass and Fail statements in extent reports

we have customized the reporting that can be written in the following ways:

```
Reporting.pass("Pass Description");
Reporting.pass("Pass Description", true);
Reporting.fail("Fail Description");
Reporting.fail("Fail Description", true);
Reporting.Info("Information");
Reporting.Info("Information", true); 
```

![ReportScreenshot](Installs/docs/ReportScreenshot.png)

## Allure Report

## Allure Reporting Integration

In addition to Extent Reports, our automation framework now supports **Allure Reporting** for enhanced test result visualization and traceability.

### Allure Overview

- **Allure** is a flexible, lightweight, and multi-language test reporting tool that provides a clear and interactive representation of test execution.
- It is particularly effective in Java-based automation frameworks, where **annotations** play a key role in organizing and enriching test reports.
- These annotations help improve traceability, making it easier to analyze test coverage, failures, and execution flow.

### Coexistence with Extent Reports

- While Allure has been introduced, **Extent Reports continue to function** as part of the framework.
- This dual-reporting setup allows teams to leverage the strengths of both tools based on their reporting needs and preferences.

## Allure Report Annotations
==================================

Allure provides several annotations to enrich the test reports.


1. @Epic
---------
Used to group test cases under a high-level business requirement or module.

Example:
@Epic("User Management")

2. @Feature
------------
Defines a feature of the application under test.

Example:
@Feature("Login Feature")

3. @Story
----------
Describes a user story or a specific functionality.

Example:
@Story("User logs in with valid credentials")

4. @Severity
-------------
Specifies the severity level of the test case. Severity levels include:
- SeverityLevel.BLOCKER
- SeverityLevel.CRITICAL
- SeverityLevel.NORMAL
- SeverityLevel.MINOR
- SeverityLevel.TRIVIAL

Example:
@Severity(SeverityLevel.CRITICAL)

5. @Description
----------------
Provides a detailed description of the test case.

Example:
@Description("Verify that a user can log in with valid credentials.")

6. @Step
---------
Used to annotate methods that represent steps in the test execution. These steps will be shown in the report.

Example:
@Step("Open login page")
public void openLoginPage() {
    // implementation
}


## How to write Pass and Fail statements in allure report:

```
Allure.step("testing teh Allure step method");
Allure.step("test for failure", Status.FAILED);
Allure.step("test for failure", Status.PASSED);
lib.screenshotToAllure(String stepName, String screenshotName)
```

Process of allure reporting is different. It needs a local server to see the report.
Json files are generated in allure-results folder which are source to generate the report

## Report generation in Allure

Unlike Extent Reports, which often generate the report automatically during test execution, Allure Reports require manual interation to generate report via the command line after the test run is complete


## Report Generation Methods in Allure
There are two main ways to generate and view Allure reports:

1. allure serve allure-results
Description: This command generates the report and serves it via a temporary local web server.
Behavior:
Opens the report automatically in your default browser.
Useful for quick local viewing.
The server is temporary and shuts down when you close the terminal or stop the process.
```
allure serve allure-results
```

2. allure generate --single-file allure-results
Description: This command generates a static HTML report that can be shared or hosted.
Behavior:
Creates a single HTML file (index.html) in the output directory.
Ideal for archiving or sharing with team members.
```
allure generate --single-file allure-results
```



allure-results is the folder where Allure stores raw test results (usually .json files).
You must run these commands after your test execution is complete and results are available.

## Library Functions

Library functions are the methods built in the framework. These methods can be accessed with object *lib*.

*Following are the list of methods:*

- *click()* - This method clicks on the element

- *maximizethewindow()* - This method will maximize the browser window handled by driver

- *screenshotToAllure(String stepName, String screenshotName)* - This method will attach the screenshot to the allure report with the names mentioned in parameters

## Tracing

Playwright Tracing records detailed test execution data — including action logs, screenshots, DOM snapshots, network traffic, console logs, and more. It generates a .zip trace file you can explore via Playwright’s Trace Viewer. This is incredibly helpful for debugging failures

In our framework, zip file is created in a folder Tracing
To view the zip file, Open the [URL](https://trace.playwright.dev/) and upload the zip file from Tracing folder.
Please note that, we are overwriting the zip file for every execution.
## Util Functions:

*sleepforseconds(int n)* - This method is used to sleep for number of seconds mentioned in parameter

*pressEscape()* - This method is used to click Escape button

*numberoftabs(int tabscount)* - This method used to click tab button number of times mentioned in parameter

*pressKeys(int KeyEvent)* - This method to press the keyboard keys.
For ex pressKeys(keyEvent.VK_1) , keyEvent.VK_A

*hitenter* - This method will click the Enter button 

*runAutoit(String autoitexepath)* - This method is used to execute the Autoit script

*runAutoit(String autoitexepath, String param1)* - This method is used to execute the Autoit script.
- @param autoitexepath - Path of compiled Autoitscript file with extension .exe
- @param param1 - This is the value of parameter that was declared in the Autoit script

*getCurrentDatenTime(String format)* - This method will return the current date and time in the format specified in parameter.

*getTomorrowDatenTime(String format)* - This method will return the tomorrow date and time in the format specified in parameter

*getYesterdayDatenTime(String format)* - This method will return the yesterday date and time in the format specified in parameter

*getformat()* - This method just returns the format predefined in this class

*createFolder(String sPath)* - this method look for the folder and creates if not found in the specified path

*renameFolder(String CurrentName, String NewName)* - This method will rename the file/folder by providing both old and new name parameters. Please note that, If you want to rename the file, it should have extensions as well

*deleteFolder(String Path)* - This method will delete the file/folder specified as parameter. Here we have to pass the complete path of the file or folder.

*randonNumber_int3digits()* - This method will generate a random number which is 3 digit number and returns as int

*randonNumber_int5digits()* - This method will generate a random number which is 5 digit number and returns as int

# Tools Installation by Script:

- Download the GitHub repository provided to your project
- Go to **Installs** folder
- Double click the Batch file **Enable_To_Run.bat**
- Run the file **Run_Installations** in Administrator Powershell and provide your options for the softwares:


        
        Do you want to install Eclipse ? (Y/N)

        Do you want to install OpenJDk	? (Y/N)

        Do you want to install Git	? (Y/N)


**Manual Installations:**

- *Eclipse*  : Eclipse can be downloaded from the [link](https://www.eclipse.org/downloads/packages/release/2018-09/r/eclipse-ide-java-ee-developers)
- *Java*   : Copy the folder Installs to the local and run the Install_Openjdk.ps1 in Elevated shell.
- *TestNG* : This can be handled with pom.xml
- *Extent* : This can be handled with pom.xml
- *Git*	   : Copy the folder from docs/Scripts to the local folder and run the Install_Git.ps1 in Elevated shell.




## Installation & Setup:
### Cloning the Repository:
Clone the repository from the Azure devops to a local folder.
Note: I will be setting up the project in Azure Devops and sharing the project link 

### Tools Installation:

Note: While installing with the scripts, Powershell should be allowed to run scripts.

- *Eclipse*  : Eclipse can be downloaded from the [link](https://www.eclipse.org/downloads/packages/release/2018-09/r/eclipse-ide-java-ee-developers)
- *Java*   : Copy the folder *Installs* to the local and run the Install_Openjdk.ps1 in Elevated shell.
- *TestNG* : Install steps are [here](/Installs/docs/Install_TestNG)
- *Git*	   : Copy the folder *Installs* to the local and run the *Install_Git.ps1* in Elevated shell.


### **Importing the cloned repository**
1. Open the Eclipse
2. *File* --> *Import*
3. In the Import dialogue, select *Maven* --> *Existing Maven Projects*  and click *Next*
4. Browse to the repository
5. Click *Finish*

Notice the project created in Eclipse. Now, we are ready with Java, TestNG and Eclipse with maven project setup.

## **Project folders Explanation:** 

- *src/test/java*    -   It is the place to write the Automation scripts
-   *drivers*   -   Place to store the drivers of browsers
-   *Reports*   -   Reports are generated in this folder with the name of the class appending with data and time to avoid the override of report.
-   *Testdata*  -   Testdata is maintained in the Excel which are stored in this folder. Here we store the excel workbook and any other files related to test data.

- *allure-results* - This folder is automatically generated during test execution.
	- It contains **raw test execution data** in the form of JSON, XML, and other files.
	- These files include:
  		- Test case results
  		- Attachments (e.g., screenshots, logs)
  		- Environment information
  		- Executor details
	- The data in this folder serves as the **input** for generating the final Allure report.

> ⚠️ Do not manually modify or delete files in this folder unless necessary. It is regenerated with each test run. so, you can delete the complete folder before execution
 

- *allure-report* - This folder is created when you run the Allure report generation command (e.g., `allure generate`).
- It contains the **final, human-readable HTML report** built from the data in `allure-results`.
- The report includes:
  - Interactive dashboards
  - Test case summaries
  - Execution timelines

> ✅ You can open the `index.html` file in this folder to view the report in a browser.




-   *Config.properties* -   It is a properties file that stores global values:
    -   *Browser* -   Chrome, IE, FF
    -   *url* -   URL can be written in config file incase of only one url through out the project.
    If we need to have different URL than the one mentioned in config file, can have option to override the URL in the script class
    -   *testdatafile*    -   name of testdata file like DSP_Testdata.xlsx
	- 	*Headless* - This is a boolean that executes in headless mode. Default value is false, so execution happens in UI unless mentioned.
    

-   Scripting: 

While scripting, we have to use two packages, Package1 ends with *Pages* is used for maintaining Page Object Pages and Package2 ends with *Scripts* is used for writing functional scripts
Writing the first Page Object page 

## Writing Page Object pages

Create a new class in the package with the proper name like HomePage, LoginPage, RegistrationPage etc and write the all the elements in the page. 

Please see the example class below:

```
public class DSLoginPage {

	public static Elements Tenant_Switch_Btn = new Elements("//a[@id='AbpTenantSwitchLink']");
	public static Elements Switch_Tenant_Name = new Elements("//input[@id='Input_Name']");
	public static Elements Switch_Tenant_Cancel = new Elements("//button[contains(text(),'Cancel')]");
	public static Elements Switch_Tenant_Save = new Elements("//button/span[contains(text(),'Save')]");
	public static Elements Switch_Tenant_Ok = new Elements("//button[@class='swal2-confirm btn btn-primary']");
	public static Elements Switch_Tenant_DoesnotExist = new Elements("//div[@id='swal2-html-container']");
	
	public static Elements Username = new Elements("//input[@id='LoginInput_UserNameOrEmailAddress']");
	public static Elements Password = new Elements("//input[@id='password-input']");
	public static Elements Login_Btn = new Elements("//button[@value='Login']");
	
	
	public static Elements Username_RequiredError = new Elements("//span[@id='LoginInput_UserNameOrEmailAddress-error']"); // The User name or email address field is required.
	public static Elements Password_RequiredError = new Elements("//span[@id='password-input-error']"); // The Password field is required.
	
	public static Elements Invalid_Username_Or_Password_Error = new Elements("//div[@id='AbpPageAlerts']/div");  // Invalid username or password!
}   

```

To explain, *public static* remain same for all the elements to access across the project.

keyword *Elements* is a class that represents we are dealing with. The word *new Elements* creates an object for the class Elements as UserName.

("Username"); - This text Username with in quotes is the ID of the element Username textfield by which Playwright identifies.

while writing the elements, Please refer this section for each type of elements. Also above samples have different ways of writing simple and complex XPATHs

## Writing Scripts

Create a class in the Package and extends to basetest. See the sample below:

### Explaining the above class: 

**public class ClassName extends ClassName_Library** -   Here *ClassName_Library* is a Library class to write down the reusable methods used in the class *ClassName* 



 ### **Writing Methods :**
 -  Method name should start with small letter
 -  Method name should have numbering like TS01
 -  Method should have **@Test** annotation from TestNG.
 -  Preferred Method format -   \<ModuleName\>_TS01_\<Functionality_Name\>
 -  Sample Method name -   dsp_TS01_Login_with_Valid_Credentials

-   *Reporting.pass("pass Description")*    -    This line will print success line in the report.
-   *Reporting.fail("FailDescription")*     -   This line will print failure line in the report
-   *Reporting.fail("FailDescription", __true__)*     -   This line will print failure line in the report along with screenshot of the application.


### How to run as TestNG

-	Right click on the script file --> Run as --> TestNG Test
-	To Debug Right click -->	Debug as --> TestNG Test.

- we can run an individual test by selecting the test name and click **F11**. Test will initiate to execute.


### How to run failed Test scripts

- When executing a suite of test scripts, there is a possibility that some tests may fail.

- To address this, our framework provides the flexibility to re-run only the failed test scripts using a batch file named **FailRun.bat**, located in the repository folder.
