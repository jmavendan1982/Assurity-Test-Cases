import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

// Create a RequestObject for the API endpoint
RequestObject request = new RequestObject()
request.setRestRequestMethod("GET")
request.setRestUrl("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false")

// Send the request and retrieve the response
ResponseObject response = WS.sendRequest(request)

// Test Case 1 : Verify the Name is "Carbon credits"
def nameAssertion = response.getResponseText() =~ /"Name"\s*:\s*"Carbon credits"/
println "Acceptance Criteria 1: " + (nameAssertion ? "Passed" : "Failed")

// Test Case 2 : Verify CanRelist is true
def canRelistAssertion = response.getResponseText() =~ /"CanRelist"\s*:\s*true/
println "Acceptance Criteria 2: " + (canRelistAssertion ? "Passed" : "Failed")

// Test Case 3 : Verify the Promotions element with Name = "Gallery" has a Description containing the text "Good position in category"
def promotions = new groovy.json.JsonSlurper().parseText(response.getResponseText()).Promotions
def galleryPromotionAssertion = promotions.find { it.Name == "Gallery" && it.Description.contains("Good position in category") } != null
println "Acceptance Criteria 3: " + (galleryPromotionAssertion ? "Passed" : "Failed")
 