import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// == Consumer ==
// == GET (ambil semua data)
def getAllProducts = WS.sendRequest(findTestObject('PUBLIC API/GET_AllProducts'))
WS.verifyResponseStatusCode(getAllProducts, 200)
WS.verifyElementPropertyValue(getAllProducts, 'products[0].title', 'Essence Mascara Lash Princess')

// == GET (ambil data berdasarkan ID)
def getProductByID = WS.sendRequest(findTestObject('PUBLIC API/GET_Product_ByID'))
WS.verifyResponseStatusCode(getProductByID, 200)
WS.verifyElementPropertyValue(getProductByID, 'title', 'Essence Mascara Lash Princess')