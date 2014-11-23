package object;

public class TestDataClass {
	
	
	/**
	 * TestCases sheet column headers variables
	 */
	private String TestCase_TestCaseID;
	private String TestCase_Desc;
	private String TestCase_RunMode;
	private String TestCase_Browser;
	private String TestCase_TestCaseRepeat;
	private String TestCase_TestDataSheet;
	
	/**
	 * TestSteps sheet column headers variables
	 */
	
	private String TestSteps_TestCaseID;
	private String TestSteps_TestStepID;
	private String TestSteps_Desc;
	private String TestSteps_ElementType;
	private String TestSteps_LocatorType;
	private String TestSteps_LocatorValue;
	private String TestSteps_Action;
	private String TestSteps_TestData;
	
	/***
	 
	 TestData for Login Sheet Column Header Variables
	 */
	 
	private String TestData_TestCaseID;
	private String TestData_TestStepID;
	private String TestData_UserName;
	private String TestData_Password;
	
	
	
	/**
	 * @return the testCase_TestCaseID
	 */
	public String getTestCase_TestCaseID() {
		return TestCase_TestCaseID;
	}
	/**
	 * @param testCase_TestCaseID the testCase_TestCaseID to set
	 */
	public void setTestCase_TestCaseID(String testCase_TestCaseID) {
		TestCase_TestCaseID = testCase_TestCaseID;
	}
	/**
	 * @return the testCase_Desc
	 */
	public String getTestCase_Desc() {
		return TestCase_Desc;
	}
	/**
	 * @param testCase_Desc the testCase_Desc to set
	 */
	public void setTestCase_Desc(String testCase_Desc) {
		TestCase_Desc = testCase_Desc;
	}
	/**
	 * @return the testCase_RunMode
	 */
	public String getTestCase_RunMode() {
		return TestCase_RunMode;
	}
	/**
	 * @param testCase_RunMode the testCase_RunMode to set
	 */
	public void setTestCase_RunMode(String testCase_RunMode) {
		TestCase_RunMode = testCase_RunMode;
	}
	/**
	 * @return the testCase_Browser
	 */
	public String getTestCase_Browser() {
		return TestCase_Browser;
	}
	/**
	 * @param testCase_Browser the testCase_Browser to set
	 */
	public void setTestCase_Browser(String testCase_Browser) {
		TestCase_Browser = testCase_Browser;
	}
	
	public String getTestCase_TestCaseRepeat() {
		return TestCase_TestCaseRepeat;
	}
	/**
	 * @param testCase_TestCaseRepeat the testCase_TestCaseRepeat to set
	 */
	public void setTestCase_TestCaseRepeat(String testCase_TestCaseRepeat) {
		TestCase_TestCaseRepeat = testCase_TestCaseRepeat;
	}
	/**
	 * @return the testCase_TestDataSheet
	 */
	public String getTestCase_TestDataSheet() {
		return TestCase_TestDataSheet;
	}
	/**
	 * @param testCase_TestDataSheet the testCase_TestDataSheet to set
	 */
	public void setTestCase_TestDataSheet(String testCase_TestDataSheet) {
		TestCase_TestDataSheet = testCase_TestDataSheet;
	}
	
	/**
	 * @return the testSteps_TestCaseID
	 */
	public String getTestSteps_TestCaseID() {
		return TestSteps_TestCaseID;
	}
	/**
	 * @param testSteps_TestCaseID the testSteps_TestCaseID to set
	 */
	public void setTestSteps_TestCaseID(String testSteps_TestCaseID) {
		TestSteps_TestCaseID = testSteps_TestCaseID;
	}
	/**
	 * @return the testSteps_TestStepID
	 */
	public String getTestSteps_TestStepID() {
		return TestSteps_TestStepID;
	}
	/**
	 * @param testSteps_TestStepID the testSteps_TestStepID to set
	 */
	public void setTestSteps_TestStepID(String testSteps_TestStepID) {
		TestSteps_TestStepID = testSteps_TestStepID;
	}
	/**
	 * @return the testSteps_Desc
	 */
	public String getTestSteps_Desc() {
		return TestSteps_Desc;
	}
	/**
	 * @param testSteps_Desc the testSteps_Desc to set
	 */
	public void setTestSteps_Desc(String testSteps_Desc) {
		TestSteps_Desc = testSteps_Desc;
	}
	/**
	 * @return the testSteps_ElementType
	 */
	public String getTestSteps_ElementType() {
		return TestSteps_ElementType;
	}
	/**
	 * @param testSteps_ElementType the testSteps_ElementType to set
	 */
	public void setTestSteps_ElementType(String testSteps_ElementType) {
		TestSteps_ElementType = testSteps_ElementType;
	}
	/**
	 * @return the testSteps_LocatorType
	 */
	public String getTestSteps_LocatorType() {
		return TestSteps_LocatorType;
	}
	/**
	 * @param testSteps_LocatorType the testSteps_LocatorType to set
	 */
	public void setTestSteps_LocatorType(String testSteps_LocatorType) {
		TestSteps_LocatorType = testSteps_LocatorType;
	}
	/**
	 * @return the testSteps_LocatorValue
	 */
	public String getTestSteps_LocatorValue() {
		return TestSteps_LocatorValue;
	}
	/**
	 * @param testSteps_LocatorValue the testSteps_LocatorValue to set
	 */
	public void setTestSteps_LocatorValue(String testSteps_LocatorValue) {
		TestSteps_LocatorValue = testSteps_LocatorValue;
	}
	/**
	 * @return the testSteps_Action
	 */
	public String getTestSteps_Action() {
		return TestSteps_Action;
	}
	/**
	 * @param testSteps_Action the testSteps_Action to set
	 */
	public void setTestSteps_Action(String testSteps_Action) {
		TestSteps_Action = testSteps_Action;
	}
	/**
	 * @return the testSteps_TestData
	 */
	public String getTestSteps_TestData() {
		return TestSteps_TestData;
	}
	/**
	 * @param testSteps_TestData the testSteps_TestData to set
	 */
	public void setTestSteps_TestData(String testSteps_TestData) {
		TestSteps_TestData = testSteps_TestData;
	}
	/**
	 * @return the testData_TestStepID
	 */
	public String getTestData_TestStepID() {
		return TestData_TestStepID;
	}
	/**
	 * @param testData_TestStepID the testData_TestStepID to set
	 */
	public void setTestData_TestStepID(String testData_TestStepID) {
		TestData_TestStepID = testData_TestStepID;
	}
	/**
	 * @return the testData_TestCaseID
	 */
	public String getTestData_TestCaseID() {
		return TestData_TestCaseID;
	}
	/**
	 * @param testData_TestCaseID the testData_TestCaseID to set
	 */
	public void setTestData_TestCaseID(String testData_TestCaseID) {
		TestData_TestCaseID = testData_TestCaseID;
	}
	/**
	 * @return the testData_UserName
	 */
	public String getTestData_UserName() {
		return TestData_UserName;
	}
	/**
	 * @param testData_UserName the testData_UserName to set
	 */
	public void setTestData_UserName(String testData_UserName) {
		TestData_UserName = testData_UserName;
	}
	/**
	 * @return the testData_Password
	 */
	public String getTestData_Password() {
		return TestData_Password;
	}
	/**
	 * @param testData_Password the testData_Password to set
	 */
	public void setTestData_Password(String testData_Password) {
		TestData_Password = testData_Password;
	}
	/**
	 * @return the testCase_TestCaseRepeat
	 */
	
	
}
