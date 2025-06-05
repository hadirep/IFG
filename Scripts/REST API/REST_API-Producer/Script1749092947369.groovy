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
import groovy.json.JsonOutput
import kafka.KafkaProducerHelper

// == PRODUCER ==
// == POST (mengirim) ==
def addProduct = WS.sendRequest(findTestObject('PUBLIC API/POST_addProduct'))
WS.verifyResponseStatusCode(addProduct, 201)
WS.verifyElementPropertyValue(addProduct, 'title', 'Product Hadi')

assert addProduct.getStatusCode() == 201
def responseText = addProduct.getResponseText()
println("Data dari API: \n $responseText")
KafkaProducerHelper.sendMessage("hadi-topic", responseText)

// === PUT (update) ===
def updateProduct = WS.sendRequest(findTestObject('PUBLIC API/PUT_UpdateProduct'))
WS.verifyResponseStatusCode(updateProduct, 200)
WS.verifyElementPropertyValue(updateProduct, 'title', 'Update Produk Hadi')