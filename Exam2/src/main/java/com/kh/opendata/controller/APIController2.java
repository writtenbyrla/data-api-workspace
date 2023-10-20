package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class APIController2 {
	
	private static final String serviceKey = "B89IuQPz4Ros4mmF86h9rCpFFjNL%2Bmi%2FnnjSEhyRRqVRyBqoP3ePHSrjoAwS0sGIhvPVTmbS8q0q8EtQV%2FQqxg%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="air.do", produces = "application/json; charset=utf-8")
	public String airPollution(String location) throws IOException {
	String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
	
	
			
	
	url += "?serviceKey=" + serviceKey;
	url += "&returnType=json";
	String sidoName = location;
	url += "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8"); 
	
	System.out.println("location : " + location);
	
	URL requestUrl = new URL(url);
	HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
	
	BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	
	String responseText = "";
	String line;
	while((line=br.readLine())!=null) {
		responseText += line;
	}
	
	br.close();
	urlConnection.disconnect();
	
	System.out.println(responseText);
	return responseText;
	}
}
