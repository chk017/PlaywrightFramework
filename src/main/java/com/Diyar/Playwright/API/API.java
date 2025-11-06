package com.Diyar.Playwright.API;

import java.util.Map;

import org.testng.Assert;

import com.Diyar.Playwright.BaseTest.BaseTest;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

public class API extends BaseTest{

	
	
	public void start() {

//		Initialize playwright
		playwright = Playwright.create();
		
		System.out.println("API mode enabled. Setting up API environment...");


		APIRequest apiRequest = playwright.request();
//		APIRequestContext apiRequestContext = apiRequest.newContext();
		
		
		
	    String baseUrl = getproperty("api.baseUrl");
	    
	    String token = getproperty("api.token"); // Or get from secrets manager

	    baseUrl = baseUrl.trim();

	    if (baseUrl == null || baseUrl.isEmpty()) {
	        throw new IllegalArgumentException("API base URL is not configured.");
	    }
	    
	    if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
	        throw new IllegalArgumentException("API base URL must start with http:// or https://");
	    }

	    
	    

	    // Example: Create API context using Playwright (if you're using Playwright API testing)
	    APIRequest.NewContextOptions options = new APIRequest.NewContextOptions()
	            .setBaseURL(baseUrl)
	            .setExtraHTTPHeaders(Map.of(
	                    "Authorization", "Bearer " + token,
	                    "Content-Type", "application/json"
	            ));

	     apiRequestContext = apiRequest.newContext(options);
	    System.out.println("API request context created with base URL: " + baseUrl);
	    
	    
APIResponse apiResponse = apiRequestContext.get(baseUrl);
		
		int statusCode = apiResponse.status();
		System.out.println("status code api2 : "+ statusCode);
		Assert.assertEquals(statusCode, 200);
		
		
		
		byte[] resBody = apiResponse.body();
		Map<String, String> resHeaders = apiResponse.headers();
		String resURL = apiResponse.url();
		
		
		System.out.println("status code : "+ statusCode);
		System.out.println("resp body : "+ resBody);
		System.out.println("resHeaders : "+ resHeaders.size());
		System.out.println("resURL : "+ resURL);
		
		int resHeaders_size = resHeaders.size();
		System.out.println("resHeaders_size = " + resHeaders_size);
	}
	
}
